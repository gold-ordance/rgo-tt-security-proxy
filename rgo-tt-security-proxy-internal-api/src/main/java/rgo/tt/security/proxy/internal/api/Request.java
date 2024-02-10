package rgo.tt.security.proxy.internal.api;

public class Request {

    private final String source;
    private final String json;

    private Request(String source, String json) {
        this.source = source;
        this.json = json;
    }

    public static Request of(String source) {
        return new Request(source, null);
    }

    public static Request of(String source, String json) {
        return new Request(source, json);
    }

    public String getSource() {
        return source;
    }

    public String getJson() {
        return json;
    }
}
