package rgo.tt.security.proxy.internal.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import rgo.tt.security.proxy.common.om.ObjectMapperProvider;
import rgo.tt.security.proxy.common.om.exception.JsonRuntimeException;

public class DefaultJsonSerialization implements JsonSerialization {

    private final ObjectMapperProvider provider;

    public DefaultJsonSerialization(ObjectMapperProvider provider) {
        this.provider = provider;
    }

    @Override
    public String json(Object o) {
        try {
            return provider.getObjectMapper().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new JsonRuntimeException("Serialization failed. object=" + o, e);
        }
    }
}
