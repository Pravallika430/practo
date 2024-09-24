package practo;
 
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
 
public class AppointmentPage extends BaseClass
{
	public static LocalDate validateDate()
	{
	    
	    WebElement dateElement = driver.findElement(By.xpath("//*[@id=\"container\"]/div[2]/div/div[1]/div/div[1]/div[2]/div/div"));
	    String dateText = dateElement.getText();  
	    
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);  
	    LocalDate Date = LocalDate.parse(dateText, formatter);
	    return Date;
	}
}
	
 