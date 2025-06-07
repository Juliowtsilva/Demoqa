package br.ce.jcsilva.suites;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.ce.jcsilva.core.DriverFactory;
import br.ce.jcsilva.core.ReportManager;
import br.ce.jcsilva.test.TesteCadastro;
import br.ce.jcsilva.test.TesteLogin;
import br.ce.jcsilva.test.TestePesquisa;


@RunWith(Suite.class)
@SuiteClasses({
	TesteCadastro.class,
	TesteLogin.class,
	TestePesquisa.class
})
public class SuiteTeste {
	private static final ReportManager reportManager = ReportManager.getInstance();
	
	@BeforeClass
	public static void inicializa() {
		//page.iniciarDriver();
		DriverFactory.getDriver();
		ReportManager.initReports();
		if (reportManager == null) {
            throw new IllegalStateException("ReportManager não foi inicializado");
        }
	}
	
	@AfterClass
	public static void finalizaTudo() {
		ReportManager.flushReports();
		DriverFactory.killDriver();
  }
}
