package rgo.tt.security.proxy.rest.api;

import com.linecorp.armeria.common.HttpMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import rgo.tt.security.proxy.internal.api.Request;
import rgo.tt.security.proxy.internal.api.RequestMeta;

import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static rgo.tt.common.utils.RandomUtils.randomString;

class DefaultHttpMethodConverterTest {

    private DefaultHttpMethodConverter converter;

    @BeforeEach
    void setUp() {
        converter = new DefaultHttpMethodConverter();
    }

    @ParameterizedTest
    @MethodSource("provideData")
    void convert(String methodName, HttpMethod expectedHttpMethod) {
        String source = randomString();
        RequestMeta meta = RequestMeta.of(Map.of("method", methodName));
        Request request = Request.of(source, meta);

        HttpMethod httpMethod = converter.convert(request);

        assertThat(httpMethod).isEqualTo(expectedHttpMethod);
    }

    private static Stream<Arguments> provideData() {
        return Stream.of(
                Arguments.of("OPTIONS", HttpMethod.OPTIONS),
                Arguments.of("GET", HttpMethod.GET),
                Arguments.of("HEAD", HttpMethod.HEAD),
                Arguments.of("POST", HttpMethod.POST),
                Arguments.of("PUT", HttpMethod.PUT),
                Arguments.of("PATCH", HttpMethod.PATCH),
                Arguments.of("DELETE", HttpMethod.DELETE),
                Arguments.of("TRACE", HttpMethod.TRACE),
                Arguments.of("CONNECT", HttpMethod.CONNECT),
                Arguments.of("UNKNOWN", null)
        );
    }
}
