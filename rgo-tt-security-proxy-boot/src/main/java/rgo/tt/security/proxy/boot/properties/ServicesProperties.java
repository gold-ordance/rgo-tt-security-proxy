package rgo.tt.security.proxy.boot.properties;

import java.util.Map;

public class ServicesProperties {

    private Map<String, String> services;

    public String uri(String serviceName) {
        String uri = services.get(serviceName);
        if (uri == null) {
            throw new IllegalArgumentException("The uri is not found by service name. serviceName=" + serviceName);
        }

        return uri;
    }

    public Map<String, String> getServices() {
        return services;
    }

    public ServicesProperties setServices(Map<String, String> services) {
        this.services = services;
        return this;
    }
}
