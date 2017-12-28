package pl.desz.qualifinder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.desz.qualifinder.model.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/person")
public class PersonController {

    @GetMapping("/add")
    public String personForm(Model model) {
        model.addAttribute("person", new Person());
        return "view/add-person";
    }

    @PostMapping("/add")
    public String addPerson(@Valid Person person, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "view/add-person";
        }

        model.addAttribute("person", person);
        return "view/person-added";
    }
}
