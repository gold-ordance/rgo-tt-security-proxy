package rgo.tt.security.proxy.service.invoker;

import rgo.tt.security.proxy.service.exception.ServiceAccessNotFoundException;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DefaultServiceAccessProvider implements ServiceAccessProvider {

    private final Map<String, ServiceAccess> serviceAccessByName;

    public DefaultServiceAccessProvider(List<ServiceAccess> services) {
        serviceAccessByName = services.stream()
                .collect(Collectors.toMap(ServiceAccess::serviceName, Function.identity()));
    }

    @Override
    public ServiceAccess get(String serviceName) {
        ServiceAccess access = serviceAccessByName.get(serviceName);
        if (access == null) {
            throw new ServiceAccessNotFoundException("Service access not found for service name: " + serviceName);
        }

        return access;
    }
}
