package rgo.tt.security.proxy.service.invoker;

import rgo.tt.security.proxy.internal.api.Request;
import rgo.tt.security.proxy.internal.api.Response;

public interface ServiceAccess {

    String serviceName();

    Response call(Request rq);
}
