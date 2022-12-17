package org.natalek;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;

    @BeforeAll
    static void registerDriver (){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupBrowser(){
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        actions = new Actions(driver);
    }

    @AfterEach
    void tearDown () {
        driver.quit();
    }
}
