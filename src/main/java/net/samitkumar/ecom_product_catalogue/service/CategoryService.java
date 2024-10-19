package net.samitkumar.ecom_product_catalogue.service;

import lombok.RequiredArgsConstructor;
import net.samitkumar.ecom_product_catalogue.client.CategoryClient;
import net.samitkumar.ecom_product_catalogue.exception.DataNotFoundException;
import net.samitkumar.ecom_product_catalogue.model.category.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    final CategoryClient categoryClient;

    public Mono<List<Category>> getCategories() {
        return categoryClient
                .allCategory()
                .map(root -> root._embedded().categories());
    }

    public Mono<Category> getCategoryById(Long id) {
        return categoryClient
                .categoryById(id)
                .onErrorResume(WebClientResponseException.class, ex -> {
                    if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new DataNotFoundException("Category with id " + id + " not found"));
                    }
                    return Mono.error(ex); // Propagate other errors
                });
    }

    public Mono<Category> createSingleCategory(Category category) {
        return categoryClient.createCategory(category);
    }

    public Mono<Category> updateCategory(Long id, Category category) {
        return getCategoryById(id)
                .map(category1 -> new Category(category1.id(), category.name(), category.description(), category.products()))
                .flatMap(category2 -> categoryClient.updateCategory(category2.id(), category2));
    }

    public Mono<Category> partialUpdateCategory(Long id, Category category) {
        return getCategoryById(id)
                .flatMap(category1 -> categoryClient.patchCategory(category1.id(), new Category(category1.id(), category.name(), category.description(), category.products())));
    }

    public Mono<List<Category>> search(String nameContain) {
        return categoryClient
                .searchCategoryByNameContain(nameContain)
                .map(root -> root._embedded().categories());
    }

    public Mono<Void> deleteCategory(long id) {
        return categoryClient.deleteCategory(id);
    }
}
