package com.rodcor.carshop_service.service;

import com.rodcor.carshop_service.CarshopServiceApplication;
import com.rodcor.carshop_service.dto.CarshopDTO;
import com.rodcor.carshop_service.model.Carshop;
import org.springframework.boot.actuate.autoconfigure.cassandra.CassandraReactiveHealthContributorAutoConfiguration;

import java.util.List;

public interface ICarshopService {

    //Create a carshop
    public void saveCarshop(Carshop carshop);

    //Get a Carshop
    public Carshop getOneCarshop(Long IdCarshop);

    //Get all Carshop records
    public List<Carshop> getAllCarshop();

    //Get a CarshopDTO
    public CarshopDTO getOneCarshopDTO(Long idCarshop);

    //Get all CarshopDTO
    public List<CarshopDTO> gatAllCarshopDTO();

    //Delete a Carshop
    public void deleteCarshop(Long idCarshop);

    //Edit a Carshop
    public void editCarshop(Long idCarshop, Carshop carshop);

}
