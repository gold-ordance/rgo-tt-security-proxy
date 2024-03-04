package rgo.tt.security.proxy.service.verification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static rgo.tt.security.proxy.service.DataGenerator.passed;
import static rgo.tt.security.proxy.service.DataGenerator.randomRequest;
import static rgo.tt.security.proxy.service.DataGenerator.rejected;

@ExtendWith(MockitoExtension.class)
class DefaultRequestVerificationChainTest {

    private RequestVerificationChain chain;

    @Mock private RequestVerification verification;

    @BeforeEach
    void setUp() {
        chain = new DefaultRequestVerificationChain(Set.of(verification));
    }

    @Test
    void verify_rejected() {
        when(verification.verify(any())).thenReturn(rejected());

        VerificationResponse response = chain.verify(randomRequest());

        assertThat(response.getStatus()).isEqualTo(VerificationStatus.REJECTED);
        assertThat(response.isRejected()).isTrue();
    }

    @Test
    void verify_passed() {
        when(verification.verify(any())).thenReturn(passed());

        VerificationResponse response = chain.verify(randomRequest());

        assertThat(response.getStatus()).isEqualTo(VerificationStatus.PASSED);
        assertThat(response.isRejected()).isFalse();
    }
}
