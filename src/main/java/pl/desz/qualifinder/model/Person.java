package pl.desz.qualifinder.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Person {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Person(String name, String surname, List<String> skills) {
        this(name, surname, null, null, skills);
    }

    public Person(String name, String surname, String email, String phone, List<String> skills) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.skills = skills;
    }

    @NotEmpty
    @Size(min = 2)
    private String name;

    @NotEmpty
    @Size(min = 2)
    private String surname;

    @Email
    private String email;

    private String phone;

    @ElementCollection(targetClass = java.util.ArrayList.class)
    private List<String> skills = new ArrayList<>();
}
