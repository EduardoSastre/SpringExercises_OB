package com.ob.exercise101112.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private Float price;
    private Float storage;
    private Integer ramQuantity;
    private String processor;
    private String motherboard;
    private String graphicCard;

    public Laptop (){

    }

    public Laptop(Long id, String brand, Float price, Float storage, Integer ramQuantity, String processor, String motherboard, String graphicCard) {
        this.id = id;
        this.brand = brand;
        this.price = price;
        this.storage = storage;
        this.ramQuantity = ramQuantity;
        this.processor = processor;
        this.motherboard = motherboard;
        this.graphicCard = graphicCard;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getStorage() {
        return storage;
    }

    public void setStorage(Float storage) {
        this.storage = storage;
    }

    public Integer getRamQuantity() {
        return ramQuantity;
    }

    public void setRamQuantity(Integer ramQuantity) {
        this.ramQuantity = ramQuantity;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(String motherboard) {
        this.motherboard = motherboard;
    }

    public String getGraphicCard() {
        return graphicCard;
    }

    public void setGraphicCard(String graphicCard) {
        this.graphicCard = graphicCard;
    }
}
