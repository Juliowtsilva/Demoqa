package br.ce.jcsilva.page;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import br.ce.jcsilva.core.BasePage;
import br.ce.jcsilva.core.DriverFactory;
import br.ce.jcsilva.core.ReportManager;

public class DemoqaPage extends BasePage {


	public void redirecionarLogin(){
		dsl.clicarBotao(By.xpath("//*[@id=\"login\"]"));
	}
	
	public void redirecionarCadastro(){
		dsl.clicarBotao(By.xpath("//*[@id=\"newUser\"]"));
	}
	
	public void setNome(String nome) {
		dsl.escrever("firstname", nome);
	}

	public void setSobrenome(String sobrenome) {
		dsl.escrever("lastname", sobrenome);
	}

	public void setUserName(String username) {
		dsl.escrever("userName", username);
	}

	public void setPassword(String password) {
		dsl.escrever("password", password);
	}

	public void verificacaoRobo(){
		dsl.clicarBotao(By.xpath("//div[@class='recaptcha-checkbox-checkmark']"));
	}

	public void registrar(){
		dsl.clicarBotao(By.xpath("//*[@id='register']"));
	}

	public void logar() {
		dsl.clicarBotao(By.xpath("//*[@id=\"login\"]"));
		//dsl.clicarBotao("login");
	}
	
	public void setPesquisa(String pesquisa) {
		dsl.escrever("searchBox", pesquisa);
	}
	
	public void pesquisar() {
		dsl.clicarBotao(By.xpath("//*[@id=\"basic-addon2\"]"));
	}

	public void scrollDown(String id) {
		WebElement scroll = DriverFactory.getDriver().findElement(By.id(id));
		dsl.executarJS("window.scrollBy(0, arguments[0])", scroll.getLocation().y);
	}
	
	public void scrollManual(String horizontal, String vertical) {
	JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
	js.executeScript("window.scrollBy("+horizontal+","+vertical+")");
	}
	
	public void reportarCampos() {
		ReportManager.logInfo("Preenchimento dos campos");	
	}
	
	public void reportarCamposSucesso() {
		ReportManager.logPass("Preencheu os campos com sucesso");
	}

}
