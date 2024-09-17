package com.fdifrison.sdk.autoconfiguration;

import com.fdifrison.sdk.ShippingExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;


@AutoConfiguration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnProperty("sdk.client.url")
public class ClientAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ShippingExchange shippingExchange(@Value("${sdk.client.url}") String url,
                                             RestClient.Builder clientBuilder) {
        return buildExchange(url, clientBuilder, ShippingExchange.class);
    }


    private <E> E buildExchange(String baseurl, RestClient.Builder restClientBuilder, Class<E> clazz) {
        var restClient = restClientBuilder.baseUrl(baseurl).build();
        var adapter = RestClientAdapter.create(restClient);
        var factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(clazz);
    }

}
