package com.rodcor.sales_service.repository;

import com.rodcor.sales_service.dto.CarshopDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// FeignClient interface for communicating with the "carshop-service" microservice
@FeignClient(name = "carshop-service")
public interface ICarshopAPIClient {

    // This method is used to fetch a CarshopDTO by its Carshop ID from the carshop-service
    // The @GetMapping annotation defines the endpoint to be called on the carshop-service
    @GetMapping("/carshop/getDTO/{idCarshop}")
    public CarshopDTO getCarshopDTOByIdCarshop(@PathVariable Long idCarshop);
}
