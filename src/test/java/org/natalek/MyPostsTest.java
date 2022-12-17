package org.natalek;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyPostsTest extends BaseTest{

    @BeforeEach
    void openBrowser(){
        driver.get("https://test-stand.gb.ru/login");
    }

    @Test
    @DisplayName("Переходы на Previous Page и Next Page (свои посты есть более 1 стр.).")
    void previousNextPageExistPostsTest () {
        driver.findElement(By.xpath("//span[.='Username']/following::input")).click();
        driver.findElement(By.xpath("//span[.='Username']/following::input")).sendKeys("IvanIvanov-82");
        driver.findElement(By.xpath("//span[.='Password']/following::input")).click();
        driver.findElement(By.xpath("//span[.='Password']/following::input")).sendKeys("87826428f6");
        driver.findElement(By.xpath("//button//span[.='Login']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li//a[contains(.,'IvanIvanov-82')]")));
        driver.get("https://test-stand.gb.ru/?sort=createdAt&order=ASC&page=1");
        Assertions.assertTrue(driver.findElement(By.xpath("//div//a[.='Previous Page' and contains(@class,'disabled')]")).isDisplayed());
        Assertions.assertTrue(driver.findElement(By.xpath("//div//a[.='Next Page' and @class='svelte-d01pfs']")).isDisplayed());
        driver.findElement(By.xpath("//div//a[.='Next Page']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//a[.='Previous Page' and @class='svelte-d01pfs']")));
        Assertions.assertTrue(driver.findElement(By.xpath("//div//a[.='Previous Page' and @class='svelte-d01pfs']")).isDisplayed());
        Assertions.assertTrue(driver.findElement(By.xpath("//div//a[.='Next Page' and contains(@class,'disabled')]")).isDisplayed());
    }

    @Test
    @DisplayName("Пост имеет изображение, название и описание.")
    void correctPostContentTest () {
        driver.findElement(By.xpath("//span[.='Username']/following::input")).click();
        driver.findElement(By.xpath("//span[.='Username']/following::input")).sendKeys("IvanIvanov-82");
        driver.findElement(By.xpath("//span[.='Password']/following::input")).click();
        driver.findElement(By.xpath("//span[.='Password']/following::input")).sendKeys("87826428f6");
        driver.findElement(By.xpath("//button//span[.='Login']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li//a[contains(.,'IvanIvanov-82')]")));
        driver.get("https://test-stand.gb.ru/?sort=createdAt&order=ASC&page=1");
        Assertions.assertTrue(driver.findElement(By.xpath("//div//a[1]//img[contains(@src,'.jpg')]")).isDisplayed());
        Assertions.assertTrue(driver.findElement(By.xpath("//div//a[1]//h2[contains(@class,'svelte-127jg4t')]")).isDisplayed());
        Assertions.assertTrue(driver.findElement(By.xpath("//div//div[1][contains(@class,'description')]")).isDisplayed());
    }

    @Test
    @DisplayName("Нет перехода на Previous Page и Next Page (своих постов нет).")
    void previousNextPageNoPostsTest () {
        driver.findElement(By.xpath("//span[.='Username']/following::input")).click();
        driver.findElement(By.xpath("//span[.='Username']/following::input")).sendKeys("Gazyusya");
        driver.findElement(By.xpath("//span[.='Password']/following::input")).click();
        driver.findElement(By.xpath("//span[.='Password']/following::input")).sendKeys("87b3ad0cfd");
        driver.findElement(By.xpath("//button//span[.='Login']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li//a[contains(.,'Gazyusya')]")));
        driver.get("https://test-stand.gb.ru/?sort=createdAt&order=ASC&page=1");
        Assertions.assertTrue(driver.findElement(By.xpath("//div[.='No items for your filter']")).isDisplayed());
        Assertions.assertTrue(!"Previous Page".contains(driver.getPageSource()));
        Assertions.assertTrue(!"Next Page".contains(driver.getPageSource()));
    }
}
