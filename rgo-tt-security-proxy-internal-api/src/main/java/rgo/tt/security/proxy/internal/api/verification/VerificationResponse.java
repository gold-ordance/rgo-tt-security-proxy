package rgo.tt.security.proxy.internal.api.verification;

public interface VerificationResponse {

    VerificationStatus getStatus();

    default boolean isRejected() {
        return getStatus() == VerificationStatus.REJECTED;
    }
}