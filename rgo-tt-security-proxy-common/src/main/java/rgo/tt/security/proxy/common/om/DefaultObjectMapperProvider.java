package rgo.tt.security.proxy.common.om;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class DefaultObjectMapperProvider implements ObjectMapperProvider {

    private final ObjectMapper om;

    public DefaultObjectMapperProvider(ObjectMapper om) {
        this.om = om;
    }

    public DefaultObjectMapperProvider() {
        om = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Override
    public ObjectMapper getObjectMapper() {
        return om;
    }
}
