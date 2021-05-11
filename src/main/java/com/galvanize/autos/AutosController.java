package com.galvanize.autos;

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
    public AutosList getAutos() {
        AutosList rtn = new AutosList(this.autosService.getAutos());
        rtn.setSearchParameters("all automobiles");
        return rtn;
    }

}