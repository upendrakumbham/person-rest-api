package com.ebi.personrestapi.client;

import com.ebi.personrestapi.PersonRestApiApplication;
import com.ebi.personrestapi.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ApiRestClient implements CommandLineRunner {

    //initialise logger
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonRestApiApplication.class);

    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Start testing the endpoints");
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        List<String> hobby = Stream.of("reading", "music")
                .collect(Collectors.toList());

        Person Alice = new Person("Alice", "wonderland", 29, "pink", hobby);
        Person John = new Person("John", "wonderland", 29, "white", hobby);
        Person Amelie = new Person("Amelie", "wonderland", 29, "black", hobby);
        Person Rabbit = new Person("Rabbit", "wonderland", 29, "green", hobby);

        List<Person> persons = Arrays.asList(Alice, John, Amelie, Rabbit);

        try {
            HttpEntity<Person> entity = new HttpEntity<>(Alice, headers);
            LOGGER.info("Calling create end point");
            ResponseEntity<Person> response = template.exchange("http://localhost:8088/persons",
                    HttpMethod.POST, entity, Person.class);
            LOGGER.info("Person created:{}",response.getBody().toString());
            LOGGER.info("Calling get person endpoint");
           String retrievedPerson = template.getForObject("http://localhost:8088/persons/{id}", String.class, response.getBody().getId());
            LOGGER.info("retrievedPerson: {} ",retrievedPerson);
            LOGGER.info("Start calling delete person endpoint ");
            template.delete("http://localhost:8088/persons/{id}", response.getBody().getId());
            LOGGER.info("Successfully performed delete operation!!");
            LOGGER.info("Call create person endpoint in a loop to create bunch of persons");
            for (Person personEntity : persons) {
                HttpEntity<Person> request = new HttpEntity<>(personEntity, headers);
                template.exchange("http://localhost:8088/persons",
                        HttpMethod.POST, request, Person.class);
            }
            LOGGER.info("Finished creating bunch of persons!!");
            LOGGER.info("Call get all persons endpoint!!");
            ResponseEntity<List<Person>> result = template.exchange("http://localhost:8088/persons", HttpMethod.GET, null, new ParameterizedTypeReference<List<Person>>() {
            });
            LOGGER.info("Retrieved all the persons:{}", persons.size());
        } catch (RestClientException e) {
            LOGGER.info("Error while creating person {}", e.getCause());
            e.printStackTrace();
        }
    }
}
