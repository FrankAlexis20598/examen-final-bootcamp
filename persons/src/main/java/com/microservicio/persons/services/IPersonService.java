package com.microservicio.persons.services;

import com.microservicio.persons.services.dto.PersonRespose;
import io.reactivex.Single;

/**
 *
 * @author Frank
 */
public interface IPersonService {
    
    Single<PersonRespose> findByDocumentNumber(String documentNumber);
}
