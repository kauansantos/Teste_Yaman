package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import factory.WebDriverFactory;

public class Hooks {

	@Before(order = 0)
	public void setUp() {
		/* Navegador_Chrome */
		WebDriverFactory.startChromeDriver();
		
		/* Navegador_Firefox */
		//WebDriverFactory.startFirefoxDriver();
	}

	@After(order = 1)
	public void tearDown() {
		WebDriverFactory.killCurentRunningDriver();
	}
}
