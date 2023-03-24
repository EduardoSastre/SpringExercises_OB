package com.ob.exercise101112;

import com.ob.exercise101112.entities.Laptop;
import com.ob.exercise101112.entities.LaptopBuilder;
import com.ob.exercise101112.repositories.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LaptopsMain {

	public static void main(String[] args) {
		//Spring container
		ApplicationContext context = SpringApplication.run(LaptopsMain.class, args);

		//Get beans
		LaptopRepository laptopRepository = ( LaptopRepository ) context.getBean( LaptopRepository.class ); //Option 2: class

		//Generate new laptops and save them to DB
		Laptop laptop = new LaptopBuilder( "Msi" )
				.setGraphicCard("RTX 3060 ti")
				.setMotherboard("MSI B560 Tornado")
				.setProcessor("Intel I5 11400")
				.setPrice(20000f)
				.setStorage(1f)
				.setRamQuantity(16)
				.build();

		Laptop laptop2 = new LaptopBuilder( "Asus" )
				.setGraphicCard("RTX 4070 ti")
				.setMotherboard("Asus B760 ROG STRIX")
				.setProcessor("Intel I5 13600k")
				.setPrice(40000f)
				.setStorage(1f)
				.setRamQuantity(32)
				.build();

		laptopRepository.save( laptop );
		laptopRepository.save( laptop2 );


	}

}
