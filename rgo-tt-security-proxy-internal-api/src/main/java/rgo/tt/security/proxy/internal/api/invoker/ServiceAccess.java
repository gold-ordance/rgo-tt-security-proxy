package rgo.tt.security.proxy.internal.api.invoker;

import rgo.tt.security.proxy.internal.api.Request;
import rgo.tt.security.proxy.internal.api.Response;

public interface ServiceAccess {

    Response call(Request rq);
}
