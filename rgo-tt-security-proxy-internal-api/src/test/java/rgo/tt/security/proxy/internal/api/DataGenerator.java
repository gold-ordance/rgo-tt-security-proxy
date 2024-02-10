package rgo.tt.security.proxy.internal.api;

import rgo.tt.security.proxy.internal.api.verification.PassedVerificationResponse;
import rgo.tt.security.proxy.internal.api.verification.RejectedVerificationResponse;

import static rgo.tt.common.utils.RandomUtils.randomString;

public final class DataGenerator {

    private DataGenerator() {
    }

    public static Request randomRequest() {
        return Request.of(randomString(), randomString());
    }

    public static PassedVerificationResponse passed() {
        return PassedVerificationResponse.of();
    }

    public static RejectedVerificationResponse rejected() {
        return RejectedVerificationResponse.of(randomString(), randomString());
    }

    public static RejectedVerificationResponse rejected(String module, String errorMessage) {
        return RejectedVerificationResponse.of(module, errorMessage);
    }
}
