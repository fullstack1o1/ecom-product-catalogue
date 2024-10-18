package net.samitkumar.ecom_product_catalogue.client;

import net.samitkumar.ecom_product_catalogue.model.product.Product;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.HttpExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@HttpExchange(url = "/products", accept = MediaType.APPLICATION_JSON_VALUE, contentType = MediaType.APPLICATION_JSON_VALUE)
public interface ProductClient {
    @GetMapping
    Mono<List<Product>> getProducts();

    @GetMapping("/{id}")
    Mono<Product> getProductById(@PathVariable("id") String id);

    @GetMapping("/search/findByCategory")
    Mono<List<Product>> getProductsByCategory(@RequestParam("categoryId") String categoryId);

    @PostMapping
    Mono<Product> createProduct(@RequestBody Product product);

    @PutMapping
    Mono<Product> updateProduct(@RequestBody Product product);

    @DeleteMapping
    Mono<Product> deleteProductById(@PathVariable("id") String id);
}
