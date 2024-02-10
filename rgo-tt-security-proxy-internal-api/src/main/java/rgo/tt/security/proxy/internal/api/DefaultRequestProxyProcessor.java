package rgo.tt.security.proxy.internal.api;

import rgo.tt.security.proxy.internal.api.invoker.ServiceInvoker;
import rgo.tt.security.proxy.internal.api.verification.RequestVerificationChain;
import rgo.tt.security.proxy.internal.api.verification.VerificationResponse;

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
