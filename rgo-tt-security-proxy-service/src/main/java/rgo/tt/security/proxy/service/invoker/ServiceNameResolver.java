package rgo.tt.security.proxy.service.invoker;

import rgo.tt.security.proxy.internal.api.Request;

public interface ServiceNameResolver {

    String resolve(Request rq);
}
