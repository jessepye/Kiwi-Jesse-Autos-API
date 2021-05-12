package com.galvanize.autos;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        Automobile automobile = autosService.updateAuto(vin, update.getPrice(), update.getPreowned());

        return automobile == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(automobile);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void invalidAutoExceptionHandler(InvalidAutoExcepton e) {

    }
}