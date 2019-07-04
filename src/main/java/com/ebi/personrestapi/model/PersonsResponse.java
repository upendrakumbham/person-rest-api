package com.ebi.personrestapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Holds the response of the getAll action.
 */
public class PersonsResponse {
    @JsonProperty("person")
    public List<Person> persons;
}
