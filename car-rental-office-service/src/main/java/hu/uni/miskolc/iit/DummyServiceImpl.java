package hu.uni.miskolc.iit;

import hu.uni.miskolc.iit.service.DummyService;
import org.springframework.stereotype.Service;

/**
 * Created by pmagnucz on 2017. 09. 26..
 */
@Service
public class DummyServiceImpl implements DummyService {
    @Override
    public String greeting(String message) {
        return message;
    }
}
