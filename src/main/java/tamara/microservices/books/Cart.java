package tamara.microservices.books;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;

/**
 * Persistent cart entity with JPA markup. books are stored in an H2
 * relational database.
 * 
 * @author Tamara Melnikova
 */
@Entity
@Table(name = "T_CART")
public class Cart implements Serializable {

	private static final long serialVersionUID = 201903271L;

	public static Long nextId = 0L;

	@Id
	protected Long id;
	
	@Column(name = "book_number")
	protected String bookNumber;

	@Column(name = "user_number")
	protected String userNumber;

	@Column(name = "quantity")
	protected String quantity;

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
	protected Cart() {

	}

	public Cart(String bookNumber, String userNumber, String quantity) {
		id = getNextId();
		this.bookNumber = bookNumber;
		this.userNumber = userNumber;
		this.quantity = quantity;
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

	public String getBookNumber() {
		return bookNumber;
	}

	public void setBookNumber(String bookNumber) {
		this.bookNumber = bookNumber;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return bookNumber + " (" + userNumber + ") " + quantity;
	}
}
