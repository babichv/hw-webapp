package com.company.webapp.dao;

import com.company.webapp.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Дмитрий", "Волков", "Водитель", "dmitriyvolkov@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT,"Виталий", "Бабич","Менеджер" ,"babichvitaliy@gmail.com"));
    }

    public List<Person> index(){
        return people;
    }

    public Person show(int id){
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person){
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update( int id, Person updatedPerson){
        Person personToBeUpdated = show(id);
        personToBeUpdated.setFirstName(updatedPerson.getFirstName());
        personToBeUpdated.setLastName(updatedPerson.getLastName());
        personToBeUpdated.setPosition(updatedPerson.getPosition());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id){
        people.removeIf(p -> p.getId() == id);
    }
}
