package com.cinebook.cinebook.service;

import com.cinebook.cinebook.dto.person.PersonRequest;
import com.cinebook.cinebook.dto.person.PersonResponse;
import com.cinebook.cinebook.entity.Person;
import com.cinebook.cinebook.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public PersonResponse createPerson(PersonRequest request) {
        Person person = new Person();
        person.setName(request.getName());
        person.setPhotoUrl(request.getPhotoUrl());
        person.setBio(request.getBio());
        return mapToResponse(personRepository.save(person));
    }

    public PersonResponse getPersonById(Long id) {
        return mapToResponse(findOrThrow(id));
    }

    private Person findOrThrow(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found."));
    }

    public List<PersonResponse> getAllPeople() {
        return personRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    public PersonResponse updatePerson(Long id, PersonRequest request) {
        Person person = findOrThrow(id);
        person.setName(request.getName());
        person.setPhotoUrl(request.getPhotoUrl());
        person.setBio(request.getBio());

        return mapToResponse(personRepository.save(person));
    }

    private PersonResponse mapToResponse(Person person) {
        return new PersonResponse(person.getId(), person.getName(), person.getPhotoUrl(), person.getBio());
    }
}
