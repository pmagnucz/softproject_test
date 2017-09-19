package uni.miskolc.iit.service.impl;

import org.springframework.stereotype.Service;
import uni.miskolc.iit.service.DummyService;

/**
 * Created by pmagnucz on 2017. 09. 19..
 */
@Service
public class DummyServiceImpl implements DummyService {
    @Override
    public String greeting(String name) {
        return "Hello " + name + "!";
    }
}
