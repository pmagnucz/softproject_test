package hu.uni.miskolc.iit.controller;

import hu.uni.miskolc.iit.service.DummyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by pmagnucz on 2017. 09. 26..
 */
@RestController
@RequestMapping("/")
public class DummyController {
    private DummyService service;

    @Autowired
    public void setService(DummyService service) {
        this.service = service;
    }

    @GetMapping
    public String greeting(){
        return service.greeting("Na ki a jani?!");
    }
}
