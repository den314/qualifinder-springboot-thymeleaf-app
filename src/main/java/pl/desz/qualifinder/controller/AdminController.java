package pl.desz.qualifinder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.desz.qualifinder.repository.PersonRepository;

@Controller
@RequestMapping("/admin/people")
public class AdminController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    public String allPeople(Model model) {
        model.addAttribute("people", personRepository.findAll());
        return "admin/all-people";
    }
}
