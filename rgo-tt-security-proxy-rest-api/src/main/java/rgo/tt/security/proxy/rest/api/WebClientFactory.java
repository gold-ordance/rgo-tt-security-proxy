package rgo.tt.security.proxy.rest.api;

import com.linecorp.armeria.client.WebClient;

public class WebClientFactory {

    private final ClientFactoryProvider factoryProvider;

    public WebClientFactory(ClientFactoryProvider factoryProvider) {
        this.factoryProvider = factoryProvider;
    }

    public WebClient createClient(String uri) {
        return WebClient.builder(uri)
                .factory(factoryProvider.getFactory())
                .build();
    }
}
