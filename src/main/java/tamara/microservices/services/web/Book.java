package tamara.microservices.services.web;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Book DTO - used to interact with the {@link WebBooksService}.
 * 
 * @author Paul Chapman
 */
@JsonRootName("Book")
public class Book {

	protected Long id;
	protected String number;
	protected String author;
	protected String title;

	/**
	 * Default constructor for JPA only.
	 */
	protected Book() {
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
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return number + " (" + author + ") " + title;
	}

}
