package pos;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pos.Header;

public class ProductPage extends Base {

	String simpleTestProductUrl = "batest-simple";
	String configTestProductUrlColor = "batest-config4";
	By simpleAddToCart = By.cssSelector("#product_addtocart_form > div.add-to-cart-wrapper > div.add-to-box > div > div.add-to-cart-buttons > button");
	By configAddToCart = By.cssSelector("#product_addtocart_form > div.product-shop > div.product-options-bottom > div.add-to-cart > div.add-to-cart-buttons > button");
	
	public ProductPage(WebDriver driver) {
		super(driver);
	}
	
	public void goToSimpleTestProductPage() throws IOException {
		visit(simpleTestProductUrl);
	}
	
	public void goToColorConfigTestProductPage() throws IOException {
		visit(configTestProductUrlColor);
	}
	
	public Boolean simpleAddToCartButtonDisplayed() {
		return waitForIsDisplayed(simpleAddToCart);
	}
	
	public Header addSimpleToCart() {
		click(simpleAddToCart);
		return new Header(driver);
	}
}
