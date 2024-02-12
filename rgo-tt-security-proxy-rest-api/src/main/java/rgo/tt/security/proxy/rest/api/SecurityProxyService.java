package rgo.tt.security.proxy.rest.api;

import com.linecorp.armeria.common.AggregatedHttpRequest;
import com.linecorp.armeria.common.HttpData;
import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.common.HttpStatus;
import com.linecorp.armeria.common.MediaType;
import com.linecorp.armeria.server.annotation.Blocking;
import com.linecorp.armeria.server.annotation.Delete;
import com.linecorp.armeria.server.annotation.Get;
import com.linecorp.armeria.server.annotation.Patch;
import com.linecorp.armeria.server.annotation.Post;
import com.linecorp.armeria.server.annotation.Put;
import rgo.tt.common.rest.api.StatusCode;
import rgo.tt.security.proxy.internal.api.Request;
import rgo.tt.security.proxy.internal.api.RequestMeta;
import rgo.tt.security.proxy.internal.api.RequestProxyProcessor;
import rgo.tt.security.proxy.internal.api.Response;
import rgo.tt.security.proxy.rest.api.extractor.JsonStatusCodeExtractor;
import rgo.tt.security.proxy.rest.api.extractor.PathExtractor;

import java.util.Map;

@Blocking
public class SecurityProxyService {

    private final RequestProxyProcessor proxy;
    private final PathExtractor pathExtractor;
    private final JsonStatusCodeExtractor statusCodeExtractor;

    public SecurityProxyService(
            RequestProxyProcessor proxy,
            PathExtractor pathExtractor,
            JsonStatusCodeExtractor statusCodeExtractor
    ) {
        this.proxy = proxy;
        this.pathExtractor = pathExtractor;
        this.statusCodeExtractor = statusCodeExtractor;
    }

    @Get("prefix:/")
    public HttpResponse get(AggregatedHttpRequest rq) {
        return handleRequestWithoutBody(rq);
    }

    private HttpResponse handleRequestWithoutBody(AggregatedHttpRequest rq) {
        Request request = createRequestWithoutBody(rq);
        Response response = proxy.handle(request);
        StatusCode statusCode = statusCodeExtractor.extract(response.getJson());
        int httpCode = statusCode.getHttpCode();

        if (HttpStatus.isContentAlwaysEmpty(httpCode)) {
            return HttpResponse.of(httpCode);
        }

        return HttpResponse.of(
                HttpStatus.valueOf(httpCode),
                MediaType.JSON_UTF_8,
                HttpData.ofUtf8(response.getJson())
        );
    }

    private Request createRequestWithoutBody(AggregatedHttpRequest rq) {
        String path = pathExtractor.extract(rq.path());
        RequestMeta meta = RequestMeta.of(Map.of("method", rq.method().name()));
        return Request.of(path, meta);
    }

    @Post("prefix:/")
    public HttpResponse post(AggregatedHttpRequest rq) {
        return handleRequestWithBody(rq);
    }

    private HttpResponse handleRequestWithBody(AggregatedHttpRequest rq) {
        try (HttpData content = rq.content()) {
            Request request = createRequestWithBody(rq, content);
            Response response = proxy.handle(request);
            StatusCode code = statusCodeExtractor.extract(response.getJson());

            return HttpResponse.of(
                    HttpStatus.valueOf(code.getHttpCode()),
                    MediaType.JSON_UTF_8,
                    HttpData.ofUtf8(response.getJson())
            );
        }
    }

    private Request createRequestWithBody(AggregatedHttpRequest rq, HttpData content) {
        String path = pathExtractor.extract(rq.path());
        RequestMeta meta = RequestMeta.of(Map.of("method", rq.method().name()));
        return Request.of(path, meta, content.toStringUtf8());
    }

    @Patch("prefix:/")
    public HttpResponse patch(AggregatedHttpRequest rq) {
        return handleRequestWithBody(rq);
    }

    @Put("prefix:/")
    public HttpResponse put(AggregatedHttpRequest rq) {
        return handleRequestWithBody(rq);
    }

    @Delete("prefix:/")
    public HttpResponse delete(AggregatedHttpRequest rq) {
        return handleRequestWithoutBody(rq);
    }
}
