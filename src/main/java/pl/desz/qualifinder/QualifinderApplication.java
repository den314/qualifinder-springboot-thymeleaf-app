package pl.desz.qualifinder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.desz.qualifinder.model.Person;
import pl.desz.qualifinder.repository.PersonRepository;

import java.util.ArrayList;

@SpringBootApplication
public class QualifinderApplication {

    public static void main(String[] args) {
        SpringApplication.run(QualifinderApplication.class, args);
    }


    // Load sample data on application startup
    @Autowired
    private PersonRepository pr;

    @Bean
    public CommandLineRunner load() {
        return args -> {
            ArrayList<String> skills = new ArrayList<>();
            skills.add("R");
            skills.add("C");
            skills.add("Python");

            pr.save(new Person("Dan", "Crown", skills));

            skills.remove("R");
            skills.remove("Python");
            skills.add("Java");
            pr.save(new Person("Mike", "Hopkins", "mike@example.com", "502697316", skills));

            skills.remove("Java");
            skills.add("C++");
            skills.add("Python");
            pr.save(new Person("Katarzyna", "Daleka", null, "605376553", skills));

            skills.remove("C");
            skills.remove("Python");
            skills.add("GoLang");
            pr.save(new Person("John", "Cennik", "JoCe@example.com", null, skills));
        };
    }
}
