package com.microservicio.fingerprints.controllers;

import com.microservicio.fingerprints.dtos.FingerprintResponse;
import com.microservicio.fingerprints.dtos.ValidateIn;
import static com.microservicio.fingerprints.util.Constantes.ENTITY_NAME;
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
@RequestMapping(value = "/core/fingerprints")
public class FingerprintController {
    
    @PostMapping(
        value = "/validate", 
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<FingerprintResponse> validateFingerprint(@RequestBody ValidateIn validateIn) {
        return Single.just(new FingerprintResponse(ENTITY_NAME, true));
    }
    
}
