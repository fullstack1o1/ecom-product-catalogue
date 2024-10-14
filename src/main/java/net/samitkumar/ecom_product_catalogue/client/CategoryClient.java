package net.samitkumar.ecom_product_catalogue.client;

import net.samitkumar.ecom_product_catalogue.model.category.Root;
import org.springframework.http.MediaType;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import reactor.core.publisher.Mono;

@HttpExchange(url = "/categories", accept = MediaType.APPLICATION_JSON_VALUE, contentType = MediaType.APPLICATION_JSON_VALUE)
public interface CategoryClient {
    @GetExchange
    Mono<Root> allCategory();

}
