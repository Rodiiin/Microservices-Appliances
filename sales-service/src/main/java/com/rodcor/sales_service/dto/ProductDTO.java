package com.rodcor.sales_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
// Created a ProductDTO to display the products in CarshopDTO
public class ProductDTO {
    private Long idProduct;
    private String dniProduct;
    private String nameProduct;
    private String brandProduct;
    private int priceProduct;
}
