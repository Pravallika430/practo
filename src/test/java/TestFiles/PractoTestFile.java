package TestFiles;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;

import practo.AppointmentPage;
import practo.BaseClass;
import practo.DoctorsPage;
import practo.HomePage;
public class PractoTestFile extends BaseClass {
    HomePage homepage;
    DoctorsPage doctorspage;
    By slot = By.xpath("//*[@id='container']/div[2]/div/div[1]/div/div[1]/div[2]/div[1]/div[2]/span[2]");
    By doctor_name = By.xpath("//*[@id='container']/div[2]/div/div[1]/div/div[1]/div[3]/div/div[2]/div[1]");
    By date_ele=By.xpath("/html/body/div/div/div[2]/div/div[1]/div/div[1]/div[2]/div[1]/div[1]/span[2]");
    @BeforeMethod
    public void Initializing_Browser() throws Exception {
        BaseClass.Open_URL();
        homepage = new HomePage(driver);
        doctorspage = new DoctorsPage(driver);
    }
   @Test(priority = 1)
    public void verify_category_list_of_doctors() throws Exception {
        test = report.createTest("Test 1 - Validate Speciality of All Doctors");
        String selected_city = homepage.selecting_city();
        homepage.select_speciality();
        int[] doctors_list = doctorspage.checking_displayed_doctors();
        System.out.println(doctors_list[0]);
        System.out.println(doctors_list[1]);
        if (doctors_list[0] == doctors_list[1]) {
            test.log(Status.PASS, "All doctors are of the same speciality Dentist");
            System.out.println("Doctors matched");
        }
        String displayed_city = driver.findElement(displayed_city_ele).getText();
        if (displayed_city.contains(selected_city)) {
            test.log(Status.PASS, "City also matched");
            System.out.println("City also matched");
        }
    }
   
    @Test(priority = 2)
    public void verify_doctor_details_timeslot_date() throws Exception {
        test = report.createTest("Test 2 - Book Slot For Visiting Clinic");
    	BaseClass.Open_URL();
    	homepage.selecting_city();
    	homepage.select_speciality();
    	String selected_doctor_name = doctorspage.select_doctor();
    	String selected_slot = doctorspage.select_slot();
    	String actual_slot=driver.findElement(slot).getText();
    	System.out.println(actual_slot);
    	String actual_doctor_name=driver.findElement(doctor_name).getText();
    	System.out.println(actual_doctor_name);
    	
    	WebElement dateElement = driver.findElement(date_ele);
 	    String date1 = dateElement.getText();  
 	    System.out.println(date1);
 	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH);  
 	    LocalDate actual_date = LocalDate.parse(date1, formatter);
    	LocalDate today = LocalDate.now();   
    	LocalDate tomorrow = today.plusDays(1);
    	if(actual_doctor_name.matches(selected_doctor_name)) 
    	{
    		test.log(Status.PASS, "Doctor name is matched");
    		System.out.println("Doctor name is matched");	
    	}
    	else
    	{
    		test.log(Status.FAIL,"Doctor name  matched");
    	}
    	if(actual_slot.matches(selected_slot))
    	{
    		test.log(Status.PASS, "slot is matched");
    	}
    	else
    	{
    		test.log(Status.FAIL, "slot is not matched");
    	}
    	if(actual_date.equals(today))
    	{
    		test.log(Status.PASS,"Date matched");
    		System.out.println("date matched");
    	}
    	else if(actual_date.equals(tomorrow))
    	{
    		test.log(Status.PASS,"Date matched");
    		System.out.println("date matched tomorrow");
    	}
    	else
    	{
    		String selected_date=doctorspage.get_selected_date();
            DateTimeFormatter formate = DateTimeFormatter.ofPattern("EEE, dd MMM", Locale.ENGLISH);
            LocalDate date = LocalDate.parse(selected_date + " 2024", formate);
            DateTimeFormatter actual_date1 = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH);
     	    LocalDate booked_date = LocalDate.parse(date1, formatter);
     	    if(selected_date.equals(booked_date))
     	    {
     	    	test.log(Status.PASS,"Date matched");
     	    }
    	}  
    }
    
    @Test(priority = 3)
    public void verify_filtering() throws Throwable {
        test = report.createTest("Test 3 - Checking the filtration functionality");
        List<WebElement> price_list = homepage.filtering();
        for (int i = 0; i < price_list.size(); i++) {
            String priceText = price_list.get(i).getText().replaceAll("[^0-9]", ""); 
            int price = Integer.parseInt(priceText);
            Assert.assertTrue(price<500,"Filter functionality is applied not correctly");  
        }
        test.log(Status.PASS,"Filter functionality is applied correctly");        
    }
    
    @AfterMethod
    public void Closing_Browser() {
        close();
    }   
}