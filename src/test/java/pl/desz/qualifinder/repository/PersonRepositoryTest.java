package pl.desz.qualifinder.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.desz.qualifinder.model.Person;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryTest {

    private Person testPersonWithAllMandatoryFields = new Person("theName", "surname", Arrays.asList("java", "python", "c"));

    @Autowired
    private TestEntityManager em;

    @Autowired
    private PersonRepository pr;

    @Test
    public void shouldFindByName() {
        Long entityId = (Long) em.persistAndGetId(testPersonWithAllMandatoryFields);
        Person person = pr.findByName("theName");

        assertThat(person).isNotNull();
        assertThat(person.getId()).isEqualTo(entityId);
        assertThat(person.getSkills()).contains("java", "python", "c");
    }

    @Test
    public void shouldFindByNameIgnoreCase() {
        Long entityId = (Long) em.persistAndGetId(testPersonWithAllMandatoryFields);
        Person person = pr.findByNameIgnoreCase("thename");

        assertThat(person).isNotNull();
        assertThat(person.getId()).isEqualTo(entityId);
        assertThat(person.getSkills()).contains("java", "python", "c");
    }
}