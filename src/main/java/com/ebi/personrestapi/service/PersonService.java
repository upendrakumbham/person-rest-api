package com.ebi.personrestapi.service;

import com.ebi.personrestapi.model.Person;

import java.util.List;

public interface PersonService {


    /**
     * Get the person entity based on Id
     * @param id - Person entity id. which is unique in the database.
     * @return person entity
     */
    Person getPerson(Long id);

    /**
     * Creates person entity
     * @param person - input body
     * @return created person entity
     */
    Person createPerson(Person person);

    /**
     * Updates person entity
     * @param person - input body
     * @return updated person entity
     */
    Person updatePerson(Person person);

    /**
     * Deletes person entity
     * @param id - id of the person entity
     * @return deleted person entity
     */
    void deletePerson(Long id);

    /**
     * Get all the person records from the database
     * @return List of persons
     */
    List<Person> getAllPersons();
}
