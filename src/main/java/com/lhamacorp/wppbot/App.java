package com.lhamacorp.wppbot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Date;
import java.util.Scanner;

public class App {

    private static final String MENSAGEM = "bom almoço amigo osni";
    private static final String CONTATO = "Osni Pow";

    private static void LOGGER(Object obj) {
        System.out.println(new Date() + ": " + obj);
    }

    private WebDriver browser = new ChromeDriver();

    App() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        browser.get("https://web.whatsapp.com");
    }

    private void openWhatsapp() {
        int count = 0;
        Scanner sc = new Scanner(System.in);
        String command = sc.next();
        if (!command.equalsIgnoreCase("start")) {
            browser.quit();
            System.exit(1);
        }
        sc.close();

        while (true) {
            try {
                String xpath = "//span[contains(@title,'" + CONTATO + "')]";

                WebElement chatPow = browser.findElement(By.xpath(xpath));
                reply(chatPow);

                Thread.sleep(1);

                LOGGER(count++);

            } catch (InterruptedException e) {
                e.printStackTrace();
                browser.quit();
            }
        }

    }

    private void reply(WebElement element) {
        element.click();

        browser.findElement(By.xpath("//div[@class=\"_3F6QL _2WovP focused\"]"))
                .sendKeys(MENSAGEM);

        browser.findElement(By.xpath("//div[@class=\"weEq5\"]//button[@class=\"_35EW6\"]"))
                .click();
    }

    public static void main(String[] args) {
        App web = new App();
        web.openWhatsapp();
    }

}
