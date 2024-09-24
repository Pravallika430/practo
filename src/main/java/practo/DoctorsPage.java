package practo;

import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DoctorsPage {
    WebDriver driver;
    By doctor = By.xpath("//*[@id=\"c-omni-container\"]/div/div[2]/div[1]/input");
    By doctors = By.xpath("//button[contains(.,\"Book Clinic Visit\")]");
    By actual_doctors=By.xpath("//div[@data-qa-id=\"doctor_card\"]");
    By dentists = By.xpath("//div[@data-qa-id=\"doctor_card\"]/div/div[2]/div[1][contains(.,\"Dentist\")]");
    By day = By.xpath("//div[@class='u-pos-rel c-slots-header__daybar ']/div");

    public DoctorsPage(WebDriver driver) {
        this.driver = driver;
    }

    public int[] checking_displayed_doctors() {
        List<WebElement> total_doctors = driver.findElements(actual_doctors);
        List<WebElement> dentist_list = driver.findElements(dentists);
        return new int[] { total_doctors.size(), dentist_list.size() };
    }
    
    
    
    public String select_doctor() throws Exception
    {
        List<WebElement> doctors_list = driver.findElements(doctors);
        Random r = new Random();
        int num = r.nextInt(doctors_list.size());
        doctors_list.get(num).click();
        List<WebElement> doctor_names = driver.findElements(By.xpath("//h2[@class=\"doctor-name\"]"));
        String selected_doctor_name = doctor_names.get(num-1).getText(); // Get the doctor's name at the random index
        return selected_doctor_name;
    }
    public String select_slot() throws Exception
	{
		List<WebElement> day_list=driver.findElements(day);
		Thread.sleep(3000);
		String text="No Slots Available";
		for (int i = 0; i < day_list.size(); i++)
		{
		    String text1 = driver.findElement(By.xpath("//div[@class='u-pos-rel c-slots-header__daybar ']/div[" + (i + 1) + "]/div[2]")).getText();
		    if (!text.equals(text1))
		    {
		        driver.findElement(By.xpath("//div[@class='u-pos-rel c-slots-header__daybar ']/div[" + (i + 1) + "]/div[1]")).click();
		        break;
		    }
		}
		Thread.sleep(3000);
		List<WebElement> slots_list=driver.findElements(By.xpath("//div[@data-qa-id=\"slot_time\"]"));
		String slot_time=slots_list.get(0).getText();
		slots_list.get(0).click();
		slot_time = slot_time.replaceFirst("^0", "");
		return slot_time;
	}
}
