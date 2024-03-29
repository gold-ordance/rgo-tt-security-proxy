package rgo.tt.security.proxy.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rgo.tt.security.proxy.common.om.DefaultObjectMapperProvider;
import rgo.tt.security.proxy.internal.api.RequestProxyProcessor;
import rgo.tt.security.proxy.internal.api.Response;
import rgo.tt.security.proxy.service.invoker.ServiceInvoker;
import rgo.tt.security.proxy.service.verification.RequestVerificationChain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static rgo.tt.common.utils.RandomUtils.randomString;
import static rgo.tt.security.proxy.service.DataGenerator.passed;
import static rgo.tt.security.proxy.service.DataGenerator.randomRequest;
import static rgo.tt.security.proxy.service.DataGenerator.rejected;

@ExtendWith(MockitoExtension.class)
class DefaultRequestProxyProcessorTest {

    private RequestProxyProcessor proxy;

    @Mock private ServiceInvoker serviceInvoker;
    @Mock private RequestVerificationChain verificationChain;

    @BeforeEach
    void setUp() {
        DefaultJsonSerialization serialization = new DefaultJsonSerialization(new DefaultObjectMapperProvider());
        proxy = new DefaultRequestProxyProcessor(serviceInvoker, verificationChain, serialization);
    }

    @Test
    void handle_rejected() {
        String module = randomString();
        String errorMessage = randomString();
        when(verificationChain.verify(any())).thenReturn(rejected(module, errorMessage));

        Response response = proxy.handle(randomRequest());

        assertThat(response.getJson()).contains(module, errorMessage);
    }

    @Test
    void handle_passed() {
        String json = randomString();
        when(verificationChain.verify(any())).thenReturn(passed());
        when(serviceInvoker.invoke(any())).thenReturn(Response.of(json));

        Response response = proxy.handle(randomRequest());

        assertThat(response.getJson()).isEqualTo(json);
    }
}
