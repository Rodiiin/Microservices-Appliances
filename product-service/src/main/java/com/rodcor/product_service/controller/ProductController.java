package com.rodcor.product_service.controller;

import com.rodcor.product_service.model.Product;
import com.rodcor.product_service.service.IProductService;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    IProductService prodServ;


    @Value("${server.port}")
    private int serverPort;     // Injects the server port from application properties

    // Create product
    @PostMapping("/save")
    public String createProduct(@RequestBody Product product) {
        prodServ.saveProduct(product);
        return "Product saved";
    }

    // Obtain one product
    @GetMapping("/get/{idProduct}")
    public Product getProductById(@PathVariable Long idProduct) {
        System.out.println("---Running in the port " + serverPort);
        return prodServ.getOneProduct(idProduct);
    }

    // Obtain all products
    @GetMapping("/getAll")
    public List<Product> getAllProducts() {
        return prodServ.getAllProduct();
    }

    // Get product by Dni
    @GetMapping("/getByDni/{dniProduct}")
    public Product getByDni(@PathVariable String dniProduct) {
        return prodServ.getByDni(dniProduct);
    }

    // Delete a product
    @DeleteMapping("/delete/{idProduct}")
    public String deleteProduct(@PathVariable Long idProduct){
        prodServ.delteProduct(idProduct);
        return "Product deleted";
    }

    // Update a product
    @PutMapping("/update/{idProduct}")
    public Product updateProduct(@PathVariable Long idProduct,
                                 @RequestBody Product product) {
        prodServ.updateProduct(idProduct,product);
        Product updatedProduct = prodServ.getOneProduct(idProduct);
        return updatedProduct;
    }
}
