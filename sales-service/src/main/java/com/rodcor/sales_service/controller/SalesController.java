package com.rodcor.sales_service.controller;

import com.rodcor.sales_service.dto.SalesDTO;
import com.rodcor.sales_service.model.Sales;
import com.rodcor.sales_service.service.ISalesService;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SalesController {

    // Autowiring the Sales service to access its methods
    @Autowired
    ISalesService salSer;

    // Endpoint to save a new Sale
    @PostMapping("/save")
    public String saveSale(@RequestBody Sales sales) {
        salSer.saveSale(sales);
        return "Sale saved";
    }

    // Endpoint to fetch one Sale by its ID
    @GetMapping("/getOneSale/{idSale}")
    public Sales getOneSale(@PathVariable Long idSale) {
        return salSer.getOneSale(idSale);
    }

    // Endpoint to fetch one SaleDTO by its ID, which includes more details (like products)
    @GetMapping("/getOneSaleDTO/{idSale}")
    public SalesDTO getOneSaleDTO(@PathVariable Long idSale) {
        return salSer.getOneSaleDTO(idSale);
    }

    // Endpoint to fetch all sales as a list of SalesDTOs
    @GetMapping("/getAllSalesDTO")
    public List<SalesDTO> getAllSaleDTO() {
        return salSer.getAllSaleDTO();
    }

    // Endpoint to delete a Sale by its ID
    @DeleteMapping("/delete/{idSale}")
    public String deleteSale(@PathVariable Long idSales) {
        salSer.deleteSale(idSales);
        return "Sale deleted";
    }

    // Endpoint to edit an existing Sale by its ID
    @PutMapping("/edit/{idSale}")
    public Sales editSale(@PathVariable Long idSale,
                          @RequestBody Sales sales) {

        // Call the service to edit the Sale
        salSer.editSale(idSale, sales);
        // Return the updated Sale object
        return salSer.getOneSale(idSale);
    }
}
