package t4.entities;

import java.io.Serializable;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Role")
public class Role implements Serializable {

	private String name;

	public Role() {
	}

	public Role(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Id
	@Column(name="name")
	public String getName() {
		return this.name;
	}
}
