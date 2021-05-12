package com.galvanize.autos;

import org.springframework.stereotype.Service;

@Service
public class AutosService {

    AutosRepository autosRepository;

    public AutosService(AutosRepository autosRepository) {
        this.autosRepository = autosRepository;
    }

    public AutosList getAutos() {
        return new AutosList(autosRepository.findAll());
    }

    public AutosList getAutos(String anyString, String anyString1) {
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
