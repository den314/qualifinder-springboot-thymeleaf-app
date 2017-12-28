package pl.desz.qualifinder.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.desz.qualifinder.model.Person;
import pl.desz.qualifinder.repository.PersonRepository;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/person")
public class PersonController {
    
    private static final Logger log = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/add")
    public String personForm(Model model) {
        model.addAttribute("person", new Person());
        return "view/person/add-person";
    }

    @PostMapping("/add")
    public String addPerson(@Valid Person person, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "view/person/add-person";
        }

        log.debug("Will add person: {}", person);

        // quick and dirty check for update TODO extract to service component
        Optional<Person> one = Optional.ofNullable(personRepository.findOne(person.getId()));
        System.out.println("GOT OPTIONAL OF:");
        one.ifPresent(System.out::println);
        one.ifPresent(p -> personRepository.delete(p.getId()));

        personRepository.save(person);

        model.addAttribute("person", person);
        return "view/person/person-added";
    }
}
