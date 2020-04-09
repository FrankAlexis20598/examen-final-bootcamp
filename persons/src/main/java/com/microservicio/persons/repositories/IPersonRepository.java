package com.microservicio.persons.repositories;

import com.microservicio.persons.entities.Person;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Frank
 */
public interface IPersonRepository extends CrudRepository<Person, Long> {

    Person findByDocument(String document);
}
