package t4.eao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

	public Role findByRoleName(String roleName) {
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
		Role r = this.findByRoleName(roleName);
		if (r != null)
			em.remove(r);
	}

}
