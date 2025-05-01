package stepdefs;

import java.io.IOException;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.*;
import utils.ErrorCaptureUtil;

public class Hooks {
    public static ExtentReports extent;
    public static ExtentTest test;

    @BeforeAll
    public static void setup() {
        ExtentSparkReporter spark = new ExtentSparkReporter("target/extent-report.html");
        spark.config().setReportName("API Test Results");
        spark.config().setDocumentTitle("API Test Report");

        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        test = extent.createTest(scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
        	test.fail("Scenario Failed: " + scenario.getName());

            // Capture failure info and attach
            String logPath = ErrorCaptureUtil.captureFailureDetails("Scenario failed at step: " + scenario.getStatus());
            test.addScreenCaptureFromPath(logPath, "Failure Log");
        } else {
            test.pass("Scenario Passed");
        }
    }

    @AfterAll
    public static void tearDown() {
        extent.flush();
    }
}
