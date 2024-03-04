package rgo.tt.security.proxy.service;

import com.linecorp.armeria.common.HttpMethod;
import rgo.tt.security.proxy.internal.api.Request;

public interface HttpMethodConverter {

    HttpMethod convert(Request rq);
}
