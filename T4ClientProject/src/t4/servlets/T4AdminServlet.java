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
				url = "/EditorSelector.jsp";
				break;
				
			case "editPerson":
				personId = request.getParameter("personId");
				Person p = this.facade.findPersonByPersonId(personId);

				request.setAttribute("test", "test");
				request.setAttribute("allRoles", this.facade.findAllRoles());
				request.setAttribute("personToEdit", p);
				request.setAttribute("editing", true);
				url = "/EditorSelector.jsp";
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
				
			// DELETE/CREATE ROLE
			} else if (operation.equals("roleModification")) {
				
				Mode mode = null;
				if (request.getParameter("deleteRole") != null) {
					mode = Mode.DELETE;
				} else if (request.getParameter("createRole") != null) {
					mode = Mode.CREATE;
				}
				Role role = this.handleRoleModification(request, mode);
				url = this.fillRequestWithRoleInfoAndReturnUrl(role, request);
				
			} else if (operation.equals("loginModification")) { 
			
				Mode mode = null;
				if (request.getParameter("deleteLogin") != null) {
					mode = Mode.DELETE;
				} else if (request.getParameter("createLogin") != null) {
					mode = Mode.CREATE;
				}
				Login login = this.handleLoginModification(request, mode);
				url = this.fillRequestWithPersonInfoAndReturnUrl(login == null ? null : login.getPerson(), request);
				
			}else {
				switch (operation) {
				
				case "goToPersonEditPage":
					
					boolean editing = request.getParameter("editing") != null;
					request.setAttribute("editing", editing);
					
					Person personSubject = null;
					if (editing) {					
						String personId = request.getParameter("selectedPerson");
						if (personId != null) {
							personSubject = facade.findPersonByPersonId(personId);
						}
					}
					url = this.fillRequestWithPersonInfoAndReturnUrl(personSubject, request);
					break;
					
				case "goToRoleEditPage":
					
					editing = request.getParameter("editing") != null;
					request.setAttribute("editing", editing);
					
					Role roleSubject = null;
					if (editing) {
						String roleName = request.getParameter("selectedRole");
						if (roleName != null) {
							roleSubject = facade.findRoleByRoleName(roleName);
						}
					}
					url = this.fillRequestWithRoleInfoAndReturnUrl(roleSubject, request);
					break;					
				
				case "goToEditorSelectorPage":
					url = "/EditorSelector.jsp";
					request.setAttribute("allPersons", facade.findAllPersons());	
					request.setAttribute("allRoles", facade.findAllRoles());					
					break;
					
				case "loginUser":
					String username = request.getParameter("username");
					String password = request.getParameter("password");
					Login login = facade.findLoginByPersonId(username);
					if (login != null && login.getPassword().equals(password)) {
						url = "/EditorSelector.jsp";
						request.setAttribute("loggedInUser", login.getPerson());
						request.setAttribute("allPersons", facade.findAllPersons());
						request.setAttribute("allRoles", facade.findAllRoles());	
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
		if (personId != null) {
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
		}
		return null;
	}

	private String fillRequestWithPersonInfoAndReturnUrl(Person person, HttpServletRequest request) {
		
		if (person != null) {
			request.setAttribute("personSubject", person);
			request.setAttribute("editing", true);
			
			Login login = facade.findLoginByPersonId(person.getId());
			if (login != null) {
				request.setAttribute("loginSubject", login);
				request.setAttribute("loginExists", true);
			} else {
				request.setAttribute("loginExists", false);
			}
			
		} else {
			request.setAttribute("editing", false);
		}
		request.setAttribute("allRoles", facade.findAllRoles());
		String url = "/PersonEditor.jsp";
		return url;
	}
	
	private Role handleRoleModification(HttpServletRequest request, Mode mode) {

		String roleName = request.getParameter("roleName");
		if (roleName != null) {
			if (mode == Mode.DELETE) {
				this.facade.deleteRole(roleName);
			} else {
				Role role = new Role();
				role.setName(roleName);

				if (mode == Mode.UPDATE) {
					return this.facade.updateRole(role);
				} else if (mode == Mode.CREATE) {
					return this.facade.createRole(role);
				}
			}
		}
		return null;
	}


	private String fillRequestWithRoleInfoAndReturnUrl(Role role, HttpServletRequest request) {

		if (role != null) {
			request.setAttribute("roleSubject", role);
			request.setAttribute("editing", true);
		} else {
			request.setAttribute("editing", false);
		}
		String url = "/RoleEditor.jsp";
		return url;
	}

	private Login handleLoginModification(HttpServletRequest request, Mode mode) {

		String personId = request.getParameter("username");
		Person loginOwner = facade.findPersonByPersonId(personId);
		if (loginOwner != null) {
			if (mode == Mode.DELETE) {
				this.facade.deleteLogin(personId);
			} else {
				String password, confirmPassword;
				password = request.getParameter("password");
				confirmPassword = request.getParameter("confirmPassword");
				if (password.equals(confirmPassword)) {
					Login login = new Login();
					login.setPerson(loginOwner);
					login.setPassword(request.getParameter("password"));

					if (mode == Mode.UPDATE) {
						return this.facade.updateLogin(login);
					} else if (mode == Mode.CREATE) {
						return this.facade.createLogin(login);
					}
				} else {
					request.setAttribute("responseLabel", "Passwords doesnt match");
				}
			}

		} else {
			request.setAttribute("responseLabel", "Could not find owner (person)");
		}
		return null;
	}
}
