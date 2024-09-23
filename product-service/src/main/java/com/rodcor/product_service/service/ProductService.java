package com.rodcor.product_service.service;

import com.rodcor.product_service.model.Product;
import com.rodcor.product_service.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{

    @Autowired
    IProductoRepository proRepo;

    @Override
    public void saveProduct(Product product) {
        // Save the given product to the repository
        proRepo.save(product);
    }

    @Override
    public Product getOneProduct(Long idProduct) {
        // Retrieve a product by its ID, throwing an exception if not found
        return proRepo.findById(idProduct).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    @Override
    public List<Product> getAllProduct() {
        // Return a list of all products from the repository
        return proRepo.findAll();
    }

    @Override
    public Product getByDni(String dniProduct) {
        // Retrieve a product by its DNI (identifier)
        return proRepo.findByDni(dniProduct);
    }

    @Override
    public void delteProduct(Long idProduct) {
        // Delete a product by its ID from the repository
        proRepo.deleteById(idProduct);
    }

    @Override
    public void updateProduct(Long idProduct, Product product) {
        // Retrieve the existing product by its ID, throwing an exception if not found
        Product existingProduct = proRepo.findById(idProduct).orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Update the fields of the existing product with new values
        existingProduct.setDniProduct(product.getDniProduct());
        existingProduct.setNameProduct(product.getNameProduct());
        existingProduct.setBrandProduct(product.getBrandProduct());
        existingProduct.setPriceProduct(product.getPriceProduct());

        // Save the updated product back to the repository
        proRepo.save(existingProduct);
    }
}
