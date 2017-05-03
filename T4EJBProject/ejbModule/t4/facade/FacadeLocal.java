package t4.facade;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ejb.Local;
import javax.validation.ConstraintViolationException;

import t4.entities.Login;
import t4.entities.Person;
import t4.entities.Role;

@Local
public interface FacadeLocal {

	public ArrayList<Login> findAllLogins();
    public Login findLoginByPersonId(String personId);
    public Login createLogin(Login login);
    public Login updateLogin(Login login);
    public void deleteLogin(String personId);

	public ArrayList<Person> findAllPersons();
    public Person findPersonByPersonId(String personId);
    public Person createPerson(Person person);
    public Person updatePerson(Person person);
    public void deletePerson(String personId);

	public ArrayList<Role> findAllRoles();
    public Role findRoleByRoleName(String roleName);
    public Role createRole(Role role);
    public Role updateRole(Role role);
    public void deleteRole(String roleName);
    
}
