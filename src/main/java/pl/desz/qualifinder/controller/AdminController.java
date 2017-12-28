package pl.desz.qualifinder.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.desz.qualifinder.model.Person;
import pl.desz.qualifinder.repository.PersonRepository;

import java.util.Optional;

@Controller
@RequestMapping("/admin/people")
public class AdminController {
    
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    public String allPeople(Model model) {
        model.addAttribute("people", personRepository.findAll());
        return "admin/all-people";
    }

    @GetMapping("/edit")
    public String editPerson(@RequestParam("id") Long id, Model model) {

        Person personToUpdate = personRepository.findOne(id);

        Optional.ofNullable(personToUpdate).orElseThrow(() -> new IllegalArgumentException("User does not exists, id was:" + id));
        model.addAttribute("person", personToUpdate);

        log.debug("Forwarding to person edit with model: {}", personToUpdate);

        return "admin/edit-person";
    }

    @GetMapping("/delete")
    public String deletePerson(@RequestParam("id") Long id) {

        Optional.ofNullable(personRepository.findOne(id)).orElseThrow(() -> new IllegalArgumentException("User does not exists, id was:" + id));
        personRepository.delete(id);

        return "redirect:/admin/people";
    }
}
