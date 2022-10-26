package Academy.pageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Academy.AbstractComponents.AbastractComponent;

public class OrdersPage extends AbastractComponent{

	WebDriver driver;
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//tr[@class='ng-star-inserted']/td[2]")
	List<WebElement> orderNames;
	
	public Boolean VerifyOrderDisplay(String productName) {
		//Get all product name into one list -> Stream -> match
		Boolean match = orderNames.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
	}
}
