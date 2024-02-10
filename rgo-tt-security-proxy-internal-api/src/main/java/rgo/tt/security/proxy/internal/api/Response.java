package rgo.tt.security.proxy.internal.api;

public class Response {

    private final String json;

    private Response(String json) {
        this.json = json;
    }

    public static Response of(String json) {
        return new Response(json);
    }

    public String getJson() {
        return json;
    }
}
