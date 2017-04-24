package t4.eao;

import java.util.ArrayList;

import javax.ejb.Local;

import t4.entities.Role;

@Local
public interface RoleEAOLocal {
	public ArrayList<Role> findAllRoles();
    public Role findRoleByRoleName(String roleName);
    public Role createRole(Role role);
    public Role updateRole(Role role);
    public void deleteRole(String roleName);
}
