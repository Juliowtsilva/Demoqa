package br.ce.jcsilva.core;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class BaseTest {


	@Rule
	public TestName testName = new TestName();


	public void tiraPrint() throws IOException{
		TakesScreenshot ss = (TakesScreenshot) DriverFactory.getDriver();
		File arquivo = ss.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(arquivo, new File ("C:" + File.separator + "Users"+ File.separator + File.separator + "jcrec" + File.separator + "OneDrive" + File.separator + "Imagens" + File.separator + "Screenshot Pitang" + File.separator + testName.getMethodName() + ".jpg"));
		}
	
	@After
	public void finaliza() throws IOException{
		//TakesScreenshot ss = (TakesScreenshot) DriverFactory.getDriver();
		//File arquivo = ss.getScreenshotAs(OutputType.FILE);
		//FileUtils.copyFile(arquivo, new File ("target" + File.separator + "screenshot"+ File.separator + testName.getMethodName() + ".jpg"));

		if(Propriedades.FECHAR_BROWSER) {
		DriverFactory.killDriver();
	}
}
}
