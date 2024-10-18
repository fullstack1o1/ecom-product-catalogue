package net.samitkumar.ecom_product_catalogue.model.product;

/**
 "id": 2,
 "category": 2,
 "name": "Laptop",
 "description": "Laptop",
 "price": 1000,
 "inventory": {
 "id": 1,
 "productId": 2,
 "quantity": 10
 }
 */
public record Product(int id, int category, String name, String description, double price, Inventory inventory) {
    public record Inventory(int id, int productId, int quantity) {}
}

