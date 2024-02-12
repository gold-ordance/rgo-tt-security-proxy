package rgo.tt.security.proxy.internal.api;

import java.util.Collections;
import java.util.Map;

public class ResponseMeta {

    private final Map<String, String> params;

    private ResponseMeta() {
        params = Collections.emptyMap();
    }

    private ResponseMeta(Map<String, String> params) {
        this.params = params;
    }

    public static ResponseMeta of() {
        return new ResponseMeta();
    }

    public static ResponseMeta of(Map<String, String> params) {
        return new ResponseMeta(params);
    }

    public ResponseMeta addValue(String paramName, String value) {
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
