package com.galvanize.autos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutosListTest {
    AutosList autosList;
    @BeforeEach
    void setup() {
        autosList = new AutosList(new ArrayList<>());
    }

    @Test
    public void isEmpty_returnsTrue() {
        assertEquals(true, autosList.isEmpty(), "Should return true for an empty AutosList");
    }

    @Test
    public void get_noArgs_returnsAllAutomobiles() {
        assertEquals(true, autosList.isEmpty(), "Should return false for a new empty AutosList");

        List<Automobile> testAutomobiles = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Automobile automobile = new Automobile(i, "Ford", "Mustang", 1967 + i, "ABC" + 12 + i);
            testAutomobiles.add(automobile);
        }

        autosList = new AutosList(testAutomobiles);

        assertEquals(testAutomobiles, autosList.getAutomobiles(), "Should return true after adding");
    }
}
