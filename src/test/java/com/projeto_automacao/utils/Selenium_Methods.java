package com.projeto_automacao.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.projeto_automacao.Selenium_Setup;

import java.awt.Robot;
import java.awt.event.InputEvent;


public class Selenium_Methods extends Selenium_Setup {

    public void waitForElementPresent(By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // Método personalizado para clicar em um elemento
    protected void clickWithHighlight(WebElement element) {
        highlightElement(element);
        element.click();
        wait(1);
    }

    // Método personalizado para enviar texto para um elemento
    protected void sendKeysWithHighlight(WebElement element, String text) {
        highlightElement(element);
        element.sendKeys(text);
        wait(1);
    }

    // Método personalizado para limpar o conteúdo de um elemento
    protected void clearWithHighlight(WebElement element) {
        highlightElement(element);
        element.clear();
        wait(1);
    }

    // Função para destacar um elemento com uma linha vermelha
    private void highlightElement(WebElement element) {
        // Executa um script JavaScript para adicionar um estilo CSS temporário ao elemento
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='3px solid red';", element);

        // Aguarda um pequeno intervalo para que o destaque seja visível
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Remove o estilo CSS para retornar ao estado original
        js.executeScript("arguments[0].style.border='';", element);
    }

    // Clicar em Coordenadas
    protected void clickCoordinates (int x, int y) throws Exception {
        if (x < 0 || y < 0) {
            throw new Exception("As coordenadas não podem ser negativas");
        } else if (x > 1920 || y > 1080) {
            throw new Exception("As coordenadas não podem ser maiores que 1920x1080");	
        } else {
            Robot robot = new Robot();
            robot.mouseMove(x, y); // Define as coordenadas do mouse
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // Pressiona o botão esquerdo do mouse
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // Libera o botão esquerdo do mouse
        }
    }

    // Esperar
    public void wait(int timeoutSeconds) {
        try {
            Thread.sleep(timeoutSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
