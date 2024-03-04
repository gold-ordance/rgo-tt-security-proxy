package rgo.tt.security.proxy.service.invoker;

public interface ServiceAccessProvider {

    ServiceAccess get(String serviceName);
}
