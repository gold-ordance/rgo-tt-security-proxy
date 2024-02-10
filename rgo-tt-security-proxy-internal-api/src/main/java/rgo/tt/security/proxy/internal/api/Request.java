package rgo.tt.security.proxy.internal.api;

public class Request {

    private final String source;
    private final String json;
    private final RequestMeta meta;

    private Request(String source, String json, RequestMeta meta) {
        this.source = source;
        this.json = json;
        this.meta = meta;
    }

    public static Request of(String source, RequestMeta meta) {
        return new Request(source, null, meta);
    }

    public static Request of(String source, RequestMeta meta, String json) {
        return new Request(source, json, meta);
    }

    public String getSource() {
        return source;
    }

    public String getJson() {
        return json;
    }

    public RequestMeta getMeta() {
        return meta;
    }
}
