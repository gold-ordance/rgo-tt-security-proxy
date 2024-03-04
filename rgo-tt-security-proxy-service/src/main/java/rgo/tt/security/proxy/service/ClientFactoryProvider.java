package rgo.tt.security.proxy.service;

import com.linecorp.armeria.client.ClientFactory;

public class ClientFactoryProvider {

    private final ClientFactory factory;

    public ClientFactoryProvider() {
        this.factory = ClientFactory.ofDefault();
    }

    public ClientFactory getFactory() {
        return factory;
    }
}
