package rgo.tt.security.proxy.internal.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class DefaultJsonSerialization implements JsonSerialization {

    private final ObjectMapper om;

    public DefaultJsonSerialization(ObjectMapper om) {
        this.om = om;
    }

    public DefaultJsonSerialization() {
        om = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Override
    public String json(Object o) {
        try {
            return om.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new JsonRuntimeException("Serialization failed. object=" + o, e);
        }
    }

    public static class JsonRuntimeException extends RuntimeException {

        public JsonRuntimeException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
