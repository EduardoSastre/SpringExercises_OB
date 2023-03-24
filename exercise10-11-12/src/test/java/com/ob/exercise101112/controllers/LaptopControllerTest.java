package com.ob.exercise101112.controllers;

import com.ob.exercise101112.entities.Laptop;
import com.ob.exercise101112.entities.LaptopBuilder;
import com.ob.exercise101112.repositories.LaptopRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort //Spring inyect the port in this variable
    private int port;

    @Autowired
    LaptopRepository laptopRepository;


    @BeforeEach
    void setUp() {
        //Create a connection with a port
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate( restTemplateBuilder );

        //Create books in DB
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

    @Test
    void findAll() {

        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/api/laptops", Laptop[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());


        List<Laptop> laptops = Arrays.asList( response.getBody() ); //Transform Array to List
        assertEquals(2, laptops.size());

    }

    @Test
    void findOneById() {

        ResponseEntity<Laptop> response = testRestTemplate.getForEntity("/api/laptops/1", Laptop.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        Laptop laptopFinded = response.getBody();
        assertEquals( "Msi", laptopFinded.getBrand() );

    }


    @Test
    void create() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); //This means that we will send a JSON
        headers.setAccept(Arrays.asList( MediaType.APPLICATION_JSON )); //This means that we will accept JSON
        String json = """
                 {
                         "brand": "Laptop creada desde Test",
                         "price": 22000.0,
                         "storage": 0.5,
                         "ramQuantity": 12,
                         "processor": "Intel I5 12400F",
                         "motherboard": "MSI B560 Tornado",
                         "graphicCard": "RTX 3070 ti"
                     }
                """;

        HttpEntity<String> request = new HttpEntity<>(json,headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops", HttpMethod.POST, request, Laptop.class);

        Laptop result = response.getBody();

        assertEquals( 3, result.getId() );
        assertEquals("Laptop creada desde Test", result.getBrand());

    }



    @Test
    void update() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); //This means that we will send a JSON
        headers.setAccept(Arrays.asList( MediaType.APPLICATION_JSON )); //This means that we will accept JSON
        String json = """
                {
                        "id": 1,
                        "brand": "Laptop actualizada en update test",
                        "price": 22000.0,
                        "storage": 0.5,
                        "ramQuantity": 12,
                        "processor": "Intel I5 12400F",
                        "motherboard": "MSI B560 Tornado",
                        "graphicCard": "RTX 3070 ti"
                    }
                """;

        HttpEntity<String> request = new HttpEntity<>(json,headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops", HttpMethod.PUT, request, Laptop.class);

        Laptop result = response.getBody();

        assertEquals(1, result.getId());
        assertEquals("Laptop actualizada en update test", result.getBrand());

    }

    @Test
    void delete() {

        testRestTemplate.delete("/api/laptops/1");
        assertEquals(Optional.empty(), laptopRepository.findById(1L));

    }

    @Test
    void deleteAll() {

        testRestTemplate.delete("/api/laptops");
        assertEquals(0, laptopRepository.count());

    }
}