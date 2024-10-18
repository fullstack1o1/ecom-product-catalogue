package net.samitkumar.ecom_product_catalogue.client;

import net.samitkumar.ecom_product_catalogue.model.category.Category;
import net.samitkumar.ecom_product_catalogue.model.category.Root;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import reactor.core.publisher.Mono;

@HttpExchange(url = "/categories", accept = MediaType.APPLICATION_JSON_VALUE, contentType = MediaType.APPLICATION_JSON_VALUE)
public interface CategoryClient {

    @GetExchange
    Mono<Root> allCategory();

    @GetExchange("/{id}")
    Mono<Category> categoryById(@PathVariable("id") String id);

    @GetMapping("/search/findByName")
    Mono<Category> searchCategoryByName(@RequestParam("name") String name);

    @GetMapping("/search/findByNameContaining")
    Mono<Category> searchCategoryByNameContain(@RequestParam("name") String name);

    @PostMapping
    Mono<Category> createCategory(@RequestBody Category category);

    @PutMapping("/{id}")
    Mono<Category> updateCategory(@PathVariable("id") String id, @RequestBody Category category);

    @PatchMapping("/{id}")
    Mono<Category> patchCategory(@PathVariable("id") String id, @RequestBody Category category);

    @DeleteMapping("/{id}")
    Mono<Void> deleteCategory(@PathVariable("id") String id);
}
