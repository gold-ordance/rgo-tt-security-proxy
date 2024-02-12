package rgo.tt.security.proxy.rest.api.extractor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import rgo.tt.common.rest.api.StatusCode;
import rgo.tt.security.proxy.common.om.ObjectMapperProvider;
import rgo.tt.security.proxy.common.om.exception.JsonRuntimeException;

public class JsonStatusCodeExtractor {

    private final ObjectMapperProvider provider;

    public JsonStatusCodeExtractor(ObjectMapperProvider provider) {
        this.provider = provider;
    }

    public StatusCode extract(String json) {
        try {
            JsonNode node = provider.getObjectMapper().readTree(json);
            String code = node.get("status").get("statusCode").asText();
            return StatusCode.valueOf(code);
        } catch (JsonProcessingException e) {
            throw new JsonRuntimeException(e);
        }
    }
}
