package rgo.tt.security.proxy.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rgo.tt.security.proxy.common.om.DefaultObjectMapperProvider;

import static org.assertj.core.api.Assertions.assertThat;
import static rgo.tt.common.utils.RandomUtils.randomPositiveInteger;
import static rgo.tt.common.utils.RandomUtils.randomString;

class DefaultJsonSerializationTest {

    private DefaultJsonSerialization serialization;

    @BeforeEach
    void setUp() {
        serialization = new DefaultJsonSerialization(new DefaultObjectMapperProvider());
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
