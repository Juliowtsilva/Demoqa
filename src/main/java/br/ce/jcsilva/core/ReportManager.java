package br.ce.jcsilva.core;

	import com.aventstack.extentreports.ExtentReports;
	import com.aventstack.extentreports.ExtentTest;
	import com.aventstack.extentreports.reporter.ExtentSparkReporter;

	public class ReportManager {
	    private static ExtentReports extent;
	    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	    private static ReportManager instance;
	    
	    public static void initReports() {
	        extent = new ExtentReports();
	        ExtentSparkReporter spark = new ExtentSparkReporter("C:\\Users\\jcrec\\eclipse-workspace\\ProjetoPitang\\Reports\\extent-report.html");
	        spark.config().setDocumentTitle("Automation Report");
	        spark.config().setReportName("Selenium Tests");
	        extent.attachReporter(spark);
	    }
	    public static synchronized ReportManager getInstance() {
	        if (instance == null) {
	            instance = new ReportManager();
	        }
	    
	    
	        return instance;
	    }
	    
	    public static void createTest(String testName) {
	        if (extent == null) {
	            initReports(); 
	        }
	        test.set(extent.createTest(testName));
	    }
	    
	    public static void logPass(String message) {
	        test.get().pass(message);
	    }
	    
	    public static void logFail(String message) {
	        test.get().fail(message);
	    }
	    
	    public static void logInfo(String message) {
	        if (test.get() == null) {
	            throw new IllegalStateException("Test not created. Call createTest() first.");
	        }
	        test.get().info(message);
	    }
	    

	    public static void flushReports() {
	        if (extent != null) {
	            extent.flush();
	        }
	    }

}
