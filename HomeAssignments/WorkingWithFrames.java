package week4.day1.HomeAssignments;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class WorkingWithFrames {

	public static void main(String[] args) {
		
		/* Precondition
		 *  - Initialize ChromeDriver 
		 *  - Load the URL (https://www.w3schools.com/js/tryit.asp?filename=tryjs_prompt) 
		 *  - Maximize the browser window 
		 *  - Switch to the frame 
		 *  - Add an implicit wait to ensure the webpage elements are fully loaded 
		 */
		
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.w3schools.com/js/tryit.asp?filename=tryjs_confirm");
		driver.manage().window().maximize();
		driver.switchTo().frame("iframeResult");
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
		
		
		//Click the "Try It" button inside the frame 
		
		driver.findElement(By.xpath("//button[text()='Try it']")).click();
		
		// Click OK/Cancel in the alert that appears 
		
		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();
		
		//Confirm the action is performed correctly by verifying the text displayed 
		
		String text = driver.findElement(By.xpath("//p[@id='demo']")).getText();
		System.out.println(text);
		
		if(text.equals("You pressed OK!"))
			System.out.println("Verification Successful");
		else
			System.out.println("Verification failure");
		
		driver.close();

	}

}
