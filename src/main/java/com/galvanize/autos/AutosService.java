package com.galvanize.autos;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutosService {

    AutosRepository autosRepository;

    public AutosService(AutosRepository autosRepository) {
        this.autosRepository = autosRepository;
    }

    public AutosList getAutos() {
        List<Automobile> automobiles = autosRepository.findAll();

        if(!automobiles.isEmpty()) {
            return new AutosList(automobiles);
        }

        return null;
    }

    public AutosList getAutos(String color, String make) {
        List<Automobile> automobiles = autosRepository.findByColorContainsAndMakeContains(color, make);

        if(!automobiles.isEmpty()) {
            return new AutosList(automobiles);
        }

        return null;
    }

    public Automobile addAuto(Automobile automobile) {
        return null;
    }

    public Automobile getAuto(String vin) {
        return null;
    }

    public Automobile updateAuto(String vin, int price, Preowned preowned) {
        return null;
    }

    public void deleteAuto(String vin) {
    }
}
