package rgo.tt.security.proxy.rest.api.extractor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import rgo.tt.common.rest.api.StatusCode;
import rgo.tt.security.proxy.common.om.DefaultObjectMapperProvider;

import static org.assertj.core.api.Assertions.assertThat;

class JsonStatusCodeExtractorTest {

    private JsonStatusCodeExtractor extractor;

    @BeforeEach
    void setUp() {
        extractor = new JsonStatusCodeExtractor(new DefaultObjectMapperProvider());
    }

    @ParameterizedTest
    @EnumSource(StatusCode.class)
    void extract(StatusCode code) {
        String json = """
                {
                    "status": {
                        "statusCode": "{code}"
                    }
                }
                """.replace("{code}", code.name());

        StatusCode statusCode = extractor.extract(json);
        assertThat(statusCode).isEqualTo(code);
    }
}
