package com.rodcor.carshop_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//Create a product DTO to store the products for each shopping cart
public class ProductDTO {
    private Long idProduct;
    private String dniProduct;
    private String nameProduct;
    private String brandProduct;
    private int priceProduct;
}
