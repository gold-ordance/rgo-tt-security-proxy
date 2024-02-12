package rgo.tt.security.proxy.internal.api;

public class Response {

    private final String json;
    private final ResponseMeta meta;

    private Response(String json, ResponseMeta meta) {
        this.json = json;
        this.meta = meta;
    }

    public static Response of(String json) {
        return new Response(json, ResponseMeta.of());
    }

    public static Response of(String json, ResponseMeta meta) {
        return new Response(json, meta);
    }

    public String getJson() {
        return json;
    }

    public ResponseMeta getMeta() {
        return meta;
    }
}
