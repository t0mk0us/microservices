package tamara.microservices.books;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

public class BooksControllerTests extends AbstractBookControllerTests {

	protected static final Book theBook = new Book(BOOK_1,
			BOOK_1_AUTHOR, BOOK_1_TITLE);
	protected static final Cart theCart = new Cart(CART_1,
			CART_1_USER, CART_1_BOOK);

	protected static class TestBookRepository implements BookRepository {

		@Override
		public Book findByNumber(String bookNumber) {
			if (bookNumber.equals(BOOK_1))
				return theBook;
			else
				return null;
		}

		@Override
		public List<Book> findByAuthorContainingIgnoreCase(String partialName) {
			List<Book> books = new ArrayList<Book>();

			if (BOOK_1_AUTHOR.toLowerCase().indexOf(partialName.toLowerCase()) != -1)
				books.add(theBook);

			return books;
		}

		@Override
		public int countBooks() {
			return 1;
		}

		@Override
		public List<Book> findByTitleContainingIgnoreCase(String partialTiile) {
			// TODO Auto-generated method stub
			List<Book> books = new ArrayList<Book>();
			if (BOOK_1_TITLE.toLowerCase().indexOf(partialTiile.toLowerCase()) != -1)
				books.add(theBook);
			return books;
		}

		@Override
		public Book findByTitle(String title) {
			// TODO Auto-generated method stub
			Book book = new Book();
			if (BOOK_1_TITLE.toLowerCase().equals(title.toLowerCase()))
				return book;
			else return null;
		}
	}
	
	protected static class TestCartRepository implements CartRepository {

		@Override
		public List<Cart> findByUserNumber(String userNumber) {
			/*if (userNumber.equals(BOOK_1))
				return theBook;
			else
				return null;*/
			List<Cart> carts = new ArrayList<Cart>();
			return carts;
		}
		@Override
		public void saveCart(String unumber, String bnumber, String quantity) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void save(tamara.microservices.books.Cart cart) {
			// TODO Auto-generated method stub
			
		}
	}

	protected TestBookRepository testBookRepo = new TestBookRepository();
	protected TestCartRepository testCartRepo = new TestCartRepository();

	@Before
	public void setup() {
		bookController = new BooksController(testBookRepo, testCartRepo);
	}
}
