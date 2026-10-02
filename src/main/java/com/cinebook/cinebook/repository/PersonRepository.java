package com.cinebook.cinebook.repository;

import com.cinebook.cinebook.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
