package net.samitkumar.ecom_product_catalogue.client;

import net.samitkumar.ecom_product_catalogue.model.category.Category;
import net.samitkumar.ecom_product_catalogue.model.category.Root;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@HttpExchange(url = "/categories", accept = MediaType.APPLICATION_JSON_VALUE, contentType = MediaType.APPLICATION_JSON_VALUE)
public interface CategoryClient {

    @GetExchange
    Mono<Root> allCategory();

    @GetExchange("/{id}")
    Mono<Category> categoryById(@PathVariable("id") Long id);

    @GetExchange("/search/findByName")
    Mono<Root> searchCategoryByName(@RequestParam("name") String name);

    @GetExchange("/search/findByNameContaining")
    Mono<Root> searchCategoryByNameContain(@RequestParam("name") String name);

    @PostExchange
    Mono<Category> createCategory(@RequestBody Category category);

    @PutExchange("/{id}")
    Mono<Category> updateCategory(@PathVariable("id") Long id, @RequestBody Category category);

    @PatchExchange("/{id}")
    Mono<Category> patchCategory(@PathVariable("id") Long id, @RequestBody Category category);

    @DeleteExchange("/{id}")
    Mono<Void> deleteCategory(@PathVariable("id") Long id);
}
