package rgo.tt.security.proxy.rest.api;

import com.linecorp.armeria.client.WebClient;
import com.linecorp.armeria.common.AggregatedHttpResponse;
import com.linecorp.armeria.common.HttpRequest;
import com.linecorp.armeria.common.RequestHeaders;
import rgo.tt.security.proxy.internal.api.Request;
import rgo.tt.security.proxy.internal.api.Response;
import rgo.tt.security.proxy.service.invoker.ServiceAccess;

public class ClientServiceAccess implements ServiceAccess {

    private final WebClient client;
    private final HttpMethodConverter converter;

    public ClientServiceAccess(WebClient client, HttpMethodConverter converter) {
        this.client = client;
        this.converter = converter;
    }

    @Override
    public String serviceName() {
        return "clients";
    }

    @Override
    public Response call(Request rq) {
        HttpRequest request =
                HttpRequest.of(RequestHeaders.builder()
                        .method(converter.convert(rq))
                        .path(rq.getSource())
                        .build());

        AggregatedHttpResponse httpResponse = client.execute(request).aggregate().join();
        return Response.of(httpResponse.contentUtf8());
    }
}
