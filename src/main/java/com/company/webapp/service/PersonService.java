package com.company.webapp.service;

import com.company.webapp.entity.Person;
import com.company.webapp.exception.PeopleNotFoundException;
import com.company.webapp.repositoriy.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    public PersonRepository personRepository;

    public Iterable<Person> findAll () throws PeopleNotFoundException {
        Iterable<Person> people = personRepository.findAll();
        if (people == null){
            throw new PeopleNotFoundException("People Not Found");
        }
        return people;
    }
    public Person findByPhone(String phone) throws PeopleNotFoundException {
        Person person = personRepository.findByPhone(phone);
        if (person == null){
            throw new PeopleNotFoundException("Person not found");
        }
        return person;
    }
    public Person findById(Long id) throws PeopleNotFoundException {
        Person person = personRepository.getById(id);
        if (person == null){
            throw new PeopleNotFoundException("People Not Found");
        }
        return person;
    }

    public void create(Person person){
        personRepository.save(person);
    }
    public void update(long id, Person updatePerson){
        Person personToBeUpdated = personRepository.findById(id).get();
        if (updatePerson != null){
            personToBeUpdated.setFirstName(updatePerson.getFirstName());
            personToBeUpdated.setLastName(updatePerson.getLastName());
            personToBeUpdated.setPhone(updatePerson.getPhone());
            personToBeUpdated.setEmail(updatePerson.getEmail());
            personRepository.save(personToBeUpdated);
        }
    }
    public void delete(long id){
        personRepository.deleteById(id);
    }
}
