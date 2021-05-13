package com.galvanize.autos;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AutosApiApplicationTests {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    AutosRepository autosRepository;

    List<Automobile> automobiles;
    Random random;

    @BeforeEach
    void setUp() {
        String[] make = {"Ford", "Chevrolet", "Dodge", "BMW", "Audi", "Mercedes", "Lexus", "Infiniti", "Acura"};
        String[] model = {"Compact", "Sedan", "SUV", "Truck", "Coupe"};

        automobiles = new ArrayList<>();
        random = new Random();

        for (int i = 0; i < 50; i++) {
            automobiles.add(new Automobile(make[random.nextInt(9)], model[random.nextInt(5)],
                    1967 + i, "VIN" + (i * 7)));
        }

        autosRepository.saveAll(automobiles);
    }

    @AfterEach
    void tearDown() {
        autosRepository.deleteAll();
    }

    @Test
	void contextLoads() {
	}

    @Test
    void getAutos_noArgs_exists_returnsAutosList() {
        ResponseEntity<AutosList> response = testRestTemplate.getForEntity("/autos", AutosList.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().isEmpty()).isFalse();

//        for (Automobile auto: response.getBody().getAutomobiles()) {
//            System.out.println(auto);
//        }
    }

    @Test
    void getAutos_noArgs_none_returnsNoContent() {
        autosRepository.deleteAll();

        ResponseEntity<AutosList> response = testRestTemplate.getForEntity("/autos", AutosList.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isNull();
    }

//    @Test
//    void postAutos_validArg_returnsAuto() {
//        Automobile newAuto = new Automobile("Toyota", "Corolla", 2014, "VINNY");
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
//        ResponseEntity<AutosList> response = testRestTemplate.postForEntity("/autos", request, AutosList.class);
//    }
}
