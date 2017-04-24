package t4.facade;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import t4.eao.LoginEAOLocal;
import t4.eao.PersonEAOLocal;
import t4.eao.RoleEAOLocal;
import t4.entities.Login;
import t4.entities.Person;
import t4.entities.Role;

@Stateless
public class Facade implements FacadeLocal {

	@EJB
	private LoginEAOLocal loginEAO;
	@EJB
	private PersonEAOLocal personEAO;
	@EJB
	private RoleEAOLocal roleEAO;

	public Facade() {
	}
	
	// LOGIN
	
	public ArrayList<Login> findAllLogins() {
		return this.loginEAO.findAllLogins();
	}

	public Login findLoginByPersonId(String personId) {
		return this.loginEAO.findLoginByPersonId(personId);
	}

	public Login createLogin(Login login) {
		return this.loginEAO.createLogin(login);
	}

	public Login updateLogin(Login login) {
		return this.loginEAO.updateLogin(login);
	}

	public void deleteLogin(String personId) {
		this.loginEAO.deleteLogin(personId);
	}
	
	
	// PERSON
	
	public ArrayList<Person> findAllPersons() {
		return this.personEAO.findAllPersons();
	}

	public Person findPersonByPersonId(String personId) {
		return this.personEAO.findPersonByPersonId(personId);
	}

	public Person createPerson(Person person) {
		return this.personEAO.createPerson(person);
	}

	public Person updatePerson(Person person) {
		return this.personEAO.updatePerson(person);
	}

	public void deletePerson(String personId) {
		this.personEAO.deletePerson(personId);
	}
	
	// ROLE
	
	public ArrayList<Role> findAllRoles() {
		return this.roleEAO.findAllRoles();
	}

	public Role findRoleByRoleName(String roleName) {
		return this.roleEAO.findRoleByRoleName(roleName);
	}

	public Role createRole(Role role) {
		return this.roleEAO.createRole(role);
	}

	public Role updateRole(Role role) {
		return this.roleEAO.updateRole(role);
	}

	public void deleteRole(String roleName) {
		this.roleEAO.deleteRole(roleName);
	}

}
