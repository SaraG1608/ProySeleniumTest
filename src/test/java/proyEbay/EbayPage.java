package proyEbay;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EbayPage extends Base{
	 
	By searchLocator = By.id("gh-ac"); 
	By searchBtnLocator = By.id("gh-btn");
	By searchResultLocator = By.className("srp-controls__count-heading");
	
	By pumaBrandLocator = By.cssSelector("li[data-idx='5']");
	By sizeTenLocator = By.cssSelector("input[aria-label='10']");
	
	By dropdownBtnLocator = By.id("w9");
	By dropdownLocator = By.className("srp-sort__menu");
	By dropdownItemLocator = By.xpath("//a[contains(@href,'dcat=93427&_sop')]");
		
	By gridLocator = By.cssSelector("ul[class='srp-results srp-grid clearfix']");	
	By productTextLocator = By.className("s-item__title");
	By productPrecioLocator =By.xpath("//div[@class='s-item__detail s-item__detail--primary']/span[@class='s-item__price']");
			//By.xpath("/html/body/div/div[5]/div[2]/div/div[2]/ul/li/div/div[2]/div[3]/div/span");//By.cssSelector("span[class='s-item__price']");
	
	
	public EbayPage(WebDriver driver) {
		super(driver);
	}
	
	public void searchEbay(String inputText) throws InterruptedException {
		//Input shoes
		type(inputText,searchLocator);
		Thread.sleep(2000);
		//click on "Buscar"
		click(searchBtnLocator);
		assertTrue(isDisplayed(searchResultLocator));		
	}
	
	public void filterEbay() throws InterruptedException {
		
		List<String> nameProdsAssert =  new ArrayList<String>();
		List<String> nameProdsFirst =  new ArrayList<String>();
		List<Producto> productsEbay = new ArrayList<Producto>();
		
		nameProdsFirst.add("Tenis Puma Negros");
		nameProdsFirst.add("Puma Adultos Unisex POPCAT Verano Playa Deportes Correa Diapositiva Sandalias Zapatos Blanco..");
		//nameProdsFirst.add("Puma Adultos Unisex leadcat Verano Playa Deportes Correa Diapositiva Sandalias Zapatos Negro.");
		nameProdsFirst.add("Las diapositivas del gato Puma plomo (36026302) Deportes Sandalias Flip Flop Zapatos Zapatillas diapositiva");
		nameProdsFirst.add("Las diapositivas del gato Puma plomo (36026301) Deportes Sandalias Flip Flop Zapatos Zapatillas diapositiva");
		nameProdsFirst.add("Las diapositivas del gato Puma plomo (36026308) Deportes Sandalias Flip Flop Zapatos Zapatillas diapositiva");
		//prods.add("Puma Cat diapositivas (36026321) Lead Deportes Sandalias Flip Flop Zapatos Zapatillas diapositiva");
		
		//Selección marca Puma
		click(pumaBrandLocator);
		Thread.sleep(3000);
		click(sizeTenLocator);
		Thread.sleep(3000);
		mouse_hover(dropdownBtnLocator);
		selectDropdownList("Precio + Envío: más bajo primero",dropdownLocator,dropdownItemLocator);
		Thread.sleep(2000);
		assertTrue(isDisplayed(searchResultLocator));		
		System.out.println(getText(searchResultLocator));
		System.out.print("\n");
		
		nameProdsAssert = assertProductsFirst(nameProdsFirst,gridLocator,productTextLocator);
		productsEbay= getProductsEbay(nameProdsAssert, gridLocator,productPrecioLocator);
		
		System.out.println("The 5 First Products");
		for(Producto elemento:productsEbay) {
			System.out.println(elemento);
		}
		System.out.print("\n");
		
		System.out.println("Order by Name - Ascendant");
		Collections.sort(nameProdsAssert);
		for(int i=0; i<nameProdsAssert.size();i++){
			System.out.println(nameProdsAssert.get(i));
		}
		
		System.out.print("\n");
		System.out.println("Order by Price - Descendant");
		Collections.sort(productsEbay,Collections.reverseOrder());
		for(Producto elemento:productsEbay) {
			System.out.println(elemento);
		}
	}
	
	
}
