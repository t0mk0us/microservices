package tamara.microservices.books;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home page controller.
 * 
 * @author Tamara Melnikova
 */
@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}

}
