package uni.miskolc.iit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uni.miskolc.iit.service.DummyService;

/**
 * Created by pmagnucz on 2017. 09. 19..
 */
@RestController
public class DummyController {
    private DummyService service;

    @Autowired
    public void setService(DummyService service) {
        this.service = service;
    }

    @PostMapping("/")
    public String greeting(@RequestBody String name){
        return service.greeting(name);
    }
}
