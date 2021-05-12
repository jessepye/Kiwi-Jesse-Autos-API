package com.galvanize.autos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
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
    void getAutos() {
        when(autosRepository.findAll()).thenReturn(Arrays.asList(automobile));
        AutosList autosList = autosService.getAutos();
        assertThat(autosList).isNotNull();
        assertThat(autosList.isEmpty()).isFalse();
        assertThat(autosList.getCount()).isEqualTo(1);
    }

    @Test
    void testGetAutos() {
    }

    @Test
    void addAuto() {
    }

    @Test
    void getAuto() {
    }

    @Test
    void updateAuto() {
    }

    @Test
    void deleteAuto() {
    }
}