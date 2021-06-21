package com.galvanize.autos.controller;

import com.galvanize.autos.exception.InvalidAutoException;
import com.galvanize.autos.model.Automobile;
import com.galvanize.autos.model.AutosList;
import com.galvanize.autos.model.UpdateAutoRequest;
import com.galvanize.autos.service.AutosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/autos")
public class AutosController {
    AutosService autosService;

    public AutosController(AutosService autosService) {
        this.autosService = autosService;
    }

    @GetMapping
    public ResponseEntity<AutosList> getAutos(@RequestParam(required = false) String color,
                                              @RequestParam(required = false) String make) {
        AutosList rtn;
        if (color == null && make == null) {
            rtn = this.autosService.getAutos();
        } else if (color == null) {
            rtn = this.autosService.getAutos(make);
        } else {
            rtn = this.autosService.getAutos(color, make);
        }

        return rtn.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(rtn);
    }

    @PostMapping
    public Automobile addAuto(@RequestBody Automobile automobile) {
        return this.autosService.addAuto(automobile);
    }

    @GetMapping("/{vin}")
    public ResponseEntity<Automobile> getAuto(@PathVariable String vin) {
        return autosService.getAuto(vin) == null ?
                ResponseEntity.noContent().build() : ResponseEntity.ok(autosService.getAuto(vin));
    }

    @PatchMapping("/{vin}")
    public ResponseEntity<Automobile> updateAuto(@PathVariable String vin, @RequestBody UpdateAutoRequest update) {
        Automobile automobile = autosService.updateAuto(vin, update.getPrice(), update.getPreowned(), update.getGrade());

        return automobile == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(automobile);
    }

    @DeleteMapping("/{vin}")
    public ResponseEntity deleteAuto(@PathVariable String vin) {
        try {
            autosService.deleteAuto(vin);
            return ResponseEntity.accepted().build();
        } catch (InvalidAutoException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void invalidAutoExceptionHandler(InvalidAutoException e) {}
}