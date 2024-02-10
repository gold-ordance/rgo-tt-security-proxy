package rgo.tt.security.proxy.internal.api.invoker;

public interface ServiceAccessProvider {

    ServiceAccess get(String serviceName);
}
