package rgo.tt.security.proxy.rest.api;

import com.linecorp.armeria.common.HttpMethod;
import rgo.tt.security.proxy.internal.api.Request;

public interface HttpMethodConverter {

    HttpMethod convert(Request rq);
}
