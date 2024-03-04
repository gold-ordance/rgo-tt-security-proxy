package rgo.tt.security.proxy.service.invoker;

import org.junit.jupiter.api.Test;
import rgo.tt.security.proxy.internal.api.Request;
import rgo.tt.security.proxy.internal.api.Response;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static rgo.tt.common.utils.RandomUtils.randomString;

class DefaultServiceAccessProviderTest {

    private DefaultServiceAccessProvider provider;

    @Test
    void get() {
        String serviceName = randomString();
        ServiceAccess expected = new ServiceAccess() {
            @Override public String serviceName() {return serviceName;}
            @Override public Response call(Request rq) {return null;}
        };
        provider = new DefaultServiceAccessProvider(List.of(expected));

        ServiceAccess actual = provider.get(serviceName);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void get_exception() {
        provider = new DefaultServiceAccessProvider(List.of());
        String serviceName = randomString();
        assertThatExceptionOfType(DefaultServiceAccessProvider.ServiceAccessNotFoundException.class)
                .isThrownBy(() -> provider.get(serviceName));
    }
}
