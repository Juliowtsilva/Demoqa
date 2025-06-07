package br.ce.jcsilva.test;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.ce.jcsilva.core.BaseTest;
import br.ce.jcsilva.core.DriverFactory;
import br.ce.jcsilva.core.ReportManager;
import br.ce.jcsilva.page.DemoqaPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteLogin extends BaseTest {

	private DemoqaPage page;
	private static final Logger LOGGER = Logger.getLogger(TesteLogin.class);	

	
	@Before
	public void test1_inicializa(){
		ReportManager.createTest("Teste Login");
		ReportManager.logInfo("Iniciando teste de login de usuário");
		DriverFactory.getDriver().get("https://demoqa.com/login");
		page = new DemoqaPage();
		ReportManager.logPass("Acessou a tela de login");
		

	}
	
	@Test
	public void test2_DeveRealizarLoginComSucesso() throws InterruptedException, IOException{
		page.reportarCampos();
		page.scrollDown("login");
		page.setUserName("Usuário Teste");
		page.setPassword("Senha@123");
		page.reportarCamposSucesso();
		LOGGER.info("Cada campo foi devidamente preenchido");
		Thread.sleep(5000);
		try {
			page.logar();
			Thread.sleep(5000);
			ReportManager.logPass("Usuário logado com sucesso");
			LOGGER.info("Usuário logado com sucesso");
			tiraPrint();
		} catch (Exception e){
			ReportManager.logFail("Erro ao logar usuário");
			LOGGER.error("Erro ao logar usuário", e);
			tiraPrint();
			
		}		
		
	}

		
		

	}
