package com.galvanize.autos.service;

import com.galvanize.autos.exception.InvalidAutoException;
import com.galvanize.autos.model.Automobile;
import com.galvanize.autos.model.AutosList;
import com.galvanize.autos.model.Grade;
import com.galvanize.autos.model.Preowned;
import com.galvanize.autos.repository.AutosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AutosServiceTest {
    AutosService autosService;

    @Mock
    AutosRepository autosRepository;

    Automobile automobile;

    List<Automobile> automobiles;

    @BeforeEach
    void setup() {
        autosService = new AutosService(autosRepository);
        automobile = new Automobile("Toyota", "Supra", 1995, "ABC321");
        automobiles = new ArrayList<>();
    }

    @Test
    void getAutos_hasContent_returnsList() {
        automobiles.add(automobile);

        when(autosRepository.findAll()).thenReturn(automobiles);

        AutosList actual = autosService.getAutos();

        assertThat(actual).isNotNull();
        assertThat(actual.isEmpty()).isFalse();
        assertThat(actual.getCount()).isEqualTo(1);
    }

    @Test
    void getAutos_noContent_returnsEmptyList() {
        when(autosRepository.findAll()).thenReturn(automobiles);

        AutosList actual = autosService.getAutos();

        assertThat(actual).isNotNull();
        assertThat(actual.isEmpty()).isTrue();
    }

    @Test
    void getAutos_search_hasContent_returnsList() {
        automobile.setColor("White");
        automobiles.add(automobile);

        when(autosRepository.findByColorContainsAndMakeContains(anyString(), anyString()))
                .thenReturn(automobiles);

        AutosList actual = autosService.getAutos("White", "Toyota");

        assertThat(actual).isNotNull();
        assertThat(actual.isEmpty()).isFalse();
        assertThat(actual.getAutomobiles().get(0).getColor()).isEqualTo("White");
        assertThat(actual.getAutomobiles().get(0).getMake()).isEqualTo("Toyota");
    }

    @Test
    void getAutos_search_noContent_returnsList() {
        when(autosRepository.findByColorContainsAndMakeContains(anyString(), anyString()))
                .thenReturn(new ArrayList<Automobile>());

        AutosList actual = autosService.getAutos("White", "Toyota");

        assertThat(actual).isNotNull();
        assertThat(actual.isEmpty()).isTrue();
    }

    @Test
    void addAuto_valid_returnsAuto() {
        when(autosRepository.save(any(Automobile.class))).thenReturn(automobile);

        Automobile actual = autosService.addAuto(automobile);

        assertThat(actual).isNotNull();
        assertThat(actual.getVin()).isEqualTo(automobile.getVin());
    }

    @Test
    void addAuto_invalid_throwsException() {
        when(autosRepository.save(any(Automobile.class))).thenThrow(InvalidAutoException.class);

        assertThatThrownBy(() -> {
            autosService.addAuto(automobile);
        }).isInstanceOf(InvalidAutoException.class);
    }

    @Test
    void getAuto_byVin_returnsAuto() {
        when(autosRepository.findByVin(anyString())).thenReturn(java.util.Optional.ofNullable(automobile));

        Automobile actual = autosService.getAuto(automobile.getVin());

        assertThat(actual).isNotNull();
        assertThat(actual.getVin()).isEqualTo(automobile.getVin());
    }

    @Test
    void updateAuto_successful_returnsUpdate() {
        when(autosRepository.findByVin(anyString())).thenReturn(java.util.Optional.ofNullable(automobile));

        automobile.setPrice(1234500);
        automobile.setPreowned(Preowned.CPO);
        automobile.setGrade(Grade.EXCELLENT);

        when(autosRepository.save(any(Automobile.class))).thenReturn(automobile);

        Automobile actual = autosService.updateAuto(automobile.getVin(), 1234500, Preowned.CPO, Grade.EXCELLENT);

        assertThat(actual).isNotNull();
        assertThat(actual.getVin()).isEqualTo(automobile.getVin());
        assertThat(actual.getPrice()).isEqualTo(1234500);
        assertThat(actual.getPreowned()).isEqualTo(automobile.getPreowned());
        assertThat(actual.getGrade()).isEqualTo(automobile.getGrade());
    }

    @Test
    void updateAuto_noContent_returnsNull() {
        when(autosRepository.findByVin(anyString())).thenReturn(java.util.Optional.empty());

        Automobile actual = autosService.updateAuto(automobile.getVin(), 1234500, Preowned.CPO, Grade.EXCELLENT);

        assertThat(actual).isNull();
    }

    @Test
    void updateAuto_badRequest_throwsInvalidAutoException() {
        assertThatExceptionOfType(InvalidAutoException.class)
                .isThrownBy(() -> {
                    autosService.updateAuto("$BADVIN", 1234500, Preowned.CPO, Grade.EXCELLENT);
                });
    }

    @Test
    void deleteAuto_success() {
        when(autosRepository.findByVin(anyString())).thenReturn(java.util.Optional.of(automobile));

        autosService.deleteAuto(automobile.getVin());

        verify(autosRepository).delete(any(Automobile.class));
    }

    @Test
    void deleteAuto_fails_throwsInvalidAutoException() {
        when(autosRepository.findByVin(anyString())).thenReturn(java.util.Optional.empty());

        assertThatExceptionOfType(InvalidAutoException.class)
                .isThrownBy(() -> {
            autosService.deleteAuto("BADVIN");
        });
    }
}