package tamara.microservices.books;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

/**
 * The books Spring configuration.
 * 
 * @author Tamara Melnikova
 * 
 */
@Configuration
@ComponentScan
@EntityScan("tamara.microservices.books")
@EnableJpaRepositories("tamara.microservices.books")
@PropertySource("classpath:db-config.properties")
public class BooksConfiguration {

	protected Logger logger;

	public BooksConfiguration() {
		logger = Logger.getLogger(getClass().getName());
	}

	/**
	 * Creates an in-memory "books" database populated with test data for fast
	 * testing
	 */
	@Bean
	
	public DataSource dataSource() {
		logger.info("dataSource() invoked");

		// Create an in-memory H2 relational database containing some demo
		// books.
		DataSource dataSource = (new EmbeddedDatabaseBuilder()).addScript("classpath:testdb/schema.sql")
				.addScript("classpath:testdb/data.sql").build();

		logger.info("dataSource = " + dataSource);

		// Sanity check
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> books = jdbcTemplate.queryForList("SELECT number FROM T_BOOK");
		logger.info("System has " + books.size() + " books");

		return dataSource;
	}
}
