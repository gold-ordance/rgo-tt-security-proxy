package rgo.tt.security.proxy.internal.api.verification;

import rgo.tt.security.proxy.internal.api.Request;

import java.util.Set;

public class DefaultRequestVerificationChain implements RequestVerificationChain {

    private final Set<RequestVerification> verifications;

    public DefaultRequestVerificationChain(Set<RequestVerification> verifications) {
        this.verifications = verifications;
    }

    @Override
    public VerificationResponse verify(Request rq) {
        for (RequestVerification verification : verifications) {
            VerificationResponse vr = verification.verify(rq);
            if (vr.isRejected()) {
                return vr;
            }
        }

        return PassedVerificationResponse.of();
    }
}
