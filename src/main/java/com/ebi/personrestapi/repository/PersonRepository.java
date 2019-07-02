package com.ebi.personrestapi.repository;

import com.ebi.personrestapi.model.Person;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
    Optional<Person> findById(Long id);
    Optional<Void> deleteById(Long id);
}
