package br.ce.jcsilva.core;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {


	/********* TextField e TextArea ************/

	public void escrever(By by, String texto){
		DriverFactory.getDriver().findElement(by).clear();
		DriverFactory.getDriver().findElement(by).sendKeys(texto);
	}

	public void escrever(String id_campo, String texto){
		escrever(By.id(id_campo), texto);
	}

	public String obterValorCampo(String id_campo) {
		return DriverFactory.getDriver().findElement(By.id(id_campo)).getAttribute("value");
	}

	/********* Radio e Check ************/

	public void clicarRadio(By by) {
		DriverFactory.getDriver().findElement(by).click();
	}

	public void clicarRadio(String id) {
		DriverFactory.getDriver().findElement(By.id(id)).click();
	}

	public boolean isRadioMarcado(String id){
		return DriverFactory.getDriver().findElement(By.id(id)).isSelected();
	}

	public void clicarCheck(String id) {
		DriverFactory.getDriver().findElement(By.id(id)).click();
	}

	public boolean isCheckMarcado(String id){
		return DriverFactory.getDriver().findElement(By.id(id)).isSelected();
	}

	public boolean areMultipleChecksMarcados(String id1, String id2) {
		 WebElement checkbox1 = DriverFactory.getDriver().findElement(By.id(id1));
		 WebElement checkbox2 = DriverFactory.getDriver().findElement(By.id(id2));

		 return checkbox1.isSelected() && checkbox2.isSelected();
		}

	/********* Combo ************/

	public void clicarOpcoesCombo(By by) {
		DriverFactory.getDriver().findElement(by).click();
	}

	public void selecionarCombo(String id, String valor) {
		WebElement element = DriverFactory.getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		combo.selectByVisibleText(valor);
	}

	public void deselecionarCombo(String id, String valor) {
		WebElement element = DriverFactory.getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		combo.deselectByVisibleText(valor);
	}

	public String obterValorCombo(String id) {
		WebElement element = DriverFactory.getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}

	public List<String> obterValoresCombo(String id) {
		WebElement element = DriverFactory.getDriver().findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		List<String> valores = new ArrayList<>();
		for(WebElement opcao: allSelectedOptions) {
			valores.add(opcao.getText());
		}
		return valores;
	}

	public int obterQuantidadeOpcoesCombo(String id){
		WebElement element = DriverFactory.getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		return options.size();
	}

	public boolean verificarOpcaoCombo(String id, String opcao){
		WebElement element = DriverFactory.getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		for(WebElement option: options) {
			if(option.getText().equals(opcao)){
				return true;
			}
		}
		return false;
	}

	public void selecionarComboPrime(String radical, String valor) {
		clicarOpcoesCombo(By.xpath("//*[@id='"+radical+"_input']/../..//span"));
		clicarOpcoesCombo(By.xpath("//*[@id='"+radical+"_items']//li[.='"+valor+"']"));
	}

	/********* Botao ************/

	public void clicarBotao(String id) {
		DriverFactory.getDriver().findElement(By.id(id)).click();
	}
	
	
	public void clicarBotao(By id) {
		DriverFactory.getDriver().findElement(id).click();
	}

	public String obterValueElemento(String id) {
		return DriverFactory.getDriver().findElement(By.id(id)).getAttribute("value");
	}

	/********* Link ************/

	public void clicarLink(String link) {
		DriverFactory.getDriver().findElement(By.linkText(link)).click();
	}

	/********* Textos ************/

	public String obterTexto(By by) {
		return DriverFactory.getDriver().findElement(by).getText();
	}

	public String obterTexto(String id) {
		return obterTexto(By.id(id));
	}

	/********* Alerts ************/

	public String alertaObterTexto(){
		Alert alert = DriverFactory.getDriver().switchTo().alert();
		return alert.getText();
	}

	public String alertaObterTextoEAceita(){
		Alert alert = DriverFactory.getDriver().switchTo().alert();
		String valor = alert.getText();
		alert.accept();
		return valor;

	}

	public String alertaObterTextoENega(){
		Alert alert = DriverFactory.getDriver().switchTo().alert();
		String valor = alert.getText();
		alert.dismiss();
		return valor;

	}

	public void alertaEscrever(String valor) {
		Alert alert = DriverFactory.getDriver().switchTo().alert();
		alert.sendKeys(valor);
		alert.accept();
	}

	/********* Frames e Janelas ************/

	public void entrarFrame(String id) {
		DriverFactory.getDriver().switchTo().frame(id);
	}

	public void sairFrame(){
		DriverFactory.getDriver().switchTo().defaultContent();
	}

	public void trocarJanela(String id) {
		DriverFactory.getDriver().switchTo().window(id);
	}
	
	public void scrollDown() {
	JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
	js.executeScript("window.scrollBy(0,150)", "");
	}
	

	/************** JS *********************/

	public Object executarJS(String cmd, Object... param) {
		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
		return js.executeScript(cmd, param);
	}

	/************** Tabela *********************/

	public void clicarBotaoTabela (String colunaBusca, String valor, String colunaBotao, String idTabela) {
		WebElement tabela = DriverFactory.getDriver().findElement(By.xpath("//*[@id='elementosForm:tableUsuarios']"));
		int idColuna = obterIndiceColuna(colunaBusca, tabela);

		int idLinhas = obterIndiceLinha(valor, tabela, idColuna);

		int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);

		WebElement celula = tabela.findElement(By.xpath(".//tr["+idLinhas+"]/td["+idColunaBotao+"]"));
		celula.findElement(By.xpath(".//input")).click();
}

	protected int obterIndiceLinha(String valor, WebElement tabela, int idColuna) {
		List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td["+idColuna+"]"));
		int idLinhas = -1;
		for (int i = 0; i < linhas.size(); i++) {
			if(linhas.get(i).getText().equals(valor)) {
			idLinhas = i+1;
			break;
			}
		}
		return idLinhas;
	}

	protected int obterIndiceColuna(String coluna, WebElement tabela) {
		List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
		int idColuna = -1;
		for (int i = 0; i < colunas.size(); i++) {
			if(colunas.get(i).getText().equals(coluna)) {
			idColuna = i+1;
			break;
			}
		}
		return idColuna;
	}

}