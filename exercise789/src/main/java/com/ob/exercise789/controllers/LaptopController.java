package com.ob.exercise789.controllers;

import com.ob.exercise789.entities.Laptop;
import com.ob.exercise789.repositories.LaptopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {

    LaptopRepository repository;

    private final Logger log = LoggerFactory.getLogger( LaptopController.class );
    //This is to create logs

    public LaptopController( LaptopRepository repository ){
        this.repository = repository;
    }

    @GetMapping("/api/laptops")
    public List<Laptop> findAll(){
        return repository.findAll();
    }

    @GetMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> findOneById( @PathVariable Long id ){
        Optional<Laptop> laptopFinded = repository.findById( id );

        System.out.println( laptopFinded.get() );

        if( laptopFinded.isPresent()){
            return ResponseEntity.ok( laptopFinded.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/laptops")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop ){
        if ( laptop.getId() != null ){ //If the book has and ID that means the book already exist in db ( Trying to update )
            log.warn("Trying to create a book with an ID");
            return ResponseEntity.badRequest().build();
        }

        Laptop laptopSaved = repository.save( laptop );
        return ResponseEntity.ok( laptopSaved );
    }

    @PutMapping("/api/laptops")
    public ResponseEntity<Laptop> update( @RequestBody Laptop laptop){

        if ( laptop.getId() == null ){
            log.warn("Trying update a book that doesn't exist");
            return ResponseEntity.badRequest().build();
        }

        if ( !repository.existsById( laptop.getId() ) ){ //Check if the book exist in DB
            log.warn("Trying update a book that doesn't exist");
            return ResponseEntity.notFound().build();
        }

        Laptop laptopUpdated = repository.save( laptop );
        return ResponseEntity.ok( laptopUpdated ); //.create() to redirect user to new page

    }

    @DeleteMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> delete ( @PathVariable Long id ) {

        if ( !repository.existsById(id) ) {
            log.warn("Trying to delete a non exist book");
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);
        return ResponseEntity.noContent().build(); //This sends response 200

    }

    @DeleteMapping("/api/laptops")
    public ResponseEntity<Laptop> deleteAll () {

        log.info("REST request for delete all books");
        repository.deleteAll();
        return ResponseEntity.noContent().build();

    }

}
