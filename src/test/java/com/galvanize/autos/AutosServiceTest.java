package com.galvanize.autos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AutosServiceTest {

    AutosService autosService;

    @Mock
    AutosRepository autosRepository;

    Automobile automobile;

    @BeforeEach
    void setup() {
        autosService = new AutosService(autosRepository);
        automobile = new Automobile(4, "Toyota", "Supra", 1995, "ABC321");
    }

    @Test
    void getAutos_hasContent_returnsList() {
        when(autosRepository.findAll()).thenReturn(Arrays.asList(automobile));
        AutosList autosList = autosService.getAutos();
        assertThat(autosList).isNotNull();
        assertThat(autosList.isEmpty()).isFalse();
        assertThat(autosList.getCount()).isEqualTo(1);
    }

    @Test
    void getAutos_noContent_returnsEmptyList() {
        when(autosRepository.findAll()).thenReturn(new ArrayList<Automobile>());
        AutosList autosList = autosService.getAutos();
        assertThat(autosList).isNotNull();
        assertThat(autosList.isEmpty()).isTrue();
    }

    @Test
    void getAutos_search_hasContent_returnsList() {
        automobile.setColor("White");
        when(autosRepository.findByColorContainsAndMakeContains(anyString(), anyString()))
                .thenReturn(Arrays.asList(automobile));
        AutosList autosList = autosService.getAutos("White", "Toyota");
        assertThat(autosList).isNotNull();
        assertThat(autosList.isEmpty()).isFalse();
        assertThat(autosList.getAutomobiles().get(0).getColor()).isEqualTo("White");
        assertThat(autosList.getAutomobiles().get(0).getMake()).isEqualTo("Toyota");
    }

    @Test
    void getAutos_search_noContent_returnsList() {
        automobile.setColor("White");
        when(autosRepository.findByColorContainsAndMakeContains(anyString(), anyString()))
                .thenReturn(new ArrayList<Automobile>());
        AutosList autosList = autosService.getAutos("White", "Toyota");
        assertThat(autosList).isNotNull();
        assertThat(autosList.isEmpty()).isTrue();
    }

    @Test
    void addAuto_valid_returnsAuto() {
        when(autosRepository.save(any(Automobile.class))).thenReturn(automobile);
        Automobile savedAuto = autosService.addAuto(automobile);
        assertThat(savedAuto).isNotNull();
        assertThat(savedAuto.getVin()).isEqualTo(automobile.getVin());
    }

    @Test
    void addAuto_invalid_returnsAuto() {
        when(autosRepository.save(any(Automobile.class))).thenThrow(InvalidAutoExcepton.class);

        assertThatThrownBy(() -> {
            autosService.addAuto(automobile);
        }).isInstanceOf(InvalidAutoExcepton.class);
    }

    @Test
    void getAuto_byVin_returnsAuto() {
        when(autosRepository.findByVin(anyString())).thenReturn(automobile);
        Automobile foundAuto = autosService.getAuto(automobile.getVin());
        assertThat(foundAuto).isNotNull();
        assertThat(foundAuto.getVin()).isEqualTo(automobile.getVin());
    }

    @Test
    void updateAuto() {
    }

    @Test
    void deleteAuto() {
    }
}