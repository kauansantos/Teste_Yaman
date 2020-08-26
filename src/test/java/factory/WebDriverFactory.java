package factory;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import util.selenium.Config;

import java.awt.*;
import java.util.concurrent.TimeUnit;
import util.selenium.InfraUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class WebDriverFactory {

    private static final Long TIME_OUT;
    private static final DesiredCapabilities DC;
    private static final Color COLOR_BACKGROUND = Color.WHITE;

    static {
        TIME_OUT = Long.parseLong(Config.getProperty("time.to.pageload.timeout"));
        DC = new DesiredCapabilities();
        DC.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
    }

    public static WebDriver driver;

    public static void startChromeDriver() {

        if(InfraUtils.getOsName().contains("Windows")) {
            System.setProperty("java.net.preferIPv4Stack", "true");
            System.setProperty("webdriver.chrome.driver", "src/main/resources/lib/chromedriver.exe");
            DesiredCapabilities dc = new DesiredCapabilities();
            dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--test-type");
            chromeOptions.addArguments("--start-maximized");
            chromeOptions.addArguments("--ignore-certificate-errors");
            chromeOptions.addArguments("--enable-popup-blocking");
            chromeOptions.addArguments("--disable-infobars");
            chromeOptions.addArguments("--disable-default-apps");
            chromeOptions.addArguments("--disable-extensions");
            chromeOptions.addArguments("--javaEnabled");
            chromeOptions.addArguments("--applicationCacheEnabled");
            chromeOptions.addArguments("--lang=pt-BR");
            chromeOptions.addArguments("--incognito");
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("enable-automation");

            /*TESTES MOBILE RESPONSIVO*/
            /*Map<String, Object> deviceMetrics = new HashMap<>();
            deviceMetrics.put("width", 360);
            deviceMetrics.put("height", 640);
            deviceMetrics.put("pixelRatio", 3.0);
            Map<String, Object> mobileEmulation = new HashMap<>();
            mobileEmulation.put("deviceMetrics", deviceMetrics);
            mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");
            chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);*/

            driver = new ChromeDriver(chromeOptions);
            driver.manage().deleteAllCookies();

        } else if(InfraUtils.getOsName().contains("Mac OS X")) {
            //System.setProperty("java.net.preferIPv4Stack", "true");
            System.setProperty("webdriver.chrome.driver", "src/main/resources/lib/chromedriverMac");
            DesiredCapabilities dc = new DesiredCapabilities();
            dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
            ChromeOptions chromeOptions = new ChromeOptions();
            //
            chromeOptions.addArguments("--window-size=1366,768");
            //chromeOptions.addArguments("--headless");
            //
            chromeOptions.addArguments("--test-type");
            chromeOptions.addArguments("--whitelisted-ips");
            chromeOptions.addArguments("--start-maximized");
            chromeOptions.addArguments("--ignore-certificate-errors");
            chromeOptions.addArguments("--enable-popup-blocking");
            chromeOptions.addArguments("--disable-infobars");
            chromeOptions.addArguments("--disable-default-apps");
            chromeOptions.addArguments("--disable-extensions");
            chromeOptions.addArguments("--javaEnabled");
            chromeOptions.addArguments("--applicationCacheEnabled");
            chromeOptions.addArguments("--lang=pt-BR");
            chromeOptions.addArguments("--incognito");
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("enable-automation");

            /*TESTES MOBILE RESPONSIVO*/
            /*Map<String, Object> deviceMetrics = new HashMap<>();
            deviceMetrics.put("width", 360);
            deviceMetrics.put("height", 640);
            deviceMetrics.put("pixelRatio", 3.0);
            Map<String, Object> mobileEmulation = new HashMap<>();
            mobileEmulation.put("deviceMetrics", deviceMetrics);
            mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");
            chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);*/

            driver = new ChromeDriver(chromeOptions);
            driver.manage().deleteAllCookies();


        }else{
            System.setProperty("java.net.preferIPv4Stack", "true");
            System.setProperty("java.awt.headless", "true");
            System.out.println(java.awt.GraphicsEnvironment.isHeadless());
            System.setProperty("webdriver.chrome.driver", "/var/.chromedriver/chromedriver");
            DesiredCapabilities dc = new DesiredCapabilities();
            dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--test-type");
            chromeOptions.addArguments("--start-maximized");
            chromeOptions.addArguments("--ignore-certificate-errors");
            chromeOptions.addArguments("--enable-popup-blocking");
            chromeOptions.addArguments("--disable-infobars");
            chromeOptions.addArguments("--disable-default-apps");
            chromeOptions.addArguments("--disable-extensions");
            chromeOptions.addArguments("--javaEnabled");
            chromeOptions.addArguments("--applicationCacheEnabled");
            chromeOptions.addArguments("--lang=pt-BR");
            chromeOptions.addArguments("--incognito");
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("enable-automation");
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--window-size=1366,768");

            /*TESTES MOBILE RESPONSIVO*/
            /*Map<String, Object> deviceMetrics = new HashMap<>();
            deviceMetrics.put("width", 360);
            deviceMetrics.put("height", 640);
            deviceMetrics.put("pixelRatio", 3.0);
            Map<String, Object> mobileEmulation = new HashMap<>();
            mobileEmulation.put("deviceMetrics", deviceMetrics);
            mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");
            chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);*/

            driver = new ChromeDriver(chromeOptions);
            driver.manage().deleteAllCookies();
        }

    }

    public static void startFirefoxDriver() {
        FirefoxOptions opts = new FirefoxOptions();
        opts.addArguments("-private");
        opts.setHeadless(true);
        opts.merge(DC);
        driver = new FirefoxDriver(opts);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    public static void startDriver() {
        String driverRequisitado = System.getProperty("browserdriver");
        switch (driverRequisitado) {
            case "Firefox":
                startFirefoxDriver();
                break;
            case "Chrome":
            default:
                startChromeDriver();
                break;
        }
        driver.manage().timeouts().pageLoadTimeout(TIME_OUT, TimeUnit.MINUTES);
    }

    public static WebDriver getCurrentRunningDriver() {
        return driver;
    }

    public static void closeCurrentRunningDriver() {
        driver.close();
    }

    public static void killCurentRunningDriver() {
        driver.quit();
    }
}
