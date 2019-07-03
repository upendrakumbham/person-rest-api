package com.ebi.personrestapi.controller;

import com.ebi.personrestapi.model.Person;
import com.ebi.personrestapi.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@RestController()
@RequestMapping("/api/persons")
public class PersonController {

    //logger initialised
    private static final Logger LOGGER = LoggerFactory.getLogger
            (PersonController.class);

    private PersonService personService;

    public PersonController(final PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<Person>> getPersons() {
        LOGGER.info("getting all the persons");
        List<Person> persons = personService.getAllPersons();
        LOGGER.info("Retrieved {} records successfully!!", persons.size());
        return ResponseEntity.ok(persons);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id) {
        LOGGER.info("Getting the person: {}", id);
        Person person = personService.getPerson(id);
        LOGGER.info("Retrieved person {} successfully!!", id);
        return ResponseEntity.ok(person);
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody final Person toCreate) {
        assertNotNull(toCreate, "Request body should not be null");
        LOGGER.info("Creating the person");
        Person created = personService.createPerson(toCreate);
        LOGGER.trace("Person created with id {}", created.getId());
        LOGGER.info("Created the person: {}", created.getFirst_name());
        return new ResponseEntity(created, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person toUpdate) {
        LOGGER.info("Updating the person:{}", id);
        toUpdate.setId(id);
        Person person = personService.updatePerson(toUpdate);
        LOGGER.info("Person {} updated successfully!!", id);
        return new ResponseEntity(person, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        LOGGER.info("Deleting the person {}", id);
        personService.deletePerson(id);
        LOGGER.info("Person '{}' deleted successfully!!", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
