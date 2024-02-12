package rgo.tt.security.proxy.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rgo.tt.security.proxy.common.om.DefaultObjectMapperProvider;
import rgo.tt.security.proxy.common.om.ObjectMapperProvider;
import rgo.tt.security.proxy.internal.api.DefaultJsonSerialization;
import rgo.tt.security.proxy.internal.api.DefaultRequestProxyProcessor;
import rgo.tt.security.proxy.internal.api.JsonSerialization;
import rgo.tt.security.proxy.internal.api.Request;
import rgo.tt.security.proxy.internal.api.RequestProxyProcessor;
import rgo.tt.security.proxy.internal.api.Response;
import rgo.tt.security.proxy.internal.api.invoker.DefaultServiceInvoker;
import rgo.tt.security.proxy.internal.api.invoker.ServiceAccess;
import rgo.tt.security.proxy.internal.api.invoker.ServiceAccessProvider;
import rgo.tt.security.proxy.internal.api.invoker.ServiceInvoker;
import rgo.tt.security.proxy.internal.api.invoker.ServiceNameResolver;
import rgo.tt.security.proxy.internal.api.verification.DefaultRequestVerificationChain;
import rgo.tt.security.proxy.internal.api.verification.PassedVerificationResponse;
import rgo.tt.security.proxy.internal.api.verification.RequestVerification;
import rgo.tt.security.proxy.internal.api.verification.RequestVerificationChain;
import rgo.tt.security.proxy.rest.api.SecurityProxyService;
import rgo.tt.security.proxy.rest.api.extractor.JsonStatusCodeExtractor;
import rgo.tt.security.proxy.rest.api.extractor.PathExtractor;
import rgo.tt.security.proxy.internal.api.invoker.DefaultServiceAccessProvider;
import rgo.tt.security.proxy.service.DefaultServiceNameResolver;

import java.util.List;
import java.util.Set;

@Configuration
public class AppConfig {

    @Bean
    public ObjectMapperProvider objectMapperProvider() {
        return new DefaultObjectMapperProvider();
    }

    @Bean
    public PathExtractor pathExtractor() {
        return new PathExtractor("/api/v1");
    }

    @Bean
    public JsonStatusCodeExtractor jsonStatusCodeExtractor() {
        return new JsonStatusCodeExtractor(objectMapperProvider());
    }

    @Bean
    public ServiceAccess serviceAccessStub() {

        return new ServiceAccess() {
            @Override
            public String serviceName() {
                return null;
            }

            @Override
            public Response call(Request rq) {
                return null;
            }
        };
    }

    @Bean
    public ServiceAccessProvider serviceAccessProvider(List<ServiceAccess> services) {
        return new DefaultServiceAccessProvider(services);
    }

    @Bean
    public ServiceNameResolver serviceNameResolver() {
        return new DefaultServiceNameResolver();
    }

    @Bean
    public ServiceInvoker serviceInvoker(ServiceAccessProvider provider) {
        return new DefaultServiceInvoker(provider, serviceNameResolver());
    }

    @Bean
    public RequestVerification requestVerificationStub() {
        return rq -> PassedVerificationResponse.of();
    }

    @Bean
    public RequestVerificationChain requestVerificationChain(Set<RequestVerification> verifications) {
        return new DefaultRequestVerificationChain(verifications);
    }

    @Bean
    public JsonSerialization responseJsonSerialization() {
        return new DefaultJsonSerialization(objectMapperProvider());
    }

    @Bean
    public RequestProxyProcessor requestProxyProcessor(ServiceInvoker invoker, RequestVerificationChain chain) {
        return new DefaultRequestProxyProcessor(invoker, chain, responseJsonSerialization());
    }

    @Bean
    public SecurityProxyService securityProxyService(RequestProxyProcessor proxy) {
        return new SecurityProxyService(proxy, pathExtractor(), jsonStatusCodeExtractor());
    }
}
