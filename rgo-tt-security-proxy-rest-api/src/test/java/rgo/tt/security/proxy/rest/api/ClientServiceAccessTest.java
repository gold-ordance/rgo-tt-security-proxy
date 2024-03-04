package rgo.tt.security.proxy.rest.api;

import com.linecorp.armeria.client.WebClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ClientServiceAccessTest {

    private ClientServiceAccess serviceAccess;

    @BeforeEach
    void setUp() {
        var client = WebClient.of();
        var converter = new DefaultHttpMethodConverter();
        serviceAccess = new ClientServiceAccess(client, converter);
    }

    @Test
    void serviceName() {
        String serviceName = serviceAccess.serviceName();
        assertThat(serviceName).isEqualTo("clients");
    }
}
