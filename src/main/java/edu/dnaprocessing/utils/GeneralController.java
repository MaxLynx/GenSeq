package edu.dnaprocessing.utils;

import edu.dnaprocessing.sequence.protein.ProteinSequence;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Max on 06.02.2019.
 */
@RestController
@RequestMapping(value="/general")
public class GeneralController {

    @RequestMapping(method= RequestMethod.GET, value="/connection")
    public boolean testConnection(){
        return true;
    }
}
