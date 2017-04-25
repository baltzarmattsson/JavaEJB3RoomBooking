package t4.entities;

import java.io.Serializable;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Person")
public class Person implements Serializable {

	private String id;
	private String name;
	private String email;
	private String phoneNbr;
	private Role roleName;

	public Person() {
	}

	public Person(String id, String name, String email, String phoneNbr, Role roleName) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phoneNbr = phoneNbr;
		this.roleName = roleName;
	}

	@Id
	@Column(name="id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="phoneNbr")
	public String getPhoneNbr() {
		return phoneNbr;
	}

	public void setPhoneNbr(String phoneNbr) {
		this.phoneNbr = phoneNbr;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="rolename", referencedColumnName="name")
	public Role getRole() {
		return roleName;
	}

	public void setRole(Role roleName) {
		this.roleName = roleName;
	}
}
