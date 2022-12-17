package org.natalek;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AuthorizationTest extends BaseTest{
    @BeforeEach
    void openBrowser(){
        driver.get("https://test-stand.gb.ru/login");
    }

    @Test
    @DisplayName("Валидные значения логин/пароль - открыты мои посты.")
    void validLoginPasswordTest () {
        driver.findElement(By.xpath("//span[.='Username']/following::input")).click();
        driver.findElement(By.xpath("//span[.='Username']/following::input")).sendKeys("IvanIvanov-82");
        driver.findElement(By.xpath("//span[.='Password']/following::input")).click();
        driver.findElement(By.xpath("//span[.='Password']/following::input")).sendKeys("87826428f6");
        driver.findElement(By.xpath("//button//span[.='Login']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li//a[contains(., 'IvanIvanov-82')]")));
        Assertions.assertTrue(driver.findElement(By.xpath("//li//a[contains(., 'IvanIvanov-82')]")).isDisplayed());
        Assertions.assertTrue(driver.findElement(By.xpath("//div//a//h2[.='Оладьи на молоке']")).isDisplayed());
    }

    @Test
    @DisplayName("Невалидные значения логин/пароль = код 401.")
    void inValidLoginPasswordTest () {
        driver.findElement(By.xpath("//span[.='Username']/following::input")).click();
        driver.findElement(By.xpath("//span[.='Username']/following::input")).sendKeys("Ivan");
        driver.findElement(By.xpath("//span[.='Password']/following::input")).click();
        driver.findElement(By.xpath("//span[.='Password']/following::input")).sendKeys("8");
        driver.findElement(By.xpath("//button//span[.='Login']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//h2[.='401']")));
        Assertions.assertTrue(driver.findElement(By.xpath("//div//h2[.='401']")).isDisplayed());
    }

    @Test
    @DisplayName("Логин не может содержать более 20 символов")
    void loginLongerThan20charactersTest () throws InterruptedException {
        driver.findElement(By.xpath("//span[.='Username']/following::input")).click();
        driver.findElement(By.xpath("//span[.='Username']/following::input")).sendKeys("123456789123456789999");
        driver.findElement(By.xpath("//span[.='Password']/following::input")).click();
        driver.findElement(By.xpath("//span[.='Password']/following::input")).sendKeys("092b8e43ec");
        driver.findElement(By.xpath("//button//span[.='Login']")).click();
        Thread.sleep(1000);
        Assertions.assertTrue(driver.getCurrentUrl().contains("login"));
    }

    @Test
    @DisplayName("Логин не может содержать менее 3 символов")
    void loginLessThan3charactersTest () throws InterruptedException {
        driver.findElement(By.xpath("//span[.='Username']/following::input")).click();
        driver.findElement(By.xpath("//span[.='Username']/following::input")).sendKeys("++");
        driver.findElement(By.xpath("//span[.='Password']/following::input")).click();
        driver.findElement(By.xpath("//span[.='Password']/following::input")).sendKeys("25fb0beeab");
        driver.findElement(By.xpath("//button//span[.='Login']")).click();
        Thread.sleep(1000);
        Assertions.assertTrue(driver.getCurrentUrl().contains("login"));
    }
}
