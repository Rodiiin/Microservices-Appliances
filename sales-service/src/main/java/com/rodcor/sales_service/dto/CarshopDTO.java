package com.rodcor.sales_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
// Created a CarshopDTO to display more details total purchase and the product list for the designated SalesDTO
public class CarshopDTO {
    private Long idCarshop;
    private int totalPurchase;
    private List<ProductDTO> productList;
}
