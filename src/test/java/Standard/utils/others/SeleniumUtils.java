package Standard.utils.others;

import Standard.factory.WebDriverFactory;
import Standard.utils.exceptions.ExceptionUtils;
import Standard.utils.readers.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumUtils {

	WebElement webElement;
	static Integer timeToBrooke = Integer.parseInt(Config.getProperty("time.to.brooke"));

	public static WebElement getWebElement(String xpath) {
		return WebDriverFactory.getCurrentRunningDriver().findElement(By.xpath(xpath));
	}

	public static List<WebElement> getWebElements(String xpath) {
		return WebDriverFactory.getCurrentRunningDriver().findElements(By.xpath(xpath));
	}

	public static Select getSelect(String xpath) {
		return new Select(getWebElement(xpath));
	}

	public static void waitWebElementVisible(String xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(WebDriverFactory.getCurrentRunningDriver(), timeToBrooke);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		} catch (Exception exception) {
			ExceptionUtils.throwException(exception);
		}
	}

	public static void waitWebElementClickable(String xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(WebDriverFactory.getCurrentRunningDriver(), timeToBrooke);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		} catch (Exception exception) {
			ExceptionUtils.throwException(exception);
		}
	}

	public static void waitWebElementSelectable(String xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(WebDriverFactory.getCurrentRunningDriver(), timeToBrooke);
			wait.until(ExpectedConditions.elementToBeSelected(By.xpath(xpath)));
		} catch (Exception exception) {
			ExceptionUtils.throwException(exception);
		}
	}

	public static void waitAlertPresent() {
		try {
			WebDriverWait wait = new WebDriverWait(WebDriverFactory.getCurrentRunningDriver(), timeToBrooke);
			wait.until(ExpectedConditions.alertIsPresent());
		} catch (Exception exception) {
			ExceptionUtils.throwException(exception);
		}
	}

	public static void sendText(String texto, String xpath) {
		waitWebElementVisible(xpath);
		getWebElement(xpath).clear();
		getWebElement(xpath).sendKeys(texto);
	}

	public static void click(String xpath) {
		waitWebElementClickable(xpath);
		getWebElement(xpath).click();
	}

	public static void selectCombo(String value, String xpath) {
		waitWebElementSelectable(xpath);
		getSelect(xpath).selectByValue(value);
	}

	public static void switchJanela() {
		for (String handle : WebDriverFactory.getCurrentRunningDriver().getWindowHandles()) {
			WebDriverFactory.getCurrentRunningDriver().switchTo().window(handle);
		}
	}

	public static void switchToDefault() {
		WebDriverFactory.getCurrentRunningDriver().switchTo().defaultContent();
	}

	public static WebDriver switchToFrame(String xpath) {
		return WebDriverFactory.getCurrentRunningDriver().switchTo().frame(getWebElement(xpath));
	}

	public static void wait(int time) {
		try {
			Thread.sleep(time);
		} catch (Exception exception) {
			ExceptionUtils.throwException(exception);
		}
	}

	public static String getTextoAlert() {
		wait(2000);
		waitAlertPresent();
		return WebDriverFactory.getCurrentRunningDriver().switchTo().alert().getText();
	}

	public static void alertAccept() {
		waitAlertPresent();
		WebDriverFactory.getCurrentRunningDriver().switchTo().alert().accept();
	}

	public static void alertCancel() {
		waitAlertPresent();
		WebDriverFactory.getCurrentRunningDriver().switchTo().alert().dismiss();
	}

	public static boolean isAlert() {
		try {
			WebDriverFactory.getCurrentRunningDriver().switchTo().alert();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static String getUrl() {
		return WebDriverFactory.getCurrentRunningDriver().getCurrentUrl();
	}

	public static void maximizeWindow() {
		WebDriverFactory.getCurrentRunningDriver().manage().window().maximize();
	}

	public static boolean isWebElement(String xpath) {
		boolean status = false;
		WebDriverFactory.getCurrentRunningDriver().manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);
		try {
			SeleniumUtils.getWebElement(xpath);
			status = true;
		} catch (Exception exception) {
			// silence
		}
		WebDriverFactory.getCurrentRunningDriver().manage().timeouts().implicitlyWait(timeToBrooke, TimeUnit.SECONDS);
		return status;
	}

	public static void centralize(WebElement... elements) {
		for (WebElement element : elements) {
			((JavascriptExecutor) WebDriverFactory.getCurrentRunningDriver())
					.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", element);
			break;
		}
	}

}