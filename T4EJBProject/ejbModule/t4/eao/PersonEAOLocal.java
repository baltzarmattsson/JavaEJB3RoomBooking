package t4.eao;

import javax.ejb.Local;

import t4.entities.Person;

@Local
public interface PersonEAOLocal {
    public Person findByPersonId(String personId);
    public Person createPerson(Person person);
    public Person updatePerson(Person person);
    public void deletePerson(String personId);
}
