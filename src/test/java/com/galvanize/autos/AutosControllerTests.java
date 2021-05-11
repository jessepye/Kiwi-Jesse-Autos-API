package com.galvanize.autos;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AutosController.class)
public class AutosControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AutosService autosService;

    List<Automobile> autosList;

    ObjectMapper objectMapper;


    @BeforeEach
    void setup() {
        autosList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Automobile automobile = new Automobile(i, "Ford", "Mustang",
                    1967 + i, "ABC" + 12 + i);
            autosList.add(automobile);
        }

        objectMapper = new ObjectMapper();
    }

    @Test
    void getAutos_noArgs_exists_returnsAutosList() throws Exception {
        when(autosService.getAutos()).thenReturn(new AutosList(autosList));

        mockMvc.perform(get("/autos"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.automobiles", hasSize(5)));
    }

    @Test
    void getAutos_noArgs_none_returnsNoContent() throws Exception {
        when(autosService.getAutos()).thenReturn(new AutosList());

        mockMvc.perform(get("/autos"))
                .andExpect(status().isNoContent());
    }

    @Test
    void getAutos_args_returnsAutosList() throws Exception {
        when(autosService.getAutos(anyString(), anyString())).thenReturn(new AutosList(autosList));

        mockMvc.perform(get("/autos?color=red&make=ford"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.automobiles", hasSize(5)));
    }

    @Test
    void postAuto_valid_returnsAuto() throws Exception {
        Automobile automobile = new Automobile(4, "Toyota", "Supra", 1995, "ABC321");

        when(autosService.addAuto(any(Automobile.class))).thenReturn(automobile);

        mockMvc.perform(post("/autos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(automobile)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.make").value("Toyota"));
    }

    @Test
    void postAuto_badRequest_returns400() throws Exception {
        Automobile automobile = new Automobile(4, "Toyota", "Supra", 1995, "ABC321");

        when(autosService.addAuto(any(Automobile.class))).thenThrow(InvalidAutoExcepton.class);

        mockMvc.perform(post("/autos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(automobile)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getAuto_withVin_returnsAuto() throws Exception {
        Automobile automobile = new Automobile(4, "Toyota", "Supra", 1995, "ABC321");

        when(autosService.getAuto(anyString())).thenReturn(automobile);

        mockMvc.perform(get("/autos/"+automobile.getVin()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("vin").value(automobile.getVin()));
    }

    @Test
    void getAuto_vinNotFound_returns204() throws Exception {
        Automobile automobile = new Automobile(4, "Toyota", "Supra", 1995, "ABC321");

        when(autosService.getAuto(anyString())).thenReturn(null);

        mockMvc.perform(get("/autos/"+automobile.getVin()))
                .andExpect(status().isNoContent());
    }

// PATCH /autos/{vin} - updates owner or color of vehicle
    // returns updated vehicle (200)
    // returns 204 if vehicle not found
    // returns 400 if given a bad request
// DELETE /autos/{vin} - delete an automobile by its vin
    // returns 202 when delete request is accepted
    // returns 204 if vehicle is not found
}
