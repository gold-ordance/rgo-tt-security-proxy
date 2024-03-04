package rgo.tt.security.proxy.service;

import com.linecorp.armeria.common.HttpMethod;
import rgo.tt.security.proxy.internal.api.Request;

public class DefaultHttpMethodConverter implements HttpMethodConverter {

    private static final String DEFAULT_PARAM_NAME = "method";

    private final String paramName;

    public DefaultHttpMethodConverter(String paramName) {
        this.paramName = paramName;
    }

    public DefaultHttpMethodConverter() {
        paramName = DEFAULT_PARAM_NAME;
    }

    @Override
    public HttpMethod convert(Request rq) {
        String method = rq.getMeta().getValue(paramName);
        return HttpMethod.tryParse(method);
    }
}
