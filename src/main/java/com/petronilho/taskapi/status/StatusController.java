package com.petronilho.taskapi.status;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by renanpetronilho on 31/07/17.
 */
@RestController
public class StatusController {

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public ResponseEntity<Status> getStatus(){
        return new ResponseEntity<>(new Status("OK"), HttpStatus.OK);
    }
}
