package com.projeto_automacao.tests;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class TestRunner {

    public static void main(String[] args) {
        Properties properties = new Properties();
        FileInputStream fis = null;
        Scanner scanner = new Scanner(System.in);
        try {
            fis = new FileInputStream("config.properties");
            properties.load(fis);
            // Verifique se o arquivo config.properties já existe
            System.out.print("O arquivo 'config.properties' já existe. Deseja usar as configurações existentes? (1 para Sim, 2 para Não): ");
            int escolha = scanner.nextInt();
            scanner.nextLine();
            if (escolha == 1) {
                // Usar as configurações existentes
                System.out.println("Usando as configurações existentes...");
            } else if (escolha == 2) {
                // Criar novas configurações
                createNewConfig(properties, scanner);
            } else {
                System.out.println("Opção inválida. Criando novas configurações...");
                createNewConfig(properties, scanner);
            }
        } catch (IOException e) {
            // Se o arquivo config.properties não existir, será criado um novo.
            createNewConfig(properties, scanner);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            scanner.close();
        }  

        if (properties.getProperty("webdriver.chrome.driver").isEmpty()) {
            System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
        } else {
            Selenium_Tests seleniumTests = new Selenium_Tests(properties.getProperty("webdriver.chrome.driver"));
            seleniumTests.buscaEvento();
        }

    }

    private static void createNewConfig (Properties properties, Scanner scanner) {
        // Configurar propriedades Iniciais
        properties.setProperty("webdriver.chrome.driver", "src/main/java/resources/chromedriver.exe");
        properties.setProperty("URL", "https://painel.fotop.io/");

        // Coletar informações adicionais do usuário
        System.out.println("Bem-vindo à configuração do bot de eventos da Fotop - Por Favor responda algumas perguntas, leia com calma (leva menos de 2 minutos):");

        // Configuração de Login Automático
        System.out.print("Deseja ativar o Login Automático na Fotop? (1 para Sim, 2 para Não): ");
        int escolhaLogin = scanner.nextInt();
        scanner.nextLine(); // Consuma a nova linha após a leitura do número

        if (escolhaLogin == 1) {
            System.out.print("Digite seu email: ");
            String email = scanner.nextLine();
            properties.setProperty("Email", email);
            System.out.print("Digite sua senha: ");
            String senha = scanner.nextLine();
            properties.setProperty("Senha", senha);
            properties.setProperty("LoginAutomatico", "true");
        } else if (escolhaLogin == 2) {
            properties.setProperty("LoginAutomatico", "false");
            properties.setProperty("Email", "");
            properties.setProperty("Senha", "");
        } else {
            System.out.println("Opção inválida. Login automático será desabilitado.");
            properties.setProperty("LoginAutomatico", "false");
            properties.setProperty("Email", "");
            properties.setProperty("Senha", "");
        }

        System.out.println("Qual evento deseja participar?");
        String NomeEvento = scanner.nextLine();
        properties.setProperty("NomeEvento",NomeEvento);
        
        System.out.println("Qual o intervalo de tempo (em segundos) entre as tentativas? (Digite 0 para usar o valor padrão de 0.5 segundo)");
        if (scanner.hasNextInt()) {
            properties.setProperty("Intervalo", String.valueOf(scanner.nextInt()));
        } else {
            properties.setProperty("Intervalo", "0.7");
        }

        System.out.println("Por quantas horas o bot ficará tentando?");
        if (scanner.hasNextInt()) {
            properties.setProperty("HorasBusca", String.valueOf(scanner.nextInt()));
        } else {
            properties.setProperty("HorasBusca", "4");
        }

        try {
            FileOutputStream fos = new FileOutputStream("config.properties");
            properties.store(fos, "Configurações do projeto de automação");
            fos.close();
            System.out.println("Configurações salvas com sucesso!");
            System.out.println("Busca de eventos");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
