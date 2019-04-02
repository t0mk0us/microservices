package tamara.microservices.books;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository for Book data implemented using Spring Data JPA.
 * 
 * @author Tamara Melnikova
 */
public interface BookRepository extends Repository<Book, Long> {
	/**
	 * Find an book with the specified book number.
	 *
	 * @param bookNumber
	 * @return The book if found, null otherwise.
	 */
	//@Query("from Book where number='bookNumber'")
	public Book findByNumber(String bookNumber);
	
	//@Query("from Book where title='title'")
	public Book findByTitle(String title);

	/**
	 * Find books whose author name contains the specified string
	 * 
	 * @param partialName
	 *            Any alphabetic string.
	 * @return The list of matching books - always non-null, but may be
	 *         empty.
	 */
	//@Query("from Book where author like 'partialName'")
	public List<Book> findByAuthorContainingIgnoreCase(String partialName);
	
	/**
	 * Find books whose author name contains the specified string
	 * 
	 * @param partialName
	 *            Any alphabetic string.
	 * @return The list of matching books - always non-null, but may be
	 *         empty.
	 */
	//@Query("from Book where title like 'partialTitle'")
	public List<Book> findByTitleContainingIgnoreCase(String partialTitle);

	/**
	 * Fetch the number of books known to the system.
	 * 
	 * @return The number of books.
	 */
	@Query("SELECT count(*) from Book")
	public int countBooks();
}
