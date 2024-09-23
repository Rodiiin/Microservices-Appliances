package com.rodcor.carshop_service.repository;

import com.rodcor.carshop_service.model.Carshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarshopRepository extends JpaRepository<Carshop, Long> {

}
