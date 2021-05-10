package com.galvanize.autos;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autos")
public class AutosController {

    @GetMapping
    public AutosList getAutos() {
        // AutosList autosList = Service.getAutos() // Service is an abstraction layer so we are not making calls directly to the database
        return null;
    }
}
