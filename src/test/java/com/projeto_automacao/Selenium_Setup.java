package com.projeto_automacao;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.projeto_automacao.config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Selenium_Setup {

    protected WebDriver driver = null;

    // Esperar
    public void wait(int timeoutSeconds) {
        try {
            Thread.sleep(timeoutSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @BeforeClass
    public static void prepareWebDriver() {
        WebDriverManager
        .chromedriver()
        .avoidResolutionCache()
        .setup();
    }

    @Before
    public void setUp() {
        ConfigReader.loadConfig();
        createWebDriver();
    }

    private void createWebDriver() {
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--start-maximized");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-infobars");
        driver = new ChromeDriver(options);
        driver.get(ConfigReader.getProperty("site.url"));
    }

    @After
    public void afterEachBaseSeleniumTest() {
        wait(5);
        if (driver != null && driver instanceof WebDriver) {
            driver.quit();
        }
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
            } else if (os.contains("mac")) {
                Runtime.getRuntime().exec("pkill chromedriver");
            } else if (os.contains("nix") || os.contains("nux") || os.contains("bsd")) {
                Runtime.getRuntime().exec("killall chromedriver");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
