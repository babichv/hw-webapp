package com.company.webapp.controller;

import com.company.webapp.entity.Person;
import com.company.webapp.exception.PeopleNotFoundException;
import com.company.webapp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    PersonService personService;

    @GetMapping
    public String index(Model model) throws PeopleNotFoundException {
        // Получим всех людей из Dao и передадим на отображение в представление
        model.addAttribute("people", personService.findAll());
        return "people/index";
    }

    @GetMapping("/search")
    public String show(@RequestParam(value = "phone") String phone, Model model) throws PeopleNotFoundException {
        // Получаем одного человека по его ID из DAO и передадим на отображение в представление
        Person person = personService.findByPhone(phone);
        model.addAttribute("person", person);
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model){
        //создание нового человека
        model.addAttribute("person", new Person());

        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person){
        personService.create(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) throws PeopleNotFoundException {
        model.addAttribute("person", personService.findById(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person, @PathVariable("id") Long id){
        personService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        personService.delete(id);
        return "redirect:/people";
    }
}
