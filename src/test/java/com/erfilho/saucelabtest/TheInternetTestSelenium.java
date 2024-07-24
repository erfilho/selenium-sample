package com.erfilho.saucelabtest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TheInternetTestSelenium {

    private WebDriver webDriver;

    @BeforeEach
    public void setup(){
        System.out.println("*** setup ***");
        System.setProperty("webdriver.chrome.driver", "E://dev//chromedriver-win64//chromedriver.exe");

        ChromeDriverService service = new ChromeDriverService.Builder().withLogOutput(System.out).build();

        webDriver = new ChromeDriver(service);
    }

    @Test
    public void testDynamicControl(){
        System.out.println("*** testing ***");
        webDriver.get("https://the-internet.herokuapp.com/dynamic_controls");

        boolean expectedFieldExist;

        try{
            Thread.sleep(5000);

            WebElement btn1 = webDriver.findElement(By.cssSelector("button[onClick='swapInput()']"));
            btn1.click();

            Thread.sleep(9000);

            WebElement inpt1 = webDriver.findElement(By.cssSelector("input[type='text']"));
            inpt1.sendKeys("Testando Escrita");

            btn1.click();

            Thread.sleep(15000);

            WebElement finalMessage = webDriver.findElement(By.cssSelector("p[id='message']"));

            if(finalMessage.getText().equals("It's disabled!")){
                expectedFieldExist = true;
            } else {
                expectedFieldExist = false;
            }


        } catch (NoSuchElementException e){
            System.out.println(e.getMessage());
            expectedFieldExist = false;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        assertTrue(expectedFieldExist);
    }

    @AfterEach
    public void tearDown(){
        System.out.println("*** turningoff ***");
        webDriver.quit();
    }

}