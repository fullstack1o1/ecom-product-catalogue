package net.samitkumar.ecom_product_catalogue.client;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@RequiredArgsConstructor
public class ClientConfiguration {
    final WebClient.Builder webClientBuilder;

    @Value("${spring.application.client.ecom-db-uri}")
    String baseUri;

    @Bean
    CategoryClient categoryClient() {
        webClientBuilder.baseUrl(baseUri);
        WebClientAdapter adapter = WebClientAdapter.create(webClientBuilder.build());
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(CategoryClient.class);
    }

    @Bean
    ProductClient productClient() {
        webClientBuilder.baseUrl(baseUri);
        WebClientAdapter adapter = WebClientAdapter.create(webClientBuilder.build());
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(ProductClient.class);
    }
}
