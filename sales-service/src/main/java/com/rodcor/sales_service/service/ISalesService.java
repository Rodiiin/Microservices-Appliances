package com.rodcor.sales_service.service;

import com.rodcor.sales_service.dto.SalesDTO;
import com.rodcor.sales_service.model.Sales;

import java.util.List;

public interface ISalesService {

    //Create a sale
    public void saveSale(Sales sales);

    //Get a sale
    public Sales getOneSale(Long idSale);

    //Get a saleDTO
    public SalesDTO getOneSaleDTO(Long idSales);

    //Get all saleDTO
    public List<SalesDTO> getAllSaleDTO();

    //Delete a sale
    public void deleteSale(Long idSales);

    //Edit a sale
    public void editSale(Long idSales, Sales sales);



}
