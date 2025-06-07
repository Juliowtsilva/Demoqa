package br.ce.jcsilva.test;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
public class TesteCadastro extends BaseTest {

	private CampoTreinamentoPage page;
	private static final Logger LOGGER = Logger.getLogger(TesteLogin.class);	


	@Before
	public void test1_inicializa() throws InterruptedException{
		
		ReportManager.createTest("Teste Cadastro");
		
		DriverFactory.getDriver().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		ReportManager.logInfo("Iniciando teste de cadastro de usuário");
		
		DriverFactory.getDriver().get("https://demoqa.com/register");
		page = new CampoTreinamentoPage();
		ReportManager.logPass("Acessou a tela de cadastro");

	}
	
	
//	@Test
//	public void test2_DeveRedirecionarParaCadastro() throws InterruptedException{
//		page.redirecionarLogin();
//		DriverFactory.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//		WebElement frame = DriverFactory.getDriver().findElement(By.id("recaptcha-anchor"));
//		dsl.executarJS("window.scrollBy(0, arguments[0])", frame.getLocation().y);
//		page.redirecionarCadastro();
//	}

	@Test
	public void test2_DeveRealizarCadastroComSucesso() throws InterruptedException{
		page.reportarCampos();
		page.scrollDown("register");
		page.setNome("Júlio");
		page.setSobrenome("César");
		page.setUserName("JcSilva " + Integer.toString(((new Random().nextInt(10))+1)));
		page.setPassword("Senha@123");
		page.reportarCamposSucesso();
		LOGGER.info("Cada campo foi devidamente preenchido");
		Thread.sleep(60000);
		
		try {
		    page.registrar();
		    ReportManager.logPass("Usuário cadastrado com sucesso");
		    LOGGER.info("Usuário cadastrado com sucesso");
		} catch (Exception e) {
		    ReportManager.logFail("Erro ao cadastrar o usuário");
		    LOGGER.error("Erro ao cadastrar usuário", e);
		    
		}
	}
		
		/*try {
			WebElement frame = DriverFactory.getDriver().findElement(By.id("recaptcha-anchor"));
			dsl.executarJS("window.scrollBy(0, arguments[0])", frame.getLocation().y);
			dsl.entrarFrame("recaptcha-anchor");
	        page.verificacaoRobo();

	        dsl.sairFrame();

	        Thread.sleep(2000);
	    } catch (Exception e) {
	        System.out.println("Please solve CAPTCHA manually within 30 seconds");
	        try {
	            Thread.sleep(30000);
	            } catch (InterruptedException ie) {
	            Thread.currentThread().interrupt();
	        }
	    }*/


		
		

	}
