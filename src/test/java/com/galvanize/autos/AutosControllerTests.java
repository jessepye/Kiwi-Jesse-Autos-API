package com.galvanize.autos;

import org.junit.jupiter.api.Test;
import org.mockito.internal.hamcrest.HamcrestArgumentMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AutosController.class)
public class AutosControllerTests {
    @Autowired
    MockMvc mockMvc;

// GET /autos:
    // GET: /autos no arguments returns all autos
    @Test
    void getAutos_noArgs_exists_returnsAutosList() throws Exception {
        // Arrange

        // Act
        mockMvc.perform(MockMvcRequestBuilders.get("/autos"))

        // Assert
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.automobiles", hasSize(5)));
    }
    // GET: /autos no autos in database returns 204 no content
    // GET: /autos?color=red returns all autos where color=red
    // GET: /autos?make=ford returns all autos where make=ford
    // GET: /autos?make=chevrolet&color=blue returns all blue chevrolets
// POST /autos:
    // adds an automobile to the database, returns newly created auto
    // returns error message (400) upon a bad request
// GET /autos/{vin}
    // Returns the requested automobile
    // Returns NoContent (204) if the vin is not found
// PATCH /autos/{vin} - updates owner or color of vehicle
    // returns updated vehicle (200)
    // returns 204 if vehicle not found
    // returns 400 if given a bad request
// DELETE /autos/{vin} - delete an automobile by its vin
    // returns 202 when delete request is accepted
    // returns 204 if vehicle is not found
}
