package com.workintech.s17d1.controller;

import com.workintech.s17d1.entity.Animal;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/workintech/animal/")
public class AnimalController {
    private Map<Integer,Animal> animals;

    @PostConstruct
    public void loadAll(){
        this.animals = new HashMap<>();
        this.animals.put(1,new Animal(1,"Maymun"));

    }

    @GetMapping
    public List<Animal> getAnimals(){
        return new ArrayList<>(this.animals.values());
    }

    @GetMapping(path="{id}")
    public Animal getAnimal(@PathVariable("id") Integer id){
        return this.animals.get(id);
    }

    @PostMapping(path="{id}")
    public void addAnimal(@RequestBody Animal animal){
        this.animals.put(animal.getId(),animal);
    }

    @PutMapping(path="{id}")
    public Animal updateAnimal(@PathVariable("id") Integer existingRecorId, @RequestBody Animal animal){
        this.animals.replace(existingRecorId,animal);
        return this.animals.get(existingRecorId);
    }

    @DeleteMapping(path="{id}")
    public void deleteAnimal(@PathVariable("id") Integer id){
        this.animals.remove(id);
    }
}
