package com.galvanize.autos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AutosController.class)
public class AutosControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AutosService autosService;

    List<Automobile> autosList;

    @BeforeEach
    void setup() {
        autosList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Automobile automobile = new Automobile(i, "Ford", "Mustang",
                    1967 + i, "ABC" + 12 + i);
            autosList.add(automobile);
        }
    }

    @Test
    void getAutos_noArgs_exists_returnsAutosList() throws Exception {
        when(autosService.getAutos()).thenReturn(new AutosList(autosList));

        mockMvc.perform(get("/autos"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.automobiles", hasSize(5)));
    }

    // GET: /autos no autos in database returns 204 no content
    @Test
    void getAutos_noArgs_none_returnsNoContent() throws Exception {
        when(autosService.getAutos()).thenReturn(new AutosList());

        mockMvc.perform(get("/autos"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }


    @Test
    void getAutos_args_returnsAutosList() throws Exception {
        when(autosService.getAutos(anyString(), anyString())).thenReturn(new AutosList(autosList));

        mockMvc.perform(get("/autos?color=red&make=ford"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.automobiles", hasSize(5)));
    }

    // GET: /autos?color=red returns all autos where color=red
    // GET: /autos?make=ford returns all autos where make=ford
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
