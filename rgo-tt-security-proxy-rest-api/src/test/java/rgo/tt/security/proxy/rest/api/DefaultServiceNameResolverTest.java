package rgo.tt.security.proxy.rest.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import rgo.tt.security.proxy.internal.api.Request;
import rgo.tt.security.proxy.internal.api.RequestMeta;
import rgo.tt.security.proxy.rest.api.exception.ServiceNameResolverException;
import rgo.tt.security.proxy.service.invoker.ServiceNameResolver;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static rgo.tt.common.utils.RandomUtils.randomString;

class DefaultServiceNameResolverTest {

    private ServiceNameResolver resolver;

    @BeforeEach
    void setUp() {
        resolver = new DefaultServiceNameResolver();
    }

    @ParameterizedTest
    @MethodSource("data")
    void resolve(String source, String expected) {
        Request request = Request.of(source, RequestMeta.of());
        String resolved = resolver.resolve(request);
        assertThat(resolved).isEqualTo(expected);
    }

    private static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of("/clients/1", "clients"),
                Arguments.of("/tasks?name=12&boardId=1", "tasks"),
                Arguments.of("/tasks-boards/1", "tasks-boards"),
                Arguments.of("/statuses", "statuses")
        );
    }

    @Test
    void resolve_exception() {
        Request request = Request.of(randomString(), RequestMeta.of());
        assertThatExceptionOfType(ServiceNameResolverException.class)
                .isThrownBy(() -> resolver.resolve(request));
    }
}
