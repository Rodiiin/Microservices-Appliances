package com.rodcor.carshop_service.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
//Create a DTO class for carshop to display all the products and the total purchase of that sale
public class CarshopDTO {
    private Long idCarshop;
    private int totalPurchase;
    private List<ProductDTO> productList;
}
