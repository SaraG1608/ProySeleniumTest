package proyEbay;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Base {
	
	private WebDriver driver;
	
	public Base(WebDriver driver) {
		this.driver =driver;
	}
	
	public WebDriver ChromeDriverConnection() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Chromedriver/chromedriver.exe");
		driver = new ChromeDriver(); 	
		driver.manage().window().maximize();
	return driver;
	}
	public void cerrar() {
		driver.quit();
	}
	public WebElement findElement (By locator){
	return driver.findElement(locator);
	}

	public List<WebElement> findElements(By locator){
	return driver.findElements(locator);
	}

	public String getText(WebElement element){
	return element.getText();
	}

	public String getText(By locator){
	return driver.findElement(locator).getText();
	}

	public void type(String inputText, By locator){
	driver.findElement(locator).sendKeys(inputText);
	}

	public void click(By locator){
	driver.findElement(locator).click();
	}
		
	public void click(WebElement element){
		element.click();
	}
	
	public Boolean isDisplayed(By locator){
		try{
			return driver.findElement(locator).isDisplayed();
			
		} catch(org.openqa.selenium.NoSuchElementException e){
		return false;
		}
	}

	public void visit(String url){
	driver.get(url);
	}
	
	public void mouse_hover(By locator) {
		WebElement combo = driver.findElement(locator);
		Actions action = new Actions(driver); 
		action.moveToElement(combo).perform();
	}
	
	public void selectDropdownList(String optionText, By locator_main, By locator_item)
	{
		WebElement dropdownList = findElement(locator_main);
		
		List<WebElement> list = dropdownList.findElements(locator_item);
		
		for(int i=0; i<list.size();i++){
			if(getText(list.get(i)).equals(optionText))
			{   
				click(list.get(i));
				break;				
			}
		}
	
	}
	
	public List<String> assertProductsFirst(List<String> listProds, By locator_main, By locator_item) {
		
		List<String> prodsAssertEbay =  new ArrayList<String>();
		
		WebElement gridList = findElement(locator_main);
		List<WebElement> gridItems = gridList.findElements(locator_item);
		
		for(int i=0; i<listProds.size();i++){
			assertEquals(listProds.get(i),getText(gridItems.get(i)));
			prodsAssertEbay.add(getText(gridItems.get(i)));
			}
	return prodsAssertEbay;
	}
	
	public List<Producto> getProductsEbay(List<String> listProds, By locator_main, By locator_price) {
		WebElement gridList = findElement(locator_main);
		List<WebElement> priceItems = gridList.findElements(locator_price);
		List<Producto> products = new ArrayList<Producto>();
				
		for(int i=0; i<5;i++){
			Producto p = new Producto(listProds.get(i),Double.parseDouble(getText(priceItems.get(i)).substring(4,9)));
			products.add(p);
		}
			
		return products;
	}
	

	

}
