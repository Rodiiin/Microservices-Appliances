package com.rodcor.product_service.repository;

import com.rodcor.product_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoRepository extends JpaRepository<Product,Long> {

    // This method retrieves a Product entity based on the provided DNI (identifier).
    @Query("SELECT p FROM Product p WHERE p.dniProduct = :dni ")
    Product findByDni(@Param("dni") String dni);
}
