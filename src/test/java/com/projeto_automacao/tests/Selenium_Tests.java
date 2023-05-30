package com.projeto_automacao.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.projeto_automacao.utils.Selenium_Methods;


public class Selenium_Tests extends Selenium_Methods{

    @Test
    public void Pesquisa_Google() throws Exception {
        WebElement campoPesquisa = driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/textarea"));
        waitForElementPresent(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/textarea"), 5);
        sendKeysWithHighlight(campoPesquisa, "Teste de Software");
        campoPesquisa.sendKeys(Keys.ENTER);
    }

    @Test
    public void Pesquisa_Google2() throws Exception {
        WebElement campoPesquisa = driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/textarea"));
        waitForElementPresent(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/textarea"), 5);
        sendKeysWithHighlight(campoPesquisa, "Teste de Software");
        campoPesquisa.sendKeys(Keys.ENTER);
    }

    @Test
    public void Pesquisa_Google3() throws Exception {
        WebElement campoPesquisa = driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/textarea"));
        waitForElementPresent(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/textarea"), 5);
        sendKeysWithHighlight(campoPesquisa, "Teste de Software");
        campoPesquisa.sendKeys(Keys.ENTER);
    }

    @Test
    public void Pesquisa_Google4() throws Exception {
        WebElement campoPesquisa = driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/textarea"));
        waitForElementPresent(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/textarea"), 5);
        sendKeysWithHighlight(campoPesquisa, "Teste de Software");
        campoPesquisa.sendKeys(Keys.ENTER);
    }

    @Test
    public void Pesquisa_Google5() throws Exception {
        WebElement campoPesquisa = driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/textarea"));
        waitForElementPresent(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/textarea"), 5);
        sendKeysWithHighlight(campoPesquisa, "Teste de Software");
        campoPesquisa.sendKeys(Keys.ENTER);
    }
}

