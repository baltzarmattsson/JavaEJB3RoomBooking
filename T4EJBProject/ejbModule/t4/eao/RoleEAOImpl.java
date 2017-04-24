package t4.eao;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import t4.entities.Person;
import t4.entities.Role;

/**
 * Session Bean implementation class RoleEAOImpl
 */
@Stateless
public class RoleEAOImpl implements RoleEAOLocal {

	@PersistenceContext(unitName = "MSSQLT4")
	private EntityManager em;
	
	public RoleEAOImpl() {
	}
	
	public ArrayList<Role> findAllRoles() {
		Query query = em.createQuery("select r from Role r");
		return (ArrayList<Role>) query.getResultList();
	}

	public Role findRoleByRoleName(String roleName) {
		return em.find(Role.class, roleName);
	}

	public Role createRole(Role role) {
		em.persist(role);
		return role;
	}

	public Role updateRole(Role role) {
		em.merge(role);
		return role;
	}

	public void deleteRole(String roleName) {
		Role r = this.findRoleByRoleName(roleName);
		if (r != null)
			em.remove(r);
	}

}
