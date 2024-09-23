package com.rodcor.carshop_service.repository;

import com.rodcor.carshop_service.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



// FeignClient interface for communicating with the "product-service" microservice
@FeignClient(name = "product-service")
public interface IProductsAPIClient {

    // This method is used to fetch a ProductDTO by its product ID from the product-service
    // The @GetMapping annotation defines the endpoint to be called on the product-service
    @GetMapping("/product/get/{idProduct}")
    public ProductDTO getProductByIdProduct(@PathVariable("idProduct") Long idProduct);

}



