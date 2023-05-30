package com.projeto_automacao.utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.projeto_automacao.Selenium_Setup;

public class Elements extends Selenium_Setup {
    
//#region Google

@FindBy(xpath = "/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/textarea")
private WebElement campoPesquisa;

@FindBy(xpath = "/html/body/div[1]/div[3]/form/div[1]/div[1]/div[4]/center/input[1]")
private WebElement botaoPesquisar;


//#endregion Google

}
