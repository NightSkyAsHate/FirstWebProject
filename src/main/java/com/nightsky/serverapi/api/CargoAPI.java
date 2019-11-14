package com.nightsky.serverapi.api;

import com.nightsky.serverapi.domain.Cargo;
import com.nightsky.serverapi.repository.CargoRepository;
import com.nightsky.serverapi.service.CargoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dbCargo")
public class CargoAPI {
    private final CargoService cargoService;

    @Autowired
    private CargoRepository cargoRepository;

    public CargoAPI(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @GetMapping
    public List<Cargo> getAllCargos() {
        return cargoRepository.findAll();
    }

    @GetMapping("{id}")
    public Cargo getCargoById(@PathVariable("id") Cargo cargo){
        return cargo;
    }

    @PostMapping()
    public Cargo createNewCargo(@RequestBody Cargo cargo){
        return cargoService.create(cargo);
    }

    @PutMapping("{id}")
    public Cargo modifyCargo(@PathVariable("id") Cargo cargoFromDb, @RequestBody Cargo cargo){
        BeanUtils.copyProperties(cargo, cargoFromDb, "id");
        return cargoRepository.save(cargoFromDb);
    }

    @DeleteMapping("{id}")
    public void deleteCargo(@PathVariable("id") Cargo cargo) {
        cargoRepository.delete(cargo);
    }
}
