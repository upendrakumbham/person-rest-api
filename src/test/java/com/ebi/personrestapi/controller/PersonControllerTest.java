package com.ebi.personrestapi.controller;

import com.ebi.personrestapi.model.Person;
import com.ebi.personrestapi.service.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

public class PersonControllerTest {

    Person Alice;
    private PersonController subject;
    private PersonService personServiceMock;

    public PersonControllerTest() {
        personServiceMock = mock(PersonService.class);
        subject = new PersonController(personServiceMock);
    }

    @Before
    public void setUp() throws Exception {
        Alice = getPersons().get(0);
    }

    @Test
    public void createPerson() {
        when(personServiceMock.createPerson(Alice)).thenReturn(Alice);
        ResponseEntity<Person> response = subject.createPerson(Alice);
        assertSame(HttpStatus.CREATED, response.getStatusCode());
        assertSame(Alice.getFirst_name(), response.getBody().getFirst_name());
    }

    @Test
    public void getPersonsById() {
        when(personServiceMock.getPerson(Alice.getId())).thenReturn(Alice);
        ResponseEntity<Person> response = subject.getPerson(Alice.getId());
        assertSame(HttpStatus.OK, response.getStatusCode());
        assertSame(Alice.getFirst_name(), response.getBody().getFirst_name());
    }

    @Test
    public void getAllPersons() {
        when(personServiceMock.getAllPersons()).thenReturn(getPersons());
        ResponseEntity<List<Person>> response = subject.getPersons();
        assertSame(HttpStatus.OK, response.getStatusCode());
        assertSame(4, response.getBody().size());
    }

    @Test
    public void updatePerson() {
        Alice.setFirst_name("update_me");
        Alice.setFavourite_color("black");
        when(personServiceMock.updatePerson(Alice)).thenReturn(Alice);
        ResponseEntity<Person> response = subject.updatePerson(Alice.getId(), Alice);
        assertSame(HttpStatus.OK, response.getStatusCode());
        assertSame("update_me", response.getBody().getFirst_name());
    }

    @Test
    public void deletePerson() {
        doAnswer((nothing) -> null).when(personServiceMock).deletePerson(Alice.getId());
        ResponseEntity response = subject.deletePerson(Alice.getId());
        assertSame(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    private List<Person> getPersons() {
        List<String> hobby = Stream.of("reading", "music")
                .collect(Collectors.toList());
        Person Alice = new Person("Alice", "wonderland", 29, "pink", hobby);
        Person John = new Person("John", "wonderland", 40, "white", hobby);
        Person Amelie = new Person("Amelie", "wonderland", 52, "black", hobby);
        Person Rabbit = new Person("Rabbit", "White", 13, "green", hobby);
        List<Person> persons = Arrays.asList(Alice, John, Amelie, Rabbit);
        return persons;
    }
}