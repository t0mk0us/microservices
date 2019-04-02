package tamara.microservices.services.web;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

public class ModifCriteria {
	private String bookNumber;

	//private String searchText;
	
	//private String searchTitle;
	
	private String userNumber;

	public String getBookNumber() {
		return bookNumber;
	}

	public void setBookNumber(String bookNumber) {
		this.bookNumber = bookNumber;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
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
		}else {
			errors.rejectValue("bookNumber", "nonEmpty",
					"Must specify either a book number or author name or book title");

		}

		return errors.hasErrors();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return (StringUtils.hasText(bookNumber) ? "booknumber: " + bookNumber
				: "")
				+ (StringUtils.hasText(userNumber) ? " usernumber: " + userNumber
						: "");
	}
}
