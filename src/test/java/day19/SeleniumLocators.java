package day19;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumLocators {
	public static WebDriver driver;
	/* DROPDOWN HANDLING */
	public static void dropDownHande(WebElement element, String value) {
		Select s = new Select(element);
		s.selectByVisibleText(value);
	}
	/* WINDOW HANDLING */
	public static void switchToWindow(WebDriver driver) {
		String parent = driver.getWindowHandle();
		Set<String> allwindows = driver.getWindowHandles();
		for (String window : allwindows) {
			if (!window.equals(parent)) {
				driver.switchTo().window(window);
				break;
			}
		}
	}
	/* FRAMES HANDLING*/
	public static void switchTOFrame(WebDriver driver, WebElement frameelement) {
		driver.switchTo().frame(frameelement);
		
	}
	public static void main(String[] args) throws Exception {
		driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		System.out.println("Title is : "+driver.getTitle());
		System.out.println("Current URL is : "+driver.getCurrentUrl());

		// Locator ID
		driver.findElement(By.id("autocomplete")).sendKeys("ind");
		
		// Locator CLASS NAME
		List<WebElement> menuitems = driver.findElements(By.className("ui-menu-item"));
		menuitems.stream().filter(a -> a.getText().equalsIgnoreCase("India")).findFirst().orElse(null).click();
		
		// Locator NAME
		driver.findElement(By.name("radioButton")).click();
		
		// Locator XPATH
		driver.findElement(By.xpath("//input[@value='option2']")).click();
		
		// Locator TAGNAME
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("Total Links: " + links.size());
		
		WebElement drop = driver.findElement(By.id("dropdown-class-example"));
		dropDownHande(drop, "Option2");
		
		/*ALERT HANDLING*/
		driver.findElement(By.xpath("//input[@value='Alert']")).click();
		Alert a = driver.switchTo().alert();
		System.out.println("ALERT: "+a.getText());
		a.accept();
		
		/*JAVA SCRIPT EXECUTOR TO SCROLL*/
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,900)");
		
		/* ACTION CLASS MOUSE HOVER*/
		WebElement hover = driver.findElement(By.id("mousehover"));
		Actions a1 = new Actions(driver);
		a1.moveToElement(hover).build().perform();
		
			
		//swithching frame
		WebElement element = driver.findElement(By.id("courses-iframe"));
		switchTOFrame(driver, element);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter your name']"))).sendKeys("VENUGOPAL");
		driver.switchTo().defaultContent();
		
		// Locator CSS SELECTOR
		driver.findElement(By.cssSelector("#openwindow")).click();
		switchToWindow(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='button float-left']"))).click();
		driver.close();
		
		// Locator LINK TEXT
				driver.findElement(By.linkText("Free Access to InterviewQues/ResumeAssistance/Material")).click();
				Thread.sleep(5000);
				// selenium NAVIGATION keyword
				driver.navigate().back();
				// Locator PARTIAL LINK TEXT
				driver.findElement(By.partialLinkText("Free Access to")).click();
				Thread.sleep(3000);
				driver.navigate().back();
				
		/*NAVIGATION METHODS*/
		driver.navigate().refresh();
		Thread.sleep(3000);
		driver.navigate().back();
		Thread.sleep(3000);
		driver.navigate().forward();
	}

}
