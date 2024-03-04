package rgo.tt.security.proxy.boot.properties;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static rgo.tt.common.utils.RandomUtils.randomString;

@SpringBootTest
@ActiveProfiles("test")
class ServicesPropertiesTest {

    @Autowired private ServicesProperties properties;

    @Test
    void uri_exception() {
        String randomUri = randomString();
        assertThatThrownBy(() -> properties.uri(randomUri))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("The uri is not found by service name. serviceName=" + randomUri);
    }

    @ParameterizedTest
    @MethodSource("provideData")
    void uri(String serviceName, String expectedUri) {
        String actualUri = properties.uri(serviceName);
        assertThat(actualUri).isEqualTo(expectedUri);
    }

    private static Stream<Arguments> provideData() {
        return Stream.of(
                Arguments.of("clients", "http://localhost:8081")
        );
    }
}
