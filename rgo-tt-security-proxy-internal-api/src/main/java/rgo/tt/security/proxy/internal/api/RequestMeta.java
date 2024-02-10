package rgo.tt.security.proxy.internal.api;

import java.util.Collections;
import java.util.Map;

public class RequestMeta {

    private final Map<String, String> params;

    private RequestMeta() {
        params = Collections.emptyMap();
    }

    private RequestMeta(Map<String, String> params) {
        this.params = params;
    }

    public static RequestMeta of() {
        return new RequestMeta();
    }

    public static RequestMeta of(Map<String, String> params) {
        return new RequestMeta(params);
    }

    public RequestMeta addValue(String paramName, String value) {
        params.put(paramName, value);
        return this;
    }

    public String getValue(String paramName) {
        return params.get(paramName);
    }

    public Map<String, String> getParams() {
        return params;
    }
}
