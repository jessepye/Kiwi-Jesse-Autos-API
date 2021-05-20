package com.galvanize.autos;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

        return new AutosList();
    }

    public AutosList getAutos(String make) {
        List<Automobile> automobiles = autosRepository.findByMakeContains(make);

        if(!automobiles.isEmpty()) {
            return new AutosList(automobiles);
        }

        return new AutosList();
    }

    public AutosList getAutos(String color, String make) {
        List<Automobile> automobiles = autosRepository.findByColorContainsAndMakeContains(color, make);

        if(!automobiles.isEmpty()) {
            return new AutosList(automobiles);
        }

        return new AutosList();
    }

    public Automobile addAuto(Automobile automobile) {
        if (automobile.getVin() == null || !automobile.getVin().matches("^[a-zA-Z0-9]*$")) {
            throw new InvalidAutoException();
        }

        return autosRepository.save(automobile);
    }

    public Automobile getAuto(String vin) {
        return autosRepository.findByVin(vin).orElse(null);
    }

    public Automobile updateAuto(String vin, int price, Preowned preowned) {
        if (!vin.matches("^[a-zA-Z0-9]*$")) { // VIN may only contain letters and numbers
            throw new InvalidAutoException();
        }

        Optional<Automobile> oFoundAutomobile = autosRepository.findByVin(vin);

        if (oFoundAutomobile.isPresent()) {
            oFoundAutomobile.get().setPrice(price);
            oFoundAutomobile.get().setPreowned(preowned);
            return autosRepository.save(oFoundAutomobile.get());
        } else {
            return null;
        }
    }

    public void deleteAuto(String vin) {
        Optional<Automobile> oFoundAutomobile = autosRepository.findByVin(vin);

        if (oFoundAutomobile.isPresent()) {
            autosRepository.delete(oFoundAutomobile.get());
        } else {
            throw new InvalidAutoException();
        }
    }
}
