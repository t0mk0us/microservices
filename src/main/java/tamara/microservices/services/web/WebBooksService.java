package tamara.microservices.services.web;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import tamara.microservices.books.Cart;
import tamara.microservices.exceptions.BookNotFoundException;

/**
 * Hide the access to the microservice inside this local service.
 * 
 * @author Tamara Melnikova
 */
@Service
public class WebBooksService {

	@Autowired
	@LoadBalanced
	protected RestTemplate restTemplate;

	protected String serviceUrl;

	protected Logger logger = Logger.getLogger(WebBooksService.class
			.getName());

	public WebBooksService(String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl
				: "http://" + serviceUrl;
	}

	/**
	 * The RestTemplate works because it uses a custom request-factory that uses
	 * Ribbon to look-up the service to use. This method simply exists to show
	 * this.
	 */
	@PostConstruct
	public void demoOnly() {
		// Can't do this in the constructor because the RestTemplate injection
		// happens afterwards.
		logger.warning("The RestTemplate request factory is "
				+ restTemplate.getRequestFactory().getClass());
	}

	public Book findByNumber(String bookNumber) {

		logger.info("findByNumber() invoked: for " + bookNumber);
		return restTemplate.getForObject(serviceUrl + "/books/{number}",
				Book.class, bookNumber);
	}

	public List<Book> byAuthorContains(String name) {
		logger.info("byAuthorContains() invoked:  for " + name);
		Book[] books = null;

		try {
			books = restTemplate.getForObject(serviceUrl
					+ "/books/author/{name}", Book[].class, name);
		} catch (HttpClientErrorException e) { // 404
			// Nothing found
		}

		if (books == null || books.length == 0)
			return null;
		else
			return Arrays.asList(books);
	}

	public Book getByNumber(String bookNumber) {
		Book book = restTemplate.getForObject(serviceUrl
				+ "/books/{number}", Book.class, bookNumber);

		if (book == null)
			throw new BookNotFoundException(bookNumber);
		else
			return book;
	}

	public List<Book> byTitleContains(String title) {
		logger.info("byTitleContains() invoked:  for " + title);
		Book[] books = null;

		try {
			books = restTemplate.getForObject(serviceUrl
					+ "/books/title/{title}", Book[].class, title);
		} catch (HttpClientErrorException e) { // 404
			// Nothing found
		}

		if (books == null || books.length == 0)
			return null;
		else
			return Arrays.asList(books);
	}

	public void addToCart(String bookNumber, String userNumber) {
	    final String uri = "serviceUrl" + "/cart/{userNumber}";
		 //List<tamara.microservices.services.web.Cart> carts = getCartForUser(userNumber);
		 Cart cart = new Cart(bookNumber, userNumber, "1");
		 RestTemplate restTemplate = new RestTemplate();
		 //if(carts == null) {			 
			 Cart result = restTemplate.postForObject( uri, cart, Cart.class);
		 //}
	    System.out.println(result);
	}
	
	public List<tamara.microservices.services.web.Cart> getCartForUser(String userNumber) {
		logger.info("getCartForUser() invoked:  for " + userNumber);
		tamara.microservices.services.web.Cart[] carts = null;
	
		try {
			carts = restTemplate.getForObject(serviceUrl
					+ "/carts/{userNumber}", tamara.microservices.services.web.Cart[].class, userNumber);
		} catch (HttpClientErrorException e) { // 404
			// Nothing found
		}
	
		if (carts == null || carts.length == 0)
			return null;
		else
			return Arrays.asList(carts);
	}
}
