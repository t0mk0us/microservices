package tamara.microservices.services.web;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Book DTO - used to interact with the {@link WebBooksService}.
 * 
 * @author Tamara Melnikova
 */
@JsonRootName("Book")
public class User {

	protected Long id;
	protected String number;
	protected String name;
	protected String password;

	/**
	 * Default constructor for JPA only.
	 */
	protected User() {
	}

	public long getId() {
		return id;
	}

	/**
	 * Set JPA id - for testing and JPA only. Not intended for normal use.
	 * 
	 * @param id
	 *            The new id.
	 */
	protected void setId(long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	protected void setNumber(String bookNumber) {
		this.number = bookNumber;
	}

	public String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return number + " (" + name + ") ";
	}

}
