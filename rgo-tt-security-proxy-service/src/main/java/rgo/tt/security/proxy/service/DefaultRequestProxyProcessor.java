package rgo.tt.security.proxy.service;

import rgo.tt.security.proxy.internal.api.Request;
import rgo.tt.security.proxy.internal.api.RequestProxyProcessor;
import rgo.tt.security.proxy.internal.api.Response;
import rgo.tt.security.proxy.service.invoker.ServiceInvoker;
import rgo.tt.security.proxy.service.verification.RequestVerificationChain;
import rgo.tt.security.proxy.service.verification.VerificationResponse;

public class DefaultRequestProxyProcessor implements RequestProxyProcessor {

    private final ServiceInvoker serviceInvoker;
    private final RequestVerificationChain verificationChain;
    private final JsonSerialization serialization;

    public DefaultRequestProxyProcessor(
            ServiceInvoker serviceInvoker,
            RequestVerificationChain verificationChain,
            JsonSerialization serialization
    ) {
        this.serviceInvoker = serviceInvoker;
        this.verificationChain = verificationChain;
        this.serialization = serialization;
    }

    @Override
    public Response handle(Request rq) {
        VerificationResponse vr = verificationChain.verify(rq);
        if (vr.isRejected()) {
            return Response.of(serialization.json(vr));
        }

        return serviceInvoker.invoke(rq);
    }
}
