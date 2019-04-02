package tamara.microservices.books;

import java.util.List;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;

import tamara.microservices.exceptions.BookNotFoundException;

// The following are equivalent, we only need to use one.

// 1. Read test properties from a file - neater when there are multiple properties
@TestPropertySource(locations = "classpath:book-controller-tests.properties")

// 2. Define test properties directly, acceptable here since we only have one.
// @TestPropertySource(properties={"eureka.client.enabled=false"})
public abstract class AbstractBookControllerTests {

	protected static final String BOOK_1 = "123456789";
	protected static final String BOOK_1_AUTHOR = "Ruth Rendell";
	protected static final String BOOK_1_TITLE = "The Water is Lovely";
	
	protected static final String CART_1 = "12345";
	protected static final String CART_1_USER = "123456";
	protected static final String CART_1_BOOK = "123456789";

	@Autowired
	BooksController bookController;

	@Test
	public void validBookNumber() {
		Logger.getGlobal().info("Start validBookNumber test");
		Book book = bookController.byNumber(BOOK_1);

		Assert.assertNotNull(book);
		Assert.assertEquals(BOOK_1, book.getNumber());
		Assert.assertEquals(BOOK_1_AUTHOR, book.getAuthor());
		Logger.getGlobal().info("End validBook test");
	}

	@Test
	public void validAccountOwner() {
		Logger.getGlobal().info("Start validBook test");
		List<Book> books = (List<Book>) bookController.byAuthor(BOOK_1_AUTHOR);
		Logger.getGlobal().info("In validBook test");

		Assert.assertNotNull(books);
		Assert.assertEquals(1, books.size());

		Book book = books.get(0);
		Assert.assertEquals(BOOK_1, book.getNumber());
		Assert.assertEquals(BOOK_1_AUTHOR, book.getAuthor());
		Assert.assertEquals(BOOK_1_TITLE, book.getTitle());
		Logger.getGlobal().info("End validBook test");
	}

	@Test
	public void validBookAuthorMatches1() {
		Logger.getGlobal().info("Start validBook test");
		List<Book> books = (List<Book>) bookController.byAuthor("Rendell");
		Logger.getGlobal().info("In validBook test");

		Assert.assertNotNull(books);
		Assert.assertEquals(1, books.size());

		Book book = books.get(0);
		Assert.assertEquals(BOOK_1, book.getNumber());
		Assert.assertEquals(BOOK_1_AUTHOR, book.getAuthor());
		Assert.assertEquals(BOOK_1_TITLE, book.getTitle());
		Logger.getGlobal().info("End validBook test");
	}

	@Test
	public void validBookAuthorMatches2() {
		Logger.getGlobal().info("Start validBook test");
		List<Book> books = (List<Book>) bookController.byAuthor("Reichs");
		Logger.getGlobal().info("In validBook test");

		Assert.assertNotNull(books);
		Assert.assertEquals(1, books.size());

		Book book = books.get(0);
		Assert.assertEquals(BOOK_1, book.getNumber());
		Assert.assertEquals(BOOK_1_AUTHOR, book.getAuthor());
		Assert.assertEquals(BOOK_1_TITLE, book.getTitle());
		Logger.getGlobal().info("End validAccount test");
	}

	@Test
	public void invalidBookNumber() {
		try {
			bookController.byNumber("10101010");
			Assert.fail("Expected an BookNotFoundException");
		} catch (BookNotFoundException e) {
			// Worked!
		}
	}

	@Test
	public void invalidBookAuthor() {
		try {
			bookController.byAuthor("Fred Smith");
			Assert.fail("Expected an BookNotFoundException");
		} catch (BookNotFoundException e) {
			// Worked!
		}
	}
}
