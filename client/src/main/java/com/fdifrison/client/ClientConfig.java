package com.fdifrison.client;

import com.fdifrison.sdk.ShippingExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ClientConfig {

    @Bean
    public ShippingExchange shippingExchange(@Value("${server.url}") String url, RestClient.Builder clientBuilder) {
        return buildExchange(url, clientBuilder, ShippingExchange.class);
    }

    private <E> E buildExchange(String baseurl, RestClient.Builder restClientBuilder, Class<E> clazz) {
        var restClient = restClientBuilder.baseUrl(baseurl).build();
        var adapter = RestClientAdapter.create(restClient);
        var factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(clazz);
    }

}
