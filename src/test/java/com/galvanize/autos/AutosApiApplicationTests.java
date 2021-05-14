package com.galvanize.autos;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

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
    Automobile automobile;

    @BeforeEach
    void setUp() {
        String[] make = {"Ford", "Chevrolet", "Dodge", "BMW", "Audi", "Mercedes", "Lexus", "Infiniti", "Acura"};
        String[] model = {"Compact", "Sedan", "SUV", "Truck", "Coupe"};

        automobiles = new ArrayList<>();
        random = new Random();
        automobile = new Automobile("Toyota", "Corolla", 2014, "VINNY");

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
    }

    @Test
    void getAutos_noArgs_none_returnsNoContent() {
        autosRepository.deleteAll();

        ResponseEntity<AutosList> response = testRestTemplate.getForEntity("/autos", AutosList.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isNull();
    }

    @Test
    void postAutos_validArg_returnsAuto() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<Automobile> request = new HttpEntity<>(automobile, headers);

        ResponseEntity<Automobile> response = testRestTemplate.postForEntity("/autos", request, Automobile.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getVin()).isEqualTo(automobile.getVin());
    }

    @Test
    void postAutos_badRequest_returns400() {
        automobile.setVin("$BADVIN");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<Automobile> request = new HttpEntity<>(new Automobile(), headers);

        ResponseEntity<Automobile> response = testRestTemplate.postForEntity("/autos", request, Automobile.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void getAutos_byVin_returnsAuto() {
        ResponseEntity<Automobile> response = testRestTemplate.getForEntity("/autos/VIN7", Automobile.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getVin()).isEqualTo(autosRepository.findByVin("VIN7").get().getVin());
    }

    @Test
    void getAutos_byVin_noneExists_returns204() {
        ResponseEntity<Automobile> response = testRestTemplate.getForEntity("/autos/VIN6", Automobile.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void updateAutos_withObject_returnsAuto() {
        automobile = new Automobile("Toyota", "Corolla", 2014, "VIN7");
        automobile.setPrice(7654300);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<Automobile> request = new HttpEntity<>(automobile, headers);

        ResponseEntity<Automobile> response = testRestTemplate.exchange("/autos/VIN7", HttpMethod.PATCH,
                request, Automobile.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getPrice()).isEqualTo(7654300);

    }

    @Test
    void updateAutos_notFound_returns204() {
        automobile = new Automobile("Toyota", "Corolla", 2014, "VIN7");
        automobile.setPrice(7654300);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<Automobile> request = new HttpEntity<>(automobile, headers);

        ResponseEntity<Automobile> response = testRestTemplate.exchange("/autos/VIN6", HttpMethod.PATCH,
                request, Automobile.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void updateAutos_badRequest_returns400() {
        automobile = new Automobile("Toyota", "Corolla", 2014, "VIN7");
        automobile.setPrice(7654300);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<Automobile> request = new HttpEntity<>(automobile, headers);

        ResponseEntity<Automobile> response = testRestTemplate.exchange("/autos/$VIN6", HttpMethod.PATCH,
                request, Automobile.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void deleteAutos_accepted_returns202() {
        ResponseEntity<Void> response = testRestTemplate.exchange("/autos/VIN7",
                HttpMethod.DELETE, new HttpEntity<>(new HttpHeaders()), Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);

    }

    @Test
    void deleteAutos_notFound_returns204() {
        ResponseEntity<Void> response = testRestTemplate.exchange("/autos/$Vin7",
                HttpMethod.DELETE, new HttpEntity<>(new HttpHeaders()), Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }
}
