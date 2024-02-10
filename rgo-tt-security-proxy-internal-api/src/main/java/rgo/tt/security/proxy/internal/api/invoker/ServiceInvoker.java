package rgo.tt.security.proxy.internal.api.invoker;

import rgo.tt.security.proxy.internal.api.Request;
import rgo.tt.security.proxy.internal.api.Response;

public interface ServiceInvoker {

    Response invoke(Request rq);
}
