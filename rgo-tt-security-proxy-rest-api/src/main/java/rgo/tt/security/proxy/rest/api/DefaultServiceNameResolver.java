package rgo.tt.security.proxy.rest.api;

import rgo.tt.security.proxy.internal.api.Request;
import rgo.tt.security.proxy.service.invoker.ServiceNameResolver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefaultServiceNameResolver implements ServiceNameResolver {

    private static final String REGEX = "^/([^/?]+)";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    @Override
    public String resolve(Request rq) {
        Matcher matcher = PATTERN.matcher(rq.getSource());
        if (matcher.find()) {
            return matcher.group(1);
        }

        throw new ServiceNameResolverException("Failed to resolve service name for source: " + rq.getSource());
    }

    public static class ServiceNameResolverException extends RuntimeException {

        public ServiceNameResolverException(String message) {
            super(message);
        }
    }
}
