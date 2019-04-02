package tamara.microservices.services.web;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tamara.microservices.services.web.Book;

/**
 * Client controller, fetches Book info from the microservice via
 * {@link WebBooksService}.
 * 
 * @author Tamara Melikova
 */
@Controller
public class WebBooksController {

	@Autowired
	protected WebBooksService booksService;

	protected Logger logger = Logger.getLogger(WebBooksController.class
			.getName());

	public WebBooksController(WebBooksService booksService) {
		this.booksService = booksService;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields("bookNumber", "searchText", "searchTitle", "userNumber");
	}

	@ModelAttribute(value = "c")
	public ModifCriteria newCriteria()
	{
	    return new ModifCriteria();
	}
	
	@RequestMapping(value = "/books/domodifs", method = RequestMethod.POST)
	public String updateForm(Model model) {
		model.addAttribute("modifCriteria", new ModifCriteria());
		return "bookSearch";
	}
	
	@RequestMapping("/books")
	public String goHome() {
		return "index";
	}

	@RequestMapping("/books/{bookNumber}")
	public String byNumber(Model model,
			@PathVariable("bookNumber") String bookNumber, @ModelAttribute("modifCriteria") ModifCriteria criteria) {

		String userNumber = criteria.getUserNumber();
		String bookNum = criteria.getBookNumber();
		Book book = booksService.findByNumber(bookNumber);
		logger.info("web-service byNumber() found: " + book);
		model.addAttribute("book", book);
		model.addAttribute("bookNumber", bookNum);
		model.addAttribute("userNumber", userNumber);
		return "book";
	}

	@RequestMapping("/books/author/{searchText}")
	public String authorSearch(Model model, @PathVariable("searchText") String name) {
		logger.info("web-service byauthor() invoked: " + name);

		List<Book> books = booksService.byAuthorContains(name);
		logger.info("web-service byauthor() found: " + books);
		model.addAttribute("search", name);
		if (books != null)
			model.addAttribute("books", books);
		return "books";
	}
	
	@RequestMapping("/books/title/{searchTitle}")
	public String titleSearch(Model model, @PathVariable("searchTitle") String title, @ModelAttribute("modifCriteria") ModifCriteria criteria) {
		logger.info("web-service bytitle() invoked: " + title);

		List<Book> books = booksService.byTitleContains(title);
		logger.info("web-service bytitle() found: " + books);
		model.addAttribute("search", title);
		if (books != null)
			model.addAttribute("books", books);
		return "books";
	}


	@RequestMapping(value = "/books/search", method = RequestMethod.GET)
	public String searchForm(Model model) {
		model.addAttribute("searchCriteria", new SearchCriteria());
		return "bookSearch";
	}

	@RequestMapping(value = "/books/dosearch")
	public String doSearch(Model model, SearchCriteria criteria,
			BindingResult result) {
		logger.info("web-service dosearch() invoked: " + criteria);

		criteria.validate(result);

		if (result.hasErrors())
			return "bookSearch";
		
		ModifCriteria modifCriteria = new ModifCriteria();

		String bookNumber = criteria.getBookNumber();
		String searchText = criteria.getSearchText();
		String searchTitle = criteria.getSearchTitle();
		String userNumber = criteria.getUserNumber();
		modifCriteria.setBookNumber(bookNumber);
		modifCriteria.setUserNumber(userNumber);
		if (StringUtils.hasText(bookNumber)) {
			return byNumber(model, bookNumber, modifCriteria);
		} else if (StringUtils.hasText(searchText)){			
			return authorSearch(model, searchText);
		} else {			
			return titleSearch(model, searchTitle, modifCriteria);
		}
	}
	
	@RequestMapping(value = "/books/domodif", method = RequestMethod.POST)
	public void doModif(Model model, ModifCriteria criteria, BindingResult result){
		logger.info("web-service modif() invoked: " + criteria);
		model.addAttribute("ModifCriteria", new ModifCriteria());
		
		String bookNumber = criteria.getBookNumber();
		String userNumber = criteria.getUserNumber();		
		
		addToCart(model, bookNumber, userNumber);		
	}
	
	@RequestMapping(value = "/books/addtocart/{bookNumber}{userNumber}", method = RequestMethod.POST)
	public void addToCart(Model model, @PathVariable("bookNumber") String bookNumber, @PathVariable("userNumber") String userNumber) {
		logger.info("web-service addToCart() invoked: " + bookNumber + " " + userNumber);

		//model.addAttribute("modifCriteria", new ModifCriteria());
		booksService.addToCart(bookNumber, userNumber);
	}
	
	@RequestMapping(value = "/user/user/{cartNumber}")
	public String cartSearch(Model model, User user)
	{
		List<Cart> carts = booksService.getCartForUser(user.getNumber());
		logger.info("web-service bytitle() found: " + carts);
		model.addAttribute("search", user.getNumber());
		if (carts != null)
			model.addAttribute("carts", carts);
		return "carts";
	}	
}
