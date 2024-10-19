package net.samitkumar.ecom_product_catalogue.router;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.samitkumar.ecom_product_catalogue.model.category.Category;
import net.samitkumar.ecom_product_catalogue.service.CategoryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ApplicationRouter {
    final CategoryService categoryService;

    @Bean
    RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions
                .route()
                .before((request) -> {
                    log.info("{} {}", request.path(), request.method());
                    return request;
                })
                .path("/category", builder -> builder
                        .GET("", this::allCategory)
                        .POST("", this::createCategory)
                        .GET("/search", this::search)
                        .GET("/{id}", this::categoryById)
                        .PUT("/{id}", this::updateCategory)
                        .PATCH("/{id}", this::partialUpdateCategory)
                        .DELETE("/{id}", this::deleteCategory)
                )
                .after(((serverRequest, serverResponse) -> {
                    log.info("{} {} {}", serverRequest.path(), serverRequest.method(), serverResponse.statusCode());
                    return serverResponse;
                }))
                .build();
    }

    private Mono<ServerResponse> deleteCategory(ServerRequest request) {
        var id = Long.parseLong(request.pathVariable("id"));
        return categoryService.deleteCategory(id)
                .then(ServerResponse.ok().build());
    }

    private Mono<ServerResponse> search(ServerRequest request) {
        var searchByName = request.queryParam("nameContain").orElse("");
        return categoryService.search(searchByName)
                .flatMap(ServerResponse.ok()::bodyValue);
    }

    private Mono<ServerResponse> partialUpdateCategory(ServerRequest request) {
        var id = Long.parseLong(request.pathVariable("id"));
        return request
                .bodyToMono(Category.class)
                .flatMap(category -> categoryService.partialUpdateCategory(id, category))
                .flatMap(ServerResponse.ok()::bodyValue);
    }

    private Mono<ServerResponse> updateCategory(ServerRequest request) {
        var id = Long.parseLong(request.pathVariable("id"));
        return request
                .bodyToMono(Category.class)
                .flatMap(category -> categoryService.updateCategory(id, category))
                .flatMap(ServerResponse.ok()::bodyValue);
    }

    private Mono<ServerResponse> createCategory(ServerRequest request) {
        return request.bodyToMono(Category.class)
                .flatMap(categoryService::createSingleCategory)
                .flatMap(ServerResponse.ok()::bodyValue);
    }

    private Mono<ServerResponse> categoryById(ServerRequest request) {
        var id = Long.parseLong(request.pathVariable("id"));
        return categoryService.getCategoryById(id)
                .flatMap(ServerResponse.ok()::bodyValue);
    }

    private Mono<ServerResponse> allCategory(ServerRequest request) {
        return categoryService.getCategories()
                .flatMap(ServerResponse.ok()::bodyValue);
    }
}
