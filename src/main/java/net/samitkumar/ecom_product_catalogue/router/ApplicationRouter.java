package net.samitkumar.ecom_product_catalogue.router;

import lombok.RequiredArgsConstructor;
import net.samitkumar.ecom_product_catalogue.client.CategoryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Configuration
@RequiredArgsConstructor
public class ApplicationRouter {
    final CategoryClient categoryClient;

    @Bean
    RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions
                .route()
                .path("/category", builder -> builder
                        .GET("", this::allCategory)
                )
                .build();
    }

    private Mono<ServerResponse> allCategory(ServerRequest request) {
        return categoryClient
                .allCategory()
                .map(root -> root._embedded().categories())
                .flatMap(ServerResponse.ok()::bodyValue);
    }
}
