package rgo.tt.security.proxy.rest.api.extractor;

public class PathExtractor {

    private final String basePath;

    public PathExtractor(String basePath) {
        this.basePath = basePath;
    }

    public String extract(String path) {
        return path.substring(basePath.length());
    }
}
