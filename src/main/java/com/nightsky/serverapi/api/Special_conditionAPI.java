package com.nightsky.serverapi.api;

import com.nightsky.serverapi.domain.Special_condition;
import com.nightsky.serverapi.repository.Special_conditionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("dbCondition")
public class Special_conditionAPI {

    @Autowired
    private Special_conditionRepository special_conditionRepository;

    @GetMapping
    public Iterable<Special_condition> special_condition(){
        return special_conditionRepository.findAll();
    }

    @GetMapping("{id}")
    public Special_condition getConditionById(@PathVariable("id") Special_condition special_condition){
        return special_condition;
    }

    @PostMapping
    public Special_condition createNewCondition(@RequestBody Special_condition special_condition){
        return special_conditionRepository.save(special_condition);
    }

    @PutMapping("{conditionId}")
    public Special_condition modifyCondition(@PathVariable("id") Special_condition special_conditionFromDb, Special_condition special_condition){
        BeanUtils.copyProperties(special_condition, special_conditionFromDb, "id");
        return special_conditionRepository.save(special_conditionFromDb);
    }

    @DeleteMapping("{conditionId}")
    public void deleteCondition(@PathVariable("id") Special_condition special_condition){
        special_conditionRepository.delete(special_condition);
    }
}
