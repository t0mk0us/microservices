package tamara.microservices.books;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Persistent book entity with JPA markup. books are stored in an H2
 * relational database.
 * 
 * @author Tamara Melnikova
 */
@Entity
@Table(name = "T_BOOK")
public class Book implements Serializable {

	private static final long serialVersionUID = 201903271L;

	public static Long nextId = 0L;

	@Id
	protected Long id;
	
	@Column(name = "number")
	protected String number;

	@Column(name = "author")
	protected String author;

	@Column(name = "title")
	protected String title;

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
	protected Book() {

	}

	public Book(String number, String author, String title) {
		id = getNextId();
		this.number = number;
		this.author = author;
		this.title = title;
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

	public String getAuthor() {
		return author;
	}

	protected void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return number + " (" + author + ") " + title;
	}

	public String getTitle() {
		// TODO Auto-generated method stub
		return title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
}
