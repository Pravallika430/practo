package practo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseClass {
    static ExtentSparkReporter htmlreport;
    protected static ExtentReports report;
    protected static ExtentTest test;
    protected static WebDriver driver;
    static Properties prop = new Properties();
    protected By city1 = By.xpath("//*[@id=\"c-omni-container\"]/div/div[1]/div[2]/div[2]/div[2]/span/div[1]");
    protected By displayed_city_ele = By.xpath("//*[@id=\"container\"]/div/div[4]/div/div[1]/div/div[1]/div[1]/h1");
    @BeforeSuite
    public static void report() {
        htmlreport = new ExtentSparkReporter(new File("C:\\Users\\PravallikaT\\Documents\\eclipse_practice\\practo.html"));
        htmlreport.config().setReportName("saucelabs cucumber");
        htmlreport.config().setDocumentTitle("saucelabs Test");
        htmlreport.config().setTheme(Theme.DARK);
        report = new ExtentReports();
        report.setSystemInfo("Environment", "TestEnv");
        report.setSystemInfo("TesterName", "ABC");
        report.attachReporter(htmlreport);
    }

    @BeforeMethod
    @Parameters({"x"})
    public static void Browser_Setup(String p) throws IOException {
        FileInputStream file = new FileInputStream("C:\\Users\\PravallikaT\\Documents\\eclipse_practice\\Practo\\src\\main\\java\\config\\TestData.properties");
        prop.load(file);
        file.close();
        if(p.matches("edge"))
        {
        	driver=new EdgeDriver();
        }
        if(p.matches("chrome"))
        {
        	driver=new ChromeDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
    }

    public static void Open_URL() {
        driver.get(prop.getProperty("url"));
    }

    public static void close() {
        
            driver.quit();
        
    }
    @AfterSuite
    public static void saveReport()
    {
    	report.flush();
    }
}
