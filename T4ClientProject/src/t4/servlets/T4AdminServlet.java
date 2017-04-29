package t4.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javafx.scene.chart.PieChart.Data;
import t4.entities.Login;
import t4.entities.Person;
import t4.entities.Role;
import t4.facade.FacadeLocal;


@WebServlet("/T4AdminServlet")
public class T4AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private FacadeLocal facade;

    public T4AdminServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = null;
		try {
			String operation = request.getParameter("operation");
			
			switch (operation) {
			case "getAllLogins": 
				ArrayList<Login> allLogins = this.facade.findAllLogins();
				request.setAttribute("allLogins", allLogins);
				break;
			case "getLogin":
				String personAndLoginId = request.getParameter("personAndLoginId");
				Login login = this.facade.findLoginByPersonId(personAndLoginId);
				
				if (login != null) {
					request.setAttribute("login", login);
					
				} else {
					request.setAttribute("responseLabel", "INFO: Login not found");
				}
				break;
			
			case "getAllRoles":
				ArrayList<Role> allRoles = this.facade.findAllRoles();
				request.setAttribute("allRoles", allRoles);
				break;
			case "getRole":
				String roleId = request.getParameter("roleId");
				Role role = this.facade.findRoleByRoleName(roleId);
						
				if (role != null) {
					request.setAttribute("role", role);
					
				} else {
					request.setAttribute("responseLabel", "INFO: Role not found");
				}
				break;
			case "getAllPersons":
				ArrayList<Person> allPersons = this.facade.findAllPersons();
				request.setAttribute("allPersons", allPersons);
				break;
			case "getPerson":
				String personId = request.getParameter("personId");
				Person person = this.facade.findPersonByPersonId(personId);
				
				if (person != null) {
					request.setAttribute("person", person);
					
				} else {
					request.setAttribute("responseLabel", "INFO: Person not found");
				}
				break;
			case "createNewPerson":
				request.setAttribute("allRoles", this.facade.findAllRoles());
				request.setAttribute("editing", false);
				request.setAttribute("test", "test");
				url = "/PersonEditor.jsp";
				break;
				
			case "editPerson":
				personId = request.getParameter("personId");
				Person p = this.facade.findPersonByPersonId(personId);

				request.setAttribute("test", "test");
				request.setAttribute("allRoles", this.facade.findAllRoles());
				request.setAttribute("personToEdit", p);
				request.setAttribute("editing", true);
				url = "/PersonEditor.jsp";
				break;
			}
			
		} catch (Exception e) {
			
		}
	}
	

//	<input name="operation" value=${ editing ? "updateExistingPerson" : "saveNewPerson" } type="hidden"/>
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = null;
		
		try {
			String operation = request.getParameter("operation");
			
			// UPDATE/DELETE/CREATE Person
			if (operation.equals("personModification")) {
			
				Mode mode = null;				
				if (request.getParameter("deletePerson") != null) {
					mode = Mode.DELETE;
				} else if (request.getParameter("updatePerson") != null) {
					mode = Mode.UPDATE;
				} else if (request.getParameter("createPerson") != null) {
					mode = Mode.CREATE;
				}
				Person person = this.handlePersonModification(request, mode);
				url = this.fillRequestWithPersonInfoAndReturnUrl(person, request);
			} else {
				switch (operation) {
				
				case "goToPersonEditPage":
					
					boolean editing = request.getParameter("editing") != null;
					request.setAttribute("editing", editing);
					
					Person subject = null;
					if (editing) {					
						String personId = request.getParameter("selectedPerson");
						subject = null;
						if (personId != null) {
							subject = facade.findPersonByPersonId(personId);
						}
					}
					url = this.fillRequestWithPersonInfoAndReturnUrl(subject, request);
					break;
				
				case "goToPersonSelectorPage":
					url = "/PersonSelector.jsp";
					request.setAttribute("allPersons", facade.findAllPersons());					
					break;
					
				case "loginUser":
					String username = request.getParameter("username");
					String password = request.getParameter("password");
					Login login = facade.findLoginByPersonId(username);
					if (login != null && login.getPassword().equals(password)) {
						url = "/PersonSelector.jsp";
						request.setAttribute("loggedInUser", login.getPerson());
						request.setAttribute("allPersons", facade.findAllPersons());
					} else {
						url = "/IndexLogin.jsp";
						request.setAttribute("responseLabel",
								"ERROR: Login failed, either the credentials are wrong or the user does not have a login");
					}
					break;

				case "addLogin":
					String personId = request.getParameter("personId");
					password = request.getParameter("password");
					Login loginForPerson = facade.findLoginByPersonId(personId);

					if (loginForPerson == null) {
						Person person = facade.findPersonByPersonId(personId);
						loginForPerson = new Login();
						loginForPerson.setPerson(person);
						loginForPerson.setPassword(password);
						facade.createLogin(loginForPerson);
						request.setAttribute("responseMessage", "SUCCESS: Login created");
					} else {
						loginForPerson.setPassword(password);
						facade.updateLogin(loginForPerson);
						request.setAttribute("responseMessage", "SUCCESS: Login updated");
					}
					break;

				// ROLE
				case "addRole":
					Role role = new Role();
					role.setName(request.getParameter("roleName"));
					facade.createRole(role);
					request.setAttribute("responseMessage", "SUCCESS: Role created");
					break;
				}
			}

			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (Exception e) {
			throw e;
		}
	}
	
	private enum Mode {
		UPDATE, CREATE, DELETE
	}
	
	private Person handlePersonModification(HttpServletRequest request, Mode mode) {

		String personId = request.getParameter("personId");
		if (mode == Mode.DELETE) {
			if (!personId.equals("1")) {
				this.facade.deletePerson(personId);
			} else {
				request.setAttribute("responseLabel", "Cannot delete Admin \"1\"");
			}
		} else {
			Person person = new Person();
			person.setId(personId);
			person.setName(request.getParameter("personName"));
			person.setEmail(request.getParameter("personEmail"));
			person.setPhoneNbr(request.getParameter("personPhoneNbr"));

			System.out.println("****: " + personId);

			String roleName = request.getParameter("roleName");
			if (roleName != null) {
				Role role = facade.findRoleByRoleName(roleName);
				person.setRole(role);
			}

			if (mode == Mode.UPDATE) {
				return this.facade.updatePerson(person);
			} else if (mode == Mode.CREATE) {
				return this.facade.createPerson(person);
			}
		}
		return null;
	}

	private String fillRequestWithPersonInfoAndReturnUrl(Person person, HttpServletRequest request) {
		
		if (person != null) {
			request.setAttribute("personSubject", person);
			request.setAttribute("editing", true);
		} else {
			request.setAttribute("editing", false);
		}
		request.setAttribute("allRoles", facade.findAllRoles());
		String url = "/PersonEditor.jsp";
		return url;
	}

}
