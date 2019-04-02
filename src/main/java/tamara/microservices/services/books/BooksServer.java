package tamara.microservices.services.books;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

import tamara.microservices.books.BookRepository;
import tamara.microservices.books.Book;
import tamara.microservices.books.BooksConfiguration;

/**
 * Run as a micro-service, registering with the Discovery Server (Eureka).
 * <p>
 * Note that the configuration for this application is imported from
 * {@link BooksConfiguration}. This is a deliberate separation of concerns.
 * <p>
 * This class declares no beans and current package contains no components for
 * ComponentScan to find. No point using <tt>@SpringBootApplication</tt>.
 * 
 * @author Tamara Melnikova
 */
@EnableAutoConfiguration
@EnableDiscoveryClient
@Import(BooksConfiguration.class)
public class BooksServer {

	@Autowired
	protected BookRepository bookRepository;

	protected Logger logger = Logger.getLogger(BooksServer.class.getName());

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		// Tell server to look for books-server.properties or
		// books-server.yml
		System.setProperty("spring.config.name", "books-server");

		SpringApplication.run(BooksServer.class, args);
	}
}
