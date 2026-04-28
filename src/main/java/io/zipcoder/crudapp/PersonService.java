package io.zipcoder.crudapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    private PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public Iterable<Person> getAllPeople() {
        return repository.findAll();
    }

    public Person getPersonById(Long id) {
        return repository.findById(id).orElse(notFound());
    }

    public Person createPerson(Person person) {
        return repository.save(person);
    }

    public Person updatePerson(Long id, Person person) {
        Person existingPerson = getPersonById(id);
        if (existingPerson != null) {
            person.setId(id);
            return repository.save(person);
        }
        return null;
    }

    public void deletePerson(Long id) {
        repository.deleteById(id);
    }
}
