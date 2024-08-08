package net.samitkumar.ecom_product_catalogue;

import org.springframework.boot.SpringApplication;

public class TestEcomProductCatalogueApplication {

	public static void main(String[] args) {
		SpringApplication.from(EcomProductCatalogueApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
