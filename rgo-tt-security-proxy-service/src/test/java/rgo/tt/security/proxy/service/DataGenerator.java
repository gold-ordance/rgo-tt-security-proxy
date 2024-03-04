package rgo.tt.security.proxy.service;

import rgo.tt.security.proxy.internal.api.Request;
import rgo.tt.security.proxy.internal.api.RequestMeta;
import rgo.tt.security.proxy.service.verification.PassedVerificationResponse;
import rgo.tt.security.proxy.service.verification.RejectedVerificationResponse;

import static rgo.tt.common.utils.RandomUtils.randomString;

public final class DataGenerator {

    private DataGenerator() {
    }

    public static Request randomRequest() {
        return Request.of(randomString(), RequestMeta.of(), randomString());
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
