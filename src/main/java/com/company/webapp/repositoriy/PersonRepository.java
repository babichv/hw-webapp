package com.company.webapp.repositoriy;

import com.company.webapp.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByPhone(String phone);
}
