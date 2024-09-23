package com.rodcor.sales_service.service;

import com.rodcor.sales_service.dto.CarshopDTO;
import com.rodcor.sales_service.dto.ProductDTO;
import com.rodcor.sales_service.dto.SalesDTO;
import com.rodcor.sales_service.model.Sales;
import com.rodcor.sales_service.repository.ICarshopAPIClient;
import com.rodcor.sales_service.repository.ISalesRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalesService implements ISalesService{

    @Autowired
    ISalesRepository saleRep;

    // Feign client for communicating with the carshop-service
    @Autowired
    ICarshopAPIClient carshopAPIClient;

    @Override
    public void saveSale(Sales sales) {
        // Save the provided Sales object to the repository
        saleRep.save(sales);
    }

    @Override
    public Sales getOneSale(Long idSale) {
        // Retrieve a Sales object by its ID, returning null if not found
        return saleRep.findById(idSale).orElse(null);
    }

    @Override
    @CircuitBreaker(name = "carshop-service", fallbackMethod = "fallbackGetCarshopService")
    @Retry(name = "carshop-service")
    public SalesDTO getOneSaleDTO(Long idSales) {
        // Retrieve the Sales object using the provided ID
        Sales sales = saleRep.findById(idSales).orElse(null);
        SalesDTO salesDTO = new SalesDTO();

        // Fetch CarshopDTO associated with this Sales
        CarshopDTO carshopDTO =  carshopAPIClient.getCarshopDTOByIdCarshop(sales.getIdCarshop());

        // Set the fields of SalesDTO using data from Sales and CarshopDTO
        salesDTO.setIdSales(sales.getIdSales());
        salesDTO.setIdCarshop(carshopDTO.getIdCarshop());
        salesDTO.setDateSales(sales.getDateSales());
        salesDTO.setTotalSale(carshopDTO.getTotalPurchase());
        salesDTO.setProductList(carshopDTO.getProductList());

        return salesDTO;    // Return the populated SalesDTO
    }

    // Fallback method for handling failures when calling the carshop service
    public SalesDTO fallbackGetCarshopService(Throwable throwable) {
        // Return a default SalesDTO in case of failure
        return new SalesDTO(-1L, -1L, null, 0, null);
    }

    @Override
    public List<SalesDTO> getAllSaleDTO() {

        // Fetch all Sales
        List<Sales> salesList = saleRep.findAll();

        // Prepare the list of SalesDTOs
        List<SalesDTO> salesDTOList = new ArrayList<>();

        for (Sales sales : salesList) {

            // Create a new SalesDTO object
            SalesDTO salesDTO = new SalesDTO();

            // Fetch CarshopDTO associated with this Sales
            CarshopDTO carshopDTO = carshopAPIClient.getCarshopDTOByIdCarshop(sales.getIdCarshop());

            // Set the fields of SalesDTO
            salesDTO.setIdSales(sales.getIdSales());
            salesDTO.setIdCarshop(carshopDTO.getIdCarshop());
            salesDTO.setDateSales(sales.getDateSales());
            salesDTO.setTotalSale(carshopDTO.getTotalPurchase());
            salesDTO.setProductList(carshopDTO.getProductList());

            // Add this SalesDTO to the list
            salesDTOList.add(salesDTO);
        }

        return salesDTOList;
    }

    @Override
    public void deleteSale(Long idSales) {
        // Delete the Sales object by its ID from the repository
        saleRep.deleteById(idSales);
    }

    @Override
    public void editSale(Long idSales, Sales sales) {
        // Retrieve the existing Sales object by its ID
        Sales existingSales = saleRep.findById(idSales).orElse(null);
        // Update the fields of the existing Sales object
        existingSales.setIdCarshop(sales.getIdCarshop());
        existingSales.setDateSales(sales.getDateSales());

        // Save the updated Sales object back to the repository
        saleRep.save(existingSales);

    }
}
