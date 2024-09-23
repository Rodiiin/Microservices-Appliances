package com.rodcor.product_service.service;

import com.rodcor.product_service.model.Product;

import java.util.List;

public interface IProductService {

    //Create a product
    public void saveProduct(Product product);

    //Get a product
    public Product getOneProduct(Long idProduct);

    //Get all products
    public List<Product> getAllProduct();

    //Get product by dni
    public Product getByDni(String dniProduct);

    //Delete a product
    public void delteProduct(Long idProduct);

    //Update a product
    public void updateProduct(Long idProduct, Product product);

}
