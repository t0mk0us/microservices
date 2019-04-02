package tamara.microservices.books;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tamara.microservices.exceptions.BookNotFoundException;

/**
 * A RESTFul controller for accessing book information.
 * 
 * @author Tamara Melnikova
 */
@RestController
public class BooksController {

	protected Logger logger = Logger.getLogger(BooksController.class
			.getName());
	
	protected BookRepository bookRepository;
	protected CartRepository cartRepository;

	/**
	 * Create an instance plugging in the respository of books.
	 * 
	 * @param bookRepository
	 *            An book repository implementation.
	 */
	@Autowired
	public BooksController(BookRepository bookRepository, CartRepository cartRepository) {
		this.bookRepository = bookRepository;
		this.cartRepository = cartRepository;

		logger.info("BookRepository says system has "
				+ bookRepository.countBooks() + " books");
	}

	/**
	 * Fetch an book with the specified book number.
	 * 
	 * @param bookNumber
	 *            A numeric, 9 digit book number.
	 * @return The book if found.
	 * @throws BookNotFoundException
	 *             If the number is not recognised.
	 */
	@RequestMapping("/books/{bookNumber}")
	public Book byNumber(@PathVariable("bookNumber") String bookNumber) {

		logger.info("books-service byNumber() invoked: " + bookNumber);
		Book book = (Book) bookRepository.findByNumber(bookNumber);
		logger.info("service-service byNumber() found: " + book);

		if (book == null)
			throw new BookNotFoundException(bookNumber);
		else {
			return book;
		}
	}

	/**
	 * Fetch books with the specified autor name. A partial case-insensitive match
	 * is supported. So <code>http://.../books/author/a</code> will find any
	 * books with upper or lower case 'a' in their name.
	 * 
	 * @param partialName
	 * @return A non-null, non-empty set of books.
	 * @throws BookNotFoundException
	 *             If there are no matches at all.
	 */
	@RequestMapping("/books/author/{name}")
	public List<Book> byAuthor(@PathVariable("name") String partialName) {
		logger.info("books-service byauthor() invoked: "
				+ bookRepository.getClass().getName() + " for "
				+ partialName);

		@SuppressWarnings("unchecked")
		List<Book> books = bookRepository
				.findByAuthorContainingIgnoreCase(partialName);
		logger.info("books-service byauthor() found: " + books);

		if (books == null || books.size() == 0)
			throw new BookNotFoundException(partialName);
		else {
			return books;
		}
	}
	
	/**
	 * Fetch books with the specified title . A partial case-insensitive match
	 * is supported. So <code>http://.../books/title/a</code> will find any
	 * books with upper or lower case 'a' in their name.
	 * 
	 * @param partialName
	 * @return A non-null, non-empty set of books.
	 * @throws BookNotFoundException
	 *             If there are no matches at all.
	 */
	@RequestMapping("/books/title/{title}")
	public List<Book> byTitle(@PathVariable("title") String partialName) {
		logger.info("books-service bytitle() invoked: "
				+ bookRepository.getClass().getName() + " for "
				+ partialName);

		@SuppressWarnings("unchecked")
		List<Book> books = bookRepository
				.findByTitleContainingIgnoreCase(partialName);
		logger.info("books-service bytitle() found: " + books);

		if (books == null || books.size() == 0)
			throw new BookNotFoundException(partialName);
		else {
			return books;
		}
	}
	
	@RequestMapping("/books/addtocart/{bookNumber}{usernumber}")
	public void addToCart(@PathVariable("booknumber") String booknumber, @PathVariable("usernumber") String usernumber){
	
		Cart cart = new Cart();
		
		cart.setBookNumber(booknumber);
		cart.setUserNumber(usernumber);
		cart.setQuantity("1");
		
		cartRepository.save(cart);
	}		
}
