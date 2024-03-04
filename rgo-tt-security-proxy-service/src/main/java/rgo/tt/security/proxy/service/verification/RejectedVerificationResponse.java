package rgo.tt.security.proxy.service.verification;

public class RejectedVerificationResponse implements VerificationResponse {

    private final String module;
    private final String errorMessage;

    private RejectedVerificationResponse(String module, String errorMessage) {
        this.module = module;
        this.errorMessage = errorMessage;
    }

    public static RejectedVerificationResponse of(String module, String errorMessage) {
        return new RejectedVerificationResponse(module, errorMessage);
    }

    @Override
    public VerificationStatus getStatus() {
        return VerificationStatus.REJECTED;
    }

    public String getModule() {
        return module;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
