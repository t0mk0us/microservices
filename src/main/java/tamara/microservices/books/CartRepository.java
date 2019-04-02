package tamara.microservices.books;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository for Cart data implemented using Spring Data JPA.
 * 
 * @author Tamara Melnikova
 */
public interface CartRepository extends Repository<Cart, Long> {
	/**
	 * Find an book with the specified book number.
	 *
	 * @param cartNumber
	 * @return The cart if found, null otherwise.
	 */
	
	public List<Cart> findByUserNumber(String userNumber);

	@Modifying
	@Query (value = "insert into Cart (user_number, book_number, quantity) VALUES (:unumber, :bnumber, :quantity)", nativeQuery = true)	
	@Transactional
	public void saveCart(@Param("unumber") String unumber, @Param("bnumber") String bnumber, @Param ("quantity") String quantity);

	void save(Cart cart);
}
