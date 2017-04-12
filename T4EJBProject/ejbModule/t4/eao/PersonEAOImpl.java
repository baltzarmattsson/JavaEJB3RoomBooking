package t4.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import t4.entities.Person;

/**
 * Session Bean implementation class PersonEAOImpl
 */
@Stateless
public class PersonEAOImpl implements PersonEAOLocal {

	@PersistenceContext(unitName = "MSSQLT4")
	private EntityManager em;

	public PersonEAOImpl() {
	}

	public Person findByPersonId(String personId) {
		return em.find(Person.class, personId);
	}

	public Person createPerson(Person person) {
		System.out.println("testcreate");
		em.persist(person);
		return person;
	}

	public Person updatePerson(Person person) {
		em.merge(person);
		return person;
	}

	public void deletePerson(String personId) {
		Person p = this.findByPersonId(personId);
		if (p != null)
			em.remove(p);
	}

}
