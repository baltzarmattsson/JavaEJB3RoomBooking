package t4.eao;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ejb.Local;
import javax.validation.ConstraintViolationException;

import t4.entities.Person;

@Local
public interface PersonEAOLocal {
	public ArrayList<Person> findAllPersons();
    public Person findPersonByPersonId(String personId);
    public Person createPerson(Person person);
    public Person updatePerson(Person person);
    public void deletePerson(String personId);
}
