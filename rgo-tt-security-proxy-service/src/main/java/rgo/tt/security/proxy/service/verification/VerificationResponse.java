package rgo.tt.security.proxy.service.verification;

public interface VerificationResponse {

    VerificationStatus getStatus();

    default boolean isRejected() {
        return getStatus() == VerificationStatus.REJECTED;
    }
}