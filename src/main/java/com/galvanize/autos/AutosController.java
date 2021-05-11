package com.galvanize.autos;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autos")
public class AutosController {

    AutosService autosService;

    public AutosController(AutosService autosService) {
        this.autosService = autosService;
    }

    @GetMapping
    public ResponseEntity getAutos() {
        AutosList rtn = new AutosList(this.autosService.getAutos());
        rtn.setSearchParameters("all automobiles");

        return rtn.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(rtn);
    }
}