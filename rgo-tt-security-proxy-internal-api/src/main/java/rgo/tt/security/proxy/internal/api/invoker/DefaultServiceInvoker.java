package rgo.tt.security.proxy.internal.api.invoker;

import rgo.tt.security.proxy.internal.api.Request;
import rgo.tt.security.proxy.internal.api.Response;

public class DefaultServiceInvoker implements ServiceInvoker {

    private final ServiceAccessProvider serviceAccessProvider;
    private final ServiceNameResolver serviceNameResolver;

    public DefaultServiceInvoker(ServiceAccessProvider serviceAccessProvider, ServiceNameResolver serviceNameResolver) {
        this.serviceAccessProvider = serviceAccessProvider;
        this.serviceNameResolver = serviceNameResolver;
    }

    @Override
    public Response invoke(Request rq) {
        String serviceName = serviceNameResolver.resolve(rq);
        ServiceAccess serviceAccess = serviceAccessProvider.get(serviceName);
        return serviceAccess.call(rq);
    }
}
