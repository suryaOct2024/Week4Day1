package week4.day1.HomeAssignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WindowHandling {

	public static void main(String[] args) throws InterruptedException {
		
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
		
		//Enter the username and password.
		
		driver.findElement(By.id("username")).sendKeys("DemoCSR");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		
		//Click on the Login button. 
		
		driver.findElement(By.className("decorativeSubmit")).click();
		
		// Click on the CRM/SFA link.
		
		driver.findElement(By.linkText("CRM/SFA")).click();
		
		//Click on the Contacts button. 
		
		driver.findElement(By.linkText("Contacts")).click();
		
		//Click on Merge Contacts. 
		
		driver.findElement(By.linkText("Merge Contacts")).click();
		
		//Click on the widget of the "From Contact". 
		
		driver.findElement(By.xpath("(//img[@alt='Lookup'])[1]")).click();
		
		//Click on the first resulting contact.
		//switch to From Contact window
		
		Set<String> windows = driver.getWindowHandles();
		List<String> listOfWindows = new ArrayList<String>(windows);
		driver.switchTo().window(listOfWindows.get(1));
		driver.manage().window().maximize();
		String contactId = driver.findElement(By.xpath("(//div[text()='Contact ID']/following::table)[1]/tbody/tr[1]/td[1]")).getText();
		System.out.println("First Contact ID:"+contactId);
		driver.close();
		
		// Click on the widget of the "To Contact". 
		//Switch to the main window --> window1
		
		driver.switchTo().window(listOfWindows.get(0));
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//img[@alt='Lookup'])[2]")).click();
		Set<String> windows1 = driver.getWindowHandles();
		List<String> listOfWindows1 = new ArrayList<String>(windows1);
		
		//Click on the second resulting contact. 
		//switch to "To contact window"
		
		driver.switchTo().window(listOfWindows1.get(1));
		Thread.sleep(1000);
		driver.manage().window().maximize();
		WebElement contact2 = driver.findElement(By.xpath("(//div[text()='Contact ID']/following::table)[2]/tbody/tr[1]/td[1]/div/a"));
		String contactId2 = contact2.getText();
		System.out.println("Second Contact ID:"+contactId2);
		contact2.click();
		
		//Click on the Merge button. 
		//Switch to main window
		
		driver.switchTo().window(listOfWindows1.get(0));
		WebElement mergeWE = driver.findElement(By.xpath("//table[@class='twoColumnForm']/tbody/tr[4]/td[2]/a"));
		if(mergeWE.getText().equals("Merge"))
			mergeWE.click();
		
		// Accept the alert.
		
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
		// Verify the title of the page. 
		
		System.out.println("Title of the webpage:"+driver.getTitle());
		if(driver.getTitle().equals("Merge Contacts | opentaps CRM"))
			System.out.println("Title of the Webpage - verified successfully");
		else
			System.out.println("Incorrect title - Please check the Webpage");
		
	}

}
