package com.ob.exercise789.entities;

public class LaptopBuilder {

    Laptop laptop;

    public LaptopBuilder( String brand ){
        laptop = new Laptop();
        laptop.setId( null );
        laptop.setBrand( brand );
    }

    public LaptopBuilder setPrice( Float price ){
        laptop.setPrice( price );
        return this;
    }

    public LaptopBuilder setStorage ( Float storage ){
        laptop.setStorage( storage );
        return this;
    }

    public LaptopBuilder setRamQuantity ( Integer ramQuantity ){
        laptop.setRamQuantity( ramQuantity );
        return this;
    }

    public LaptopBuilder setProcessor( String processor ){
        laptop.setProcessor( processor );
        return this;
    }

    public LaptopBuilder setMotherboard( String motherboard ){
        laptop.setMotherboard( motherboard );
        return this;
    }

    public LaptopBuilder setGraphicCard( String graphicCard ){
        laptop.setGraphicCard( graphicCard );
        return this;
    }

    public Laptop build(){
        return laptop;
    }

}
