package t4.facade;

import javax.ejb.Local;

import t4.entities.Login;
import t4.entities.Person;
import t4.entities.Role;

@Local
public interface FacadeLocal {
    public Login findByPersonId(String personId);
    public Login createLogin(Login login);
    public Login updateLogin(Login login);
    public void deleteLogin(String personId);
    
    public Person findPersonByPersonId(String personId);
    public Person createPerson(Person person);
    public Person updatePerson(Person person);
    public void deletePerson(String personId);
    
    public Role findRoleByRoleName(String roleName);
    public Role createRole(Role role);
    public Role updateRole(Role role);
    public void deleteRole(String roleName);
}
