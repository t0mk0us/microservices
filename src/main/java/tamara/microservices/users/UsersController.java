package tamara.microservices.users;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tamara.microservices.users.User;
import tamara.microservices.users.UserRepository;
import tamara.microservices.users.UsersController;
import tamara.microservices.exceptions.UserNotFoundException;

/**
 * A RESTFul controller for accessing user information.
 * 
 * @author Tamara Melnikova
 */
@RestController
public class UsersController {

	protected Logger logger = Logger.getLogger(UsersController.class
			.getName());
	
	protected UserRepository userRepository;

	/**
	 * Create an instance plugging in the respository of users.
	 * 
	 * @param userRepository
	 *            An user repository implementation.
	 */
	@Autowired
	public UsersController(UserRepository userRepository) {
		this.userRepository = userRepository;

		logger.info("UserRepository says system has "
				+ userRepository.countUsers() + " users");
	}

	/**
	 * Fetch an user with the specified user number.
	 * 
	 * @param userNumber
	 *            A numeric, 9 digit user number.
	 * @return The user if found.
	 * @throws UserNotFoundException
	 *             If the number is not recognised.
	 */
	@RequestMapping("/users/{userNumber}")
	public User byNumber(@PathVariable("userNumber") String userNumber) {

		logger.info("users-service byNumber() invoked: " + userNumber);
		User user = (User) userRepository.findByNumber(userNumber);
		logger.info("service-service byNumber() found: " + user);

		if (user == null)
			throw new UserNotFoundException(userNumber);
		else {
			return user;
		}
	}
	
	/**
	 * Fetch users with the specified title . A partial case-insensitive match
	 * is supported. So <code>http://.../users/title/a</code> will find any
	 * users with upper or lower case 'a' in their name.
	 * 
	 * @param partialName
	 * @return A non-null, non-empty set of users.
	 * @throws UserNotFoundException
	 *             If there are no matches at all.
	 */
	@RequestMapping("/users/name/{name}")
	public List<User> byName(@PathVariable("title") String partialName) {
		logger.info("users-service bytitle() invoked: "
				+ userRepository.getClass().getName() + " for "
				+ partialName);

		@SuppressWarnings("unchecked")
		List<User> users = userRepository
				.findByNameContainingIgnoreCase(partialName);
		logger.info("users-service bytitle() found: " + users);

		if (users == null || users.size() == 0)
			throw new UserNotFoundException(partialName);
		else {
			return users;
		}
	}
}
