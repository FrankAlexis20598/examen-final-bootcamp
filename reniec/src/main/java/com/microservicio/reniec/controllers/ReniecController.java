package com.microservicio.reniec.controllers;

import com.microservicio.reniec.dtos.ReniecRespose;
import com.microservicio.reniec.dtos.ValidateIn;
import static com.microservicio.reniec.util.Constantes.ENTITY_NAME;
import io.reactivex.Single;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Frank
 */
@RestController
@RequestMapping(value = "/external/reniec")
public class ReniecController {
    
    @PostMapping(
        value = "/validate",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<ReniecRespose> validateDocumentNumber(@RequestBody ValidateIn validateIn) {
        return Single.just(new ReniecRespose(ENTITY_NAME, true));
    }
    
}
