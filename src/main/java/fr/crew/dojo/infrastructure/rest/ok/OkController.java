package fr.crew.dojo.infrastructure.rest.ok;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller Ok.
 */
@RestController
public class OkController {


    @GetMapping( {"/ok"})
    public String getAnOK() {
        return "OK";
    }

}
