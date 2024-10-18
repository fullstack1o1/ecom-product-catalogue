package net.samitkumar.ecom_product_catalogue.model.product;

import net.samitkumar.ecom_product_catalogue.model.category.Category;

import java.util.List;

public record Embedded(List<Product> products) {
}
