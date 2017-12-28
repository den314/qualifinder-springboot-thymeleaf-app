package pl.desz.qualifinder.model;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Arrays;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonTest {

    private Person testPersonWithAllValidFields;
    private Person testPersonWithAllNullFields;

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
        // reset testing data
        testPersonWithAllValidFields = new Person("theName", "surname", "example@example.pl",
                "502683344", Arrays.asList("java", "python", "c"));
        testPersonWithAllNullFields = new Person();
    }

    @Test
    public void shouldFailWhenAllFieldsAreNull() {
        Set<ConstraintViolation<Person>> constraints = validator.validate(testPersonWithAllNullFields);
        assertThat(constraints).hasSize(2);
    }

    @Test
    public void shouldFailWhenEmailNotCorrect() {
        testPersonWithAllValidFields.setEmail("dummy");
        Set<ConstraintViolation<Person>> constraints = validator.validate(testPersonWithAllValidFields);
        assertThat(constraints).hasSize(1);
    }

    @Test
    public void shouldFailWhenNameAndSurnameLengthTooShort() {
        testPersonWithAllValidFields.setName("a");
        testPersonWithAllValidFields.setSurname("b");
        Set<ConstraintViolation<Person>> constraints = validator.validate(testPersonWithAllValidFields);
        assertThat(constraints).hasSize(2);
    }

    @Test
    public void shouldPassWhenAllFieldsValid() {
        Set<ConstraintViolation<Person>> constraints = validator.validate(testPersonWithAllValidFields);
        assertThat(constraints).hasSize(0);
    }
}