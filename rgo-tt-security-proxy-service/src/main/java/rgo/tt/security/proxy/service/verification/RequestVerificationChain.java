package rgo.tt.security.proxy.service.verification;

import rgo.tt.security.proxy.internal.api.Request;

public interface RequestVerificationChain {

    VerificationResponse verify(Request rq);
}
