package tamara.microservices.users;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Persistent user entity with JPA markup. books are stored in an H2
 * relational database.
 * 
 * @author Tamara Melnikova
 */
@Entity
@Table(name = "T_USER")
public class User implements Serializable {

	private static final long serialVersionUID = 201903281L;

	public static Long nextId = 0L;

	@Id
	protected Long id;
	
	@Column(name = "number")
	protected String number;

	@Column(name = "name")
	protected String name;

	@Column(name = "password")
	protected String password;

	/**
	 * This is a very simple, and non-scalable solution to generating unique
	 * ids. Not recommended for a real application. Consider using the
	 * <tt>@GeneratedValue</tt> annotation and a sequence to generate ids.
	 * 
	 * @return The next available id.
	 */
	protected static Long getNextId() {
		synchronized (nextId) {
			return nextId++;
		}
	}

	/**
	 * Default constructor for JPA only.
	 */
	protected User() {

	}

	public User(String number, String name, String password) {
		id = getNextId();
		this.number = number;
		this.name = name;
		this.password = password;
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

	@Override
	public String toString() {
		return number + " (" + name + ") ";
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
}

