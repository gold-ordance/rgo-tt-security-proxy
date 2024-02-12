package rgo.tt.security.proxy.rest.api.extractor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static rgo.tt.common.utils.RandomUtils.randomString;

class PathExtractorTest {

    private static final String BASE_PATH = "/api/v1";

    private PathExtractor extractor;

    @BeforeEach
    void setUp() {
        extractor = new PathExtractor(BASE_PATH);
    }

    @Test
    void extract() {
        String randomPath = "/" + randomString();
        String path = BASE_PATH + randomPath;

        String extracted = extractor.extract(path);

        assertThat(extracted).isEqualTo(randomPath);
    }
}
