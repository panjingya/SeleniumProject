package Academy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Academy.AbstractComponents.AbastractComponent;

public class CheckoutPage extends AbastractComponent{

	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".form-group input")
	WebElement countryElement;
	
	@FindBy(css=".ta-item.list-group-item.ng-star-inserted")
	WebElement countrySelect;
	
	public void selectCountry(String countryName) {
		waitForElementToAppear(By.cssSelector(".form-group input"));
		JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
		jsExecutor.executeScript("window.scrollBy(0,200)");
		countryElement.sendKeys(countryName);
		waitForElementToAppear(By.cssSelector(".ta-results"));
		countrySelect.click();
	}
	
	@FindBy(css=".actions a")
	WebElement submitElement;
	
	public ConfirmationPage submitOrder() {
		submitElement.click();
		ConfirmationPage confirmPage = new ConfirmationPage(driver);
		return confirmPage;
	}

}
