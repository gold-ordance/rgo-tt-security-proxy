package rgo.tt.security.proxy.internal.api.verification;

public class PassedVerificationResponse implements VerificationResponse {

    private PassedVerificationResponse() {
    }

    public static PassedVerificationResponse of() {
        return new PassedVerificationResponse();
    }

    @Override
    public VerificationStatus getStatus() {
        return VerificationStatus.PASSED;
    }
}
