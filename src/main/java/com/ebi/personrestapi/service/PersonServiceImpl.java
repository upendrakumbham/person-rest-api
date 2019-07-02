package com.ebi.personrestapi.service;

import com.ebi.personrestapi.exception.PersonNotFoundException;
import com.ebi.personrestapi.model.Person;
import com.ebi.personrestapi.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personsRepo;

    public PersonServiceImpl(PersonRepository personsRepo) {
        this.personsRepo = personsRepo;
    }

    /**
     * Get the person entity based on Id
     *
     * @param id - Person entity id. which is unique in the database.
     * @return person entity
     */
    @Override
    public Person getPerson(Long id){
       Optional<Person>  person = personsRepo.findById(id);
       if(!person.isPresent()){
           throw new PersonNotFoundException("person not found");
       }
       return person.get();
    }

    /**
     * Creates person entity
     *
     * @param person - input body
     * @return created person entity
     */
    @Override
    public Person createPerson(Person person) {
        return personsRepo.save(person);
    }

    /**
     * Updates person entity
     *
     * @param toUpdate - input body
     * @return updated person entity
     */
    @Override
    public Person updatePerson(Person toUpdate) {
        Person toBeUpdated = getPerson(toUpdate.getId());
        toBeUpdated.refresh(toUpdate);
        return personsRepo.save(toBeUpdated);
    }

    /**
     * Deletes person entity silently - If exist otherwise just perform delete operation successfully.
     * @param id - id of the person entity
     */
    @Override
    public void deletePerson(Long id) {
       getPerson(id);
        personsRepo.delete(id);
    }

    /**
     * Get all the person records from the database
     *
     * @return List of persons
     */
    @Override
    public List<Person> getAllPersons() {
        return personsRepo.findAll();
    }
}
