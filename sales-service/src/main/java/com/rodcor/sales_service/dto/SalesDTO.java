package com.rodcor.sales_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
// Created a SalesDTO to show more details than the class "Sales"
public class SalesDTO {
    private Long idSales;
    private Long idCarshop;
    private LocalDate dateSales;
    private int totalSale;
    private List<ProductDTO> productList;

}
