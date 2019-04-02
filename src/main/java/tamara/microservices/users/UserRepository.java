package tamara.microservices.users;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import tamara.microservices.users.User;

public interface UserRepository extends Repository<User, Long> {
	/**
	 * Find an user with the specified user number.
	 *
	 * @param userNumber
	 * @return The user if found, null otherwise.
	 */
	public User findByNumber(String userNumber);
	
	/**
	 * Find users whose author name contains the specified string
	 * 
	 * @param partialName
	 *            Any alphabetic string.
	 * @return The list of matching users - always non-null, but may be
	 *         empty.
	 */
	public List<User> findByNameContainingIgnoreCase(String partialName);

	/**
	 * Fetch the number of users known to the system.
	 * 
	 * @return The number of users.
	 */
	@Query("SELECT count(*) from User")
	public int countUsers();
}
