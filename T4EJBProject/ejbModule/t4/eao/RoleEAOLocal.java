package t4.eao;

import javax.ejb.Local;

import t4.entities.Role;

@Local
public interface RoleEAOLocal {
    public Role findByRoleName(String roleName);
    public Role createRole(Role role);
    public Role updateRole(Role role);
    public void deleteRole(String roleName);
}
