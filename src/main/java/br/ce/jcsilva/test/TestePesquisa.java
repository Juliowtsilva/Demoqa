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
import br.ce.jcsilva.page.CampoTreinamentoPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestePesquisa extends BaseTest {

	private CampoTreinamentoPage page;
	private static final Logger LOGGER = Logger.getLogger(TestePesquisa.class);
	

	@Before
	public void test1_inicializa(){
		ReportManager.createTest("Teste Pesquisa");
		ReportManager.logInfo("Iniciando teste de pesquisa de livros");
		DriverFactory.getDriver().get("https://demoqa.com/books");
		page = new CampoTreinamentoPage();
		ReportManager.logPass("Acessou a tela de pesquisa");

	}
	
	@Test
	public void test2_DeveRealizarBuscaComSucesso() throws InterruptedException, IOException{
		page.scrollManual("0", "250");
		page.reportarCampos();
		page.setPesquisa("Java");
		page.reportarCamposSucesso();
		LOGGER.info("O campo foi devidamente preenchido");
		
		try {
			page.pesquisar();
			ReportManager.logPass("Pesquisa realizada com sucesso");
			LOGGER.info("Pesquisa feita com sucesso");
			Thread.sleep(5000);
			tiraPrint();
		} catch (Exception e) {
			ReportManager.logFail("Falha ao pesquisar livros");
			LOGGER.error("Erro na pesquisa de livros", e);
			tiraPrint();
			
		}	
		
	}
		
		

	}
