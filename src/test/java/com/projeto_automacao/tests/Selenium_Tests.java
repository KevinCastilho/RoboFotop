package com.projeto_automacao.tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.projeto_automacao.config.ConfigReader;

public class Selenium_Tests {

protected WebDriver driver;
protected WebDriverWait wait;

    public Selenium_Tests(String chromeDriverPath) {
        ConfigReader.loadConfig();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifications");
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 30);
    }

    public void buscaEvento() {
        try {
            automacaoBuscaEvento();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Variáveis de controle - Configuradas em config.properties
    String Email = ConfigReader.getProperty("Email");
    String Senha = ConfigReader.getProperty("Senha");
    String NomeEvento = ConfigReader.getProperty("NomeEvento");
    String qtdeIntervalo = ConfigReader.getProperty("Intervalo");
    int intervalo = Integer.parseInt(qtdeIntervalo);
    String qtdeHorasBusca = ConfigReader.getProperty("HorasBusca");
    int horasEspera = Integer.parseInt(qtdeHorasBusca);

    @Test
    public void automacaoBuscaEvento() throws Exception {
        abrirFotop();
        pesquisarEvento();
        escolherEvento();
        confirmarEvento();
    }

    public void abrirFotop() {
        driver.get(ConfigReader.getProperty("URL"));
        // Mapping Elements
        WebElement txtEmail = driver.findElement(By.id("email"));
        WebElement txtSenha = driver.findElement(By.id("senha"));
        WebElement btnLogin = driver.findElement(By.xpath("/html/body/div/section/div/div/div/form/div[4]/button"));
 
         // Início do código
        waitForElementAndSendKeys(txtEmail,ConfigReader.getProperty("Email"),8);
        waitForElementAndSendKeys(txtSenha,ConfigReader.getProperty("Senha"),8);
        waitForElementAndClick(btnLogin,8);
        WebElement btnEntrar=driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div[1]/div/a"));
        waitForElementAndClick(btnEntrar, 9);
        WebElement btnVerTodosEventos=driver.findElement(By.xpath("/html/body/div[2]/div/div[4]/div[1]/div/a"));
        waitForElementAndClick(btnVerTodosEventos, 9);
    }

    public void pesquisarEvento() {
        WebElement inputEscolherEvento=driver.findElement(By.id("txtBuscaEventos"));
        waitForElementPresentAndClear(inputEscolherEvento, 9);
        waitForElementAndSendKeys(inputEscolherEvento,NomeEvento,9);
        WebElement btnBuscar=driver.findElement(By.id("btnBuscar"));
        waitForElementAndClick(btnBuscar,9);  
    }

    public void escolherEvento() throws AWTException {
        long tempoInicial = System.currentTimeMillis();
        long tempoEsperaEmMilissegundos = horasEspera * 60 * 60 * 1000; // Converter horas em milissegundos - 1 Hora = 3.6M milissegundos
        do {
            long tempoAtual = System.currentTimeMillis();
            long tempoDecorrido = tempoAtual - tempoInicial;
            WebElement btnDisponibilidade = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[1]/div/div[2]/div/div[4]/button"));
            waitForSeconds(intervalo);
            if (tempoDecorrido <= tempoEsperaEmMilissegundos) {
                if(btnDisponibilidade.isEnabled()){
                    waitForElementAndClick(btnDisponibilidade,9);
                    try {
                        Alert alert = driver.switchTo().alert();
                        alert.accept();
                    } catch (Exception e) {
                        System.out.println("Não há alerta para aceitar");
                    }
                    driver.switchTo().defaultContent();
                    break;
                } else{
                    highlightElement(btnDisponibilidade);
                    Robot robot = new Robot();
                    robot.keyPress(KeyEvent.VK_F5);
                    robot.keyRelease(KeyEvent.VK_F5);
                }   
            }
        } while (true);
    }

    public void confirmarEvento() {
        WebElement chkTermos = driver.findElement(By.xpath("/html/body/div[4]/div/div/div[2]/div[1]/div[5]/p[3]/label")); 
        waitForElementAndClick(chkTermos,9);
        WebElement btnConfirmar = driver.findElement(By.xpath("/html/body/div[4]/div/div/div[2]/div[2]/button"));
        waitForElementAndClick(btnConfirmar,9);
        Alert alert = driver.switchTo().alert();
        alert.accept();
        System.out.println("Evento confirmado com sucesso!");
    }

    //_______________________ MÉTODOS___________________________
        // Esperar e Clicar
        public void waitForElementAndClick(WebElement element, int timeoutSeconds) {
            wait.until(ExpectedConditions.visibilityOf(element));
            clickWithHighlight(element);
        }
        
        // Esperar e Digitar
        public void waitForElementAndSendKeys(WebElement element, String text, int timeoutSeconds) {
            wait.until(ExpectedConditions.visibilityOf(element));
            sendKeysWithHighlight(element, text);
        }

        // Esperar Presença do Elemento e Clicar Nele
        protected void waitForElementPresentAndClick(WebElement element, int timeoutSeconds) {
            WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
            wait.until(ExpectedConditions.visibilityOf(element));
            clickWithHighlight(element);
        }

        // Esperar Presença do Elemento e Digitar Nele
        protected void waitForElementPresentAndSendKeys(WebElement element, String text, int timeoutSeconds) {
            WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
            wait.until(ExpectedConditions.visibilityOf(element));
            sendKeysWithHighlight(element, text);
        }

        // Esperar Presença do Elemento e Limpar o Conteúdo Dele
        protected void waitForElementPresentAndClear(WebElement element, int timeoutSeconds) {
            WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
            wait.until(ExpectedConditions.visibilityOf(element));
            clearWithHighlight(element);
        }

        // Método personalizado para aguardar a visibilidade de um elemento
        protected void waitForElementPresent(WebElement element, int timeoutSeconds) {
            WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
            wait.until(ExpectedConditions.visibilityOf(element));
        }

        // Método personalizado para clicar em um elemento
        protected void clickWithHighlight(WebElement element) {
            highlightElement(element);
            element.click();
            waitForSeconds(1);
        }

        // Método personalizado para enviar texto para um elemento
        protected void sendKeysWithHighlight(WebElement element, String text) {
            highlightElement(element);
            element.sendKeys(text);
            waitForSeconds(1);
        }

        // Método personalizado para limpar o conteúdo de um elemento
        protected void clearWithHighlight(WebElement element) {
            highlightElement(element);
            element.clear();
            waitForSeconds(1);
        }

        // Função para destacar um elemento com uma linha vermelha
        protected void highlightElement(WebElement element) {
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
        public void waitForSeconds (long timeoutSeconds) {
            try {
                Thread.sleep(timeoutSeconds * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

}