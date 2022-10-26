package Academy.Tests;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Academy.TestComponents.BaseTest;
import Academy.TestComponents.Retry;
import Academy.pageobjects.CartPage;
import Academy.pageobjects.ProductCatelogue;

public class ErrorValidation extends BaseTest {
	@Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
	public void WrongLogin() throws IOException {

		ProductCatelogue productCatelogue = landing.loginApplication("abc123456@gmail.com", "Abc");
		Assert.assertEquals(landing.getErrorMsg(), "Incorrect email or password."); 
	}
	
	@Test
	public void ProductErrorValidation() throws IOException {
		String productName = "ADIDAS ORIGINAL";

		ProductCatelogue productCatelogue = landing.loginApplication("abc1234567@gmail.com", "Abc12345");
		List<WebElement> products = productCatelogue.getProductList();
		productCatelogue.addProductToCart(productName);
		CartPage cartPage = productCatelogue.goToCartPage(); //method defined in parent class, child class inherit it.
		Boolean match = cartPage.verifyProductDisplay("ADIDAS ORIGINAL22");
		Assert.assertFalse(match); //assertion method need to be in test class.
	}
}
