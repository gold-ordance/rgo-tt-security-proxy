package rgo.tt.security.proxy.service.invoker;

import rgo.tt.security.proxy.internal.api.Request;
import rgo.tt.security.proxy.internal.api.Response;

public interface ServiceInvoker {

    Response invoke(Request rq);
}
