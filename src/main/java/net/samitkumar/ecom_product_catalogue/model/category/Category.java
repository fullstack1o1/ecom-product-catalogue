package net.samitkumar.ecom_product_catalogue.model.category;

import net.samitkumar.ecom_product_catalogue.model.product.Product;

import java.util.List;

public record Category(int id, String name, String description, List<Product> products) {
}
