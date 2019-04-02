package tamara.microservices.services.web;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

public class SearchCriteria {
	private String bookNumber;

	private String searchText;
	
	private String searchTitle;
	
	private String userNumber;
	
	private String userName;

	public String getBookNumber() {
		return bookNumber;
	}

	public void setBookNumber(String bookNumber) {
		this.bookNumber = bookNumber;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	
	public String getSearchTitle() {
		return searchTitle;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setSearchTitle(String searchTitle) {
		this.searchTitle = searchTitle;
	}

	public boolean isValid() {
		if (StringUtils.hasText(bookNumber))
			return !(StringUtils.hasText(searchText));
		else
			return (StringUtils.hasText(searchText) || StringUtils.hasText(searchTitle));
	}

	public boolean validate(Errors errors) {
		if (StringUtils.hasText(bookNumber)) {
			if (bookNumber.length() != 9)
				errors.rejectValue("bookNumber", "badFormat",
						"Book number should be 9 digits");
			else {
				try {
					Integer.parseInt(bookNumber);
				} catch (NumberFormatException e) {
					errors.rejectValue("bookNumber", "badFormat",
							"Book number should be 9 digits");
				}
			}

			if (StringUtils.hasText(searchText) || StringUtils.hasText(searchTitle)) {
				errors.rejectValue("searchText", "nonEmpty",
						"Cannot specify book number and author name and/or book title");
			}
		} else if (StringUtils.hasText(searchText)) {
			; // Nothing to do
		} else if (StringUtils.hasText(searchTitle)) {
			; // Nothing to do
		}else {
			errors.rejectValue("bookNumber", "nonEmpty",
					"Must specify either a book number or author name or book title");

		}

		return errors.hasErrors();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return (StringUtils.hasText(bookNumber) ? "number: " + bookNumber
				: "")
				+ (StringUtils.hasText(searchText) ? " text: " + searchText
						: "");
	}
}
