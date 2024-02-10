package rgo.tt.security.proxy.internal.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static rgo.tt.common.utils.RandomUtils.randomPositiveInteger;
import static rgo.tt.common.utils.RandomUtils.randomString;

class DefaultResponseJsonSerializationTest {

    private DefaultResponseJsonSerialization serialization;

    @BeforeEach
    void setUp() {
        serialization = new DefaultResponseJsonSerialization();
    }

    @Test
    void json_success() {
        Value v = Value.of(randomString(), randomPositiveInteger());
        String json = serialization.json(v);
        assertThat(json).contains(v.string, v.integer.toString());
    }

    private record Value(String string, Integer integer) {

        public static Value of(String string, Integer integer) {
                return new Value(string, integer);
        }
    }
}
