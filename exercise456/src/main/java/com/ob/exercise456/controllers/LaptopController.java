package com.ob.exercise456.controllers;

import com.ob.exercise456.entities.Laptop;
import com.ob.exercise456.repositories.LaptopRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LaptopController {

    LaptopRepository repository;

    public LaptopController( LaptopRepository repository ){
        this.repository = repository;
    }

    @GetMapping("api/laptops")
    public List<Laptop> findAll(){
        return repository.findAll();
    }

    @PostMapping("api/laptops")
    public void create(@RequestBody Laptop laptop ){
        repository.save( laptop );
    }

}
