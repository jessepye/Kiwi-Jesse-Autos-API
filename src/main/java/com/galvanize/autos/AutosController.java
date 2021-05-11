package com.galvanize.autos;

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
    public Automobile addAuto(@RequestBody(required = true) Automobile automobile) {
        return this.autosService.addAuto(automobile);
    }
}