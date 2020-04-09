package com.microservicio.persons.services.impl;

import com.microservicio.persons.entities.Person;
import com.microservicio.persons.exceptions.PersonNotFoundException;
import com.microservicio.persons.repositories.IPersonRepository;
import com.microservicio.persons.services.IPersonService;
import com.microservicio.persons.services.dto.PersonRespose;
import com.microservicio.persons.utils.Util;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Frank
 */
@Service
public class PersonServiceImpl implements IPersonService {

    @Autowired
    private IPersonRepository personRepository;

    @Override
    public Single<PersonRespose> findByDocumentNumber(String documentNumber) {
        return Single.fromCallable(() -> getPerson(documentNumber))
            .map(this::parsePersonRespose);
    }
    
    private Person getPerson(String documentNumber) throws PersonNotFoundException {
        Person person = personRepository.findByDocument(documentNumber);
        if (person == null) {
            throw new PersonNotFoundException();
        }
        return person;
    }
    
    private PersonRespose parsePersonRespose(Person person) {
        return new PersonRespose(
            person.getId().intValue(),
            person.getDocument(),
            Util.parseBoolean(person.getFingerprint()),
            Util.parseBoolean(person.getBlacklist())
        );
    }

}
