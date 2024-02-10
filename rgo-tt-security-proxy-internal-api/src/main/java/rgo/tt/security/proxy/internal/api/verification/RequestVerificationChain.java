package rgo.tt.security.proxy.internal.api.verification;

import rgo.tt.security.proxy.internal.api.Request;

public interface RequestVerificationChain {

    VerificationResponse verify(Request rq);
}
