package com.nightsky.serverapi.api;

import com.nightsky.serverapi.domain.Weight;
import com.nightsky.serverapi.repository.WeightRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("dbWeight")
public final class WeightAPI {

    @Autowired
    private WeightRepository weightRepository;

    @GetMapping
    public Iterable<Weight> getAllWeights(){
        return weightRepository.findAll();
    }

    @GetMapping("{id}")
    public Weight getWeightById(@PathVariable("id") Weight weight){
        return weight;
    }

    @PostMapping()
    public Weight createNewWeight(@RequestBody Weight weight){
        return weightRepository.save(weight);
    }

    @PutMapping("{weight}")
    public Weight modifyWeight(@PathVariable("id") Weight weightFromDb, Weight weight){
        BeanUtils.copyProperties(weight, weightFromDb, "id");
        return weightRepository.save(weightFromDb);
    }

    @DeleteMapping("/{weightId}")
    public void deleteWeight(@PathVariable("id") Weight weight){
        weightRepository.delete(weight);
    }
}
