package com.com.saucedemo.utilities;

import com.aventstack.extentreports.Status;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;

/**
 * Cucumber plugin that bridges Cucumber events to ExtentReports.
 * Logs scenario names, step text, step results, and flushes the report at the end of the run.
 */
public class CucumberReportListener implements ConcurrentEventListener {

    /** Registers all Cucumber event handlers on the provided publisher. */
    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestCaseStarted.class,  this::onTestCaseStarted);
        publisher.registerHandlerFor(TestStepStarted.class,  this::onTestStepStarted);
        publisher.registerHandlerFor(TestStepFinished.class, this::onTestStepFinished);
        publisher.registerHandlerFor(TestRunFinished.class,  this::onTestRunFinished);
    }

    /** Creates a new ExtentTest node for the scenario and assigns its tags as categories. */
    private void onTestCaseStarted(TestCaseStarted event) {
        String scenarioName = event.getTestCase().getName();
        ReportUtils.test = ReportUtils.getInstance().createTest(scenarioName);
        event.getTestCase().getTags().forEach(tag ->
                ReportUtils.test.assignCategory(tag.replace("@", ""))
        );
    }

    /** Logs the Gherkin keyword and step text to the report before the step body executes. */
    private void onTestStepStarted(TestStepStarted event) {
        if (!(event.getTestStep() instanceof PickleStepTestStep)) return;
        PickleStepTestStep step = (PickleStepTestStep) event.getTestStep();
        String keyword = step.getStep().getKeyword().trim();
        String text    = step.getStep().getText();
        // Log the step text BEFORE the screenshots produced by the page methods
        ReportUtils.test.log(Status.INFO,
                "<b style='color:#5b9bd5'>[" + keyword + "]</b> " + text);
    }

    /** Logs the step result (PASS / FAIL / SKIP) and the error message when applicable. */
    private void onTestStepFinished(TestStepFinished event) {
        if (!(event.getTestStep() instanceof PickleStepTestStep)) return;
        io.cucumber.plugin.event.Status status = event.getResult().getStatus();
        switch (status) {
            case PASSED:
                ReportUtils.test.log(Status.PASS, "Step completado exitosamente");
                break;
            case FAILED:
                Throwable error = event.getResult().getError();
                ReportUtils.test.log(Status.FAIL,
                        error != null ? error.getMessage() : "Step fallido");
                break;
            case SKIPPED:
                ReportUtils.test.log(Status.SKIP, "Step omitido");
                break;
            default:
                break;
        }
    }

    /** Flushes all buffered test data and writes the final HTML report to disk. */
    private void onTestRunFinished(TestRunFinished event) {
        ReportUtils.getInstance().flush();
    }
}
