package practo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BaseClass {
    WebDriver driver;
    By city = By.xpath("//input[@data-qa-id='omni-searchbox-locality']");
    By city1 = By.xpath("//*[@id=\"c-omni-container\"]/div/div[1]/div[2]/div[2]/div[2]/span/div[1]");
    By remove_btn = By.xpath("//i[@class='icon-ic_cross_solid']");
    By speciality = By.xpath("//*[@id=\"c-omni-container\"]/div/div[2]/div[1]/input");
    String city_name;
    By filter=By.xpath("//*[@id=\"container\"]/div/div[3]/div/div/header/div[1]/div/div[4]/span/span");
    By radio_btn=By.xpath("//input[@id=\"Fees0\"]/following::div");
    By fees=By.xpath("//span[@data-qa-id=\"consultation_fee\"]");
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    public String selecting_city() throws Exception {
        WebElement area = driver.findElement(city);
        area.click();
        Thread.sleep(2000);
        driver.findElement(remove_btn).click();
        Thread.sleep(3000);
        area.sendKeys(prop.getProperty("city_name"));
        Thread.sleep(3000);
        city_name = driver.findElement(city1).getText();
        driver.findElement(city1).click();
        return city_name;
    }
    
    public void select_speciality() throws Exception {
        WebElement specialization1 = driver.findElement(speciality);
        specialization1.click();
        Thread.sleep(3000);
        specialization1.sendKeys(prop.getProperty("specialization"));
        Thread.sleep(3000);
        specialization1.sendKeys(Keys.ARROW_DOWN);
        specialization1.sendKeys(Keys.ARROW_DOWN);
        specialization1.sendKeys(Keys.ENTER);
        Thread.sleep(3000);
    }
    public List<WebElement> filtering() throws Throwable
    {
    	selecting_city();
    	select_speciality();
    	driver.findElement(filter).click();
    	Thread.sleep(3000);
    	driver.findElement(radio_btn).click();
    	Thread.sleep(3000);
    	List<WebElement> price_list=driver.findElements(fees);
    	return price_list;	
    }
}
