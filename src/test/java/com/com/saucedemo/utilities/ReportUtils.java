package com.com.saucedemo.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/** Singleton accessor for the ExtentReports instance and the current active test node. */
public class ReportUtils {

    private static ExtentReports extent;

    /** Shared test node updated by {@code CucumberReportListener} before each scenario runs. */
    public static ExtentTest test;

    /**
     * Returns the single ExtentReports instance, creating and configuring it on first call.
     * The output report is written to {@code reports/index.html}.
     */
    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("reports/index.html");
            spark.config().setTheme(Theme.DARK);
            spark.config().setDocumentTitle("SauceDemo Test Report");
            spark.config().setReportName("Automation Test Results");
            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Application", "SauceDemo");
            extent.setSystemInfo("Browser", "Chrome");
        }
        return extent;
    }
}
