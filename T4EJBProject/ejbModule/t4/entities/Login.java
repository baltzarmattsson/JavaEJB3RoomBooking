package t4.entities;

import java.io.Serializable;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="Login")
public class Login implements Serializable {

	private Person person;
	private String password;

	public Login() {
	}

	public Login(Person person, String password) {
		super();
		this.person = person;
		this.password = password;
	}

	@Id
	@OneToOne
	@JoinColumn(name="personID", referencedColumnName="id")
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Column(name="password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
