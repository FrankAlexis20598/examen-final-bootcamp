package com.microservicio.persons.controllers;

import com.microservicio.persons.services.IPersonService;
import com.microservicio.persons.services.dto.PersonRespose;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Frank
 */
@RestController
@RequestMapping(value = "/core/persons")
public class PersonController {
    
    @Autowired
    private IPersonService personService;
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<PersonRespose> findByDocumentNumber(@RequestParam String documentNumber) {
        return personService.findByDocumentNumber(documentNumber);
    }
    
}
