package com.rodcor.carshop_service.controller;

import com.rodcor.carshop_service.dto.CarshopDTO;
import com.rodcor.carshop_service.model.Carshop;
import com.rodcor.carshop_service.service.ICarshopService;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carshop")
public class CarshopController {
    @Autowired
    ICarshopService carSer;


    // Save a carshop
    @PostMapping("/save")
    public String saveCarshop(@RequestBody Carshop carshop) {
        carSer.saveCarshop(carshop);
        return "Carshop saved";
    }

    //Get one Carshop
    @GetMapping("/get/{idCarshop}")
    public Carshop getOneCarshop(@PathVariable Long idCarshop){
        return carSer.getOneCarshop(idCarshop);
    }

    //Get all Carshop records
    @GetMapping("/getAllCarshop")
    public List<Carshop> getAllCarshop() {
        return carSer.getAllCarshop();
    }

    //Get one CarshopDTO
    @GetMapping("/getDTO/{idCarshop}")
    public CarshopDTO getOneCarshopDTO(@PathVariable Long idCarshop) {
        return carSer.getOneCarshopDTO(idCarshop);
    }

    //Get all CarshopDTO
    @GetMapping("/getAllDTO")
    public List<CarshopDTO> getAllCarshopDTO() {
        return carSer.gatAllCarshopDTO();
    }

    //Delete a Carshop
    @DeleteMapping("/delete/{idCarshop}")
    public String delteCarshop(@PathVariable Long idCarshop) {
        carSer.deleteCarshop(idCarshop);
        return "Carshop deleted";
    }

    //Edit Carshop
    @PutMapping("/update/{idCarshop}")
    public Carshop editCarshop(@PathVariable Long idCarshop,
                               @RequestBody Carshop carshop) {
        carSer.editCarshop(idCarshop,carshop);
        return carSer.getOneCarshop(idCarshop);

    }
}
