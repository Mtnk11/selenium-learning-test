package tests;

import com.saucelabs.saucerest.SauceREST;
import org.junit.Rule;
import org.junit.rules.ExternalResource;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

import static config.Config.*;

public class BaseTest {
    protected WebDriver driver;
    private String testName;
    private SauceREST sauceClient;
    private String sessionId;
    @Rule
    public ExternalResource externalResource = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            if (host.equals("saucelabs")) {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("browserName", browserName);
                capabilities.setCapability("version", browserVersion);
                capabilities.setCapability("platform", platformName);
                capabilities.setCapability("name", testName);
                capabilities.setCapability("username", saucelabsUser);
                capabilities.setCapability("accessKey", saucelabsPassword);
                URL url = new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub");

                sessionId = ((RemoteWebDriver) driver).getSessionId().toString();
                driver = new RemoteWebDriver(url, capabilities);
//                sauceClient = new SauceREST(saucelabsUser, saucelabsPassword);
            } else if (host.equals("localhost")) {
                if (browserName.equals("firefox")) {
                    System.setProperty("webdriver.gecko.driver",
                            "libs/geckodriver");
                    driver = new FirefoxDriver();
                } else if (browserName.equals("chrome")) {
                    System.setProperty("webdriver.chrome.driver",
                            "libs/chromedriver");
                    ChromeOptions options = new ChromeOptions();
//                    options.addArguments("--headless");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");

                    driver = new ChromeDriver(options);
                }
            }
        }

        @Override
        protected void after() {
            driver.quit();
        }
    };

    @Rule
    public TestRule watcher = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            testName = description.getDisplayName();
        }

        @Override
        protected void failed(Throwable e, Description description) {
            if (host.equals("saucelabs")) {
//                sauceClient.jobFailed(sessionId);
                System.out.println(String.format("https://saucelabs.com/tests/%s", sessionId));
            }
        }

        @Override
        protected void succeeded(Description description) {
//            sauceClient.jobPassed(sessionId);
        }
    };
}
