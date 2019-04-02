package tamara.microservices.services.web;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Persistent cart entity with JPA markup. books are stored in an H2
 * relational database.
 * 
 * @author Tamara Melnikova
 */
@JsonRootName("Cart")
public class Cart {

	protected Long id;
	protected List<String> bookNumbers;
	protected String userNumber;
	protected String quantity;

	/**
	 * Default constructor for JPA only.
	 */
	protected Cart() {
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

	public List<String> getBookNumbers() {
		return bookNumbers;
	}

	protected void addBookNumber(String bookNumber) {
		this.bookNumbers.add(bookNumber);
	}

	public String getUserNumber() {
		return userNumber;
	}

	protected void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

/*	@Override
	public String toString() {
		return number + " (" + author + ") " + title;
	}
*/
}
