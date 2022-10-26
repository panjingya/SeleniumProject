package Academy.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.AddHasCasting;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Academy.TestComponents.BaseTest;
import Academy.pageobjects.CartPage;
import Academy.pageobjects.CheckoutPage;
import Academy.pageobjects.ConfirmationPage;
import Academy.pageobjects.OrdersPage;
import Academy.pageobjects.ProductCatelogue;

public class StandAloneTest2 extends BaseTest {
	@Test(dataProvider="getData",groups = {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException {
		ProductCatelogue productCatelogue = landing.loginApplication(input.get("email"), input.get("pwd"));
		List<WebElement> products = productCatelogue.getProductList();
		productCatelogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatelogue.goToCartPage(); //method defined in parent class, child class inherit it.
		Boolean match = cartPage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match); //assertion method need to be in test class.
		CheckoutPage checkoutPage = cartPage.checkout();
		checkoutPage.selectCountry("canada");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMsg = confirmationPage.getConfirmMsg();
		Assert.assertEquals(confirmMsg, "THANKYOU FOR THE ORDER.");
	}
	
	@DataProvider
	public Object[][] getData() throws IOException{
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+
											"\\src\\test\\java\\Academy\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
	
	//To verify ADIDAS ORIGINAL is displaying in orders page
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest() {
		ProductCatelogue productCatelogue = landing.loginApplication("abc123456@gmail.com", "Abc12345");
		OrdersPage order = productCatelogue.goToOrdersPage();
		Boolean match = order.VerifyOrderDisplay("ADIDAS ORIGINAL");
		Assert.assertTrue(match);
	}
}
