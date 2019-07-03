package com.ebi.personrestapi.service;

import com.ebi.personrestapi.controller.PersonControllerTest;
import com.ebi.personrestapi.exception.PersonNotFoundException;
import com.ebi.personrestapi.model.Person;
import com.ebi.personrestapi.repository.PersonRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class PersonServiceImplTest {
    private final PersonService subject;
    private PersonRepository personRepoMock;
    private Person Alice;

    public PersonServiceImplTest() {
        personRepoMock = mock(PersonRepository.class);
        subject = new PersonServiceImpl(personRepoMock);
    }

    @Before
    public void setUp() throws Exception {
        Alice = PersonControllerTest.getTestPersons().get(0);
        when(personRepoMock.save(Alice)).thenReturn(Alice);
        when(personRepoMock.findById(Alice.getId())).thenReturn(Optional.of(Alice));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getPersonById() {
        Person retrievedAlice = subject.getPerson(Alice.getId());
        assertThat("should be Alice", retrievedAlice.getFirst_name(), is(Alice.getFirst_name()));
    }

    @Test(expected = PersonNotFoundException.class)
    public void throwExceptionIfPersonNotFound() {
        when(personRepoMock.findById(Alice.getId())).thenReturn(Optional.empty());
        subject.getPerson(Alice.getId());
    }

    @Test
    public void createPerson() {
        Person AliceCreated = subject.createPerson(Alice);
        assertThat(AliceCreated.getFirst_name(), is(Alice.getFirst_name()));
    }

    @Test
    public void updatePerson() {
        Alice.setLast_name("changeme");
        Person updatedPerson = subject.updatePerson(Alice);
        assertThat(updatedPerson.getLast_name(), is("changeme"));
    }

    @Test
    public void deletePerson() {
        subject.deletePerson(Alice.getId());
        verify(personRepoMock, times(1)).delete(Alice.getId());
    }

    @Test
    public void getAllPersons() {
        when(personRepoMock.findAll()).thenReturn(PersonControllerTest.getTestPersons());
        List<Person> retrievedPersons = subject.getAllPersons();
        assertThat(retrievedPersons.size(), is(4));
    }
}