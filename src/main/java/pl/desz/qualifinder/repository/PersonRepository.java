package pl.desz.qualifinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.desz.qualifinder.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByName(String name);
    Person findByNameIgnoreCase(String name);
}
