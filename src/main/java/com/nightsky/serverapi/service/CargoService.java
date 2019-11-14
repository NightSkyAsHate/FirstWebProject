package com.nightsky.serverapi.service;

import com.nightsky.serverapi.domain.Cargo;
import com.nightsky.serverapi.domain.User;
import com.nightsky.serverapi.domain.Weight;
import com.nightsky.serverapi.repository.CargoRepository;
import com.nightsky.serverapi.repository.Special_conditionRepository;
import com.nightsky.serverapi.repository.WeightRepository;
import org.springframework.stereotype.Service;

@Service
public class CargoService {
    private final WeightRepository weightRepository;
    private final Special_conditionRepository special_conditionRepository;
    private final CargoRepository cargoRepository;
    private final UserService userService;

    public CargoService(WeightRepository weightRepository, Special_conditionRepository special_conditionRepository, CargoRepository cargoRepository, UserService userService) {
        this.weightRepository = weightRepository;
        this.special_conditionRepository = special_conditionRepository;
        this.cargoRepository = cargoRepository;
        this.userService = userService;
    }

    public Cargo create(Cargo cargo){
        User user = userService.loadUserByUsername(cargo.getUser().getUsername());
        cargo.setUser(user);
        Weight weight = weightRepository.findByName(cargo.getWeight().getName());
        cargo.setWeight(weight);
        return cargoRepository.save(cargo);
    }
}
