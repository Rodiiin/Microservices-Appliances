package com.rodcor.carshop_service.service;

import com.netflix.discovery.converters.Auto;
import com.rodcor.carshop_service.dto.CarshopDTO;
import com.rodcor.carshop_service.dto.ProductDTO;
import com.rodcor.carshop_service.model.Carshop;
import com.rodcor.carshop_service.repository.ICarshopRepository;
import com.rodcor.carshop_service.repository.IProductsAPIClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarshopService implements ICarshopService {

    @Autowired
    ICarshopRepository carRep;

    // Autowiring the Feign client to communicate with the Product microservice
    @Autowired
    IProductsAPIClient productsAPIClient;

    @Override
    public void saveCarshop(Carshop carshop) {
        // Saving the Carshop object in the database
        carRep.save(carshop);
    }

    @Override
    public Carshop getOneCarshop(Long idCarshop) {
        // Fetching one Carshop object by its ID from the database or throwing an exception if not found
        return carRep.findById(idCarshop).orElseThrow(() -> new RuntimeException());
    }

    @Override
    public List<Carshop> getAllCarshop() {
        // Fetching all Carshop objects from the database
        return carRep.findAll();
    }

    @Override
    @CircuitBreaker(name = "product-service", fallbackMethod = "fallbackGetProductService")
    @Retry(name = "product-service")
    public CarshopDTO getOneCarshopDTO(Long idCarshop) {
        // Fetch the Carshop object using its ID
        Carshop carshop = carRep.findById(idCarshop).orElseThrow(() -> new RuntimeException());

        // Create a new CarshopDTO object
        CarshopDTO carshopDTO = new CarshopDTO();

        // List to hold ProductDTO objects
        List<ProductDTO> productDTOList = new ArrayList<>();

        // Fetching each product associated with the Carshop
        for (Long productId : carshop.getProductIdList()) {
            ProductDTO productDTO = productsAPIClient.getProductByIdProduct(productId);
            productDTOList.add(productDTO);
        }

        // Calculating the total price of all products in the carshop
        int totalPrice = 0;
        for (ProductDTO productDTO : productDTOList) {
            totalPrice += productDTO.getPriceProduct();
        }

        // Setting fields in the CarshopDTO object
        carshopDTO.setIdCarshop(carshop.getIdCarshop());
        carshopDTO.setTotalPurchase(totalPrice);
        carshopDTO.setProductList(productDTOList);

        // Returning the populated CarshopDTO object
        return carshopDTO;
    }

    // Fallback method to handle failures in the product-service calls
    public CarshopDTO fallbackGetProductService(Throwable throwable) {
        throw new RuntimeException("Product service is unavailable");
    }

    @Override
    public List<CarshopDTO> gatAllCarshopDTO() {

        // Fetch all Carshops
        List<Carshop> carshopList = carRep.findAll();

        // Prepare the list of CarshopDTOs
        List<CarshopDTO> carshopDTOList = new ArrayList<>();

        // Iterate through each Carshop
        for (Carshop carshop : carshopList) {

            // Create a new CarshopDTO object
            CarshopDTO carshopDTO = new CarshopDTO();
            // Create a new productDTOList to add into the CarshopDTO object
            List<ProductDTO> productDTOList = new ArrayList<>();

            // Fetch products associated with this Carshop
            for (Long productId : carshop.getProductIdList()) {
                ProductDTO productDTO = productsAPIClient.getProductByIdProduct(productId);
                productDTOList.add(productDTO);
            }

            // Calculate total price for the Carshop
            int totalPrice = 0;
            for (ProductDTO productDTO : productDTOList) {
                totalPrice += productDTO.getPriceProduct();
            }

            // Set the CarshopDTO fields
            carshopDTO.setIdCarshop(carshop.getIdCarshop());
            carshopDTO.setTotalPurchase(totalPrice);
            carshopDTO.setProductList(productDTOList);

            // Add the individual CarshopDTO to the list
            carshopDTOList.add(carshopDTO);
        }

        return carshopDTOList;
    }

    @Override
    public void deleteCarshop(Long idCarshop) {
        // Delete the Carshop object from the database using its ID
        carRep.deleteById(idCarshop);
    }

    @Override
    public void editCarshop(Long idCarshop, Carshop carshop) {
        // Fetch the existing Carshop object by its ID, update its product list, and save it back to the database
        Carshop existingCarshop = carRep.findById(idCarshop).orElseThrow(() -> new RuntimeException());
        existingCarshop.setProductIdList(carshop.getProductIdList());
        carRep.save(existingCarshop);
    }
}
