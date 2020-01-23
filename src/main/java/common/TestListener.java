package common;

import common.logger.LogInstance;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private String testMethodName;
    private Logger log;
    private ScreenshotUtils utilities = new ScreenshotUtils();

    private String screenshotPath = String.format("%s\\target\\test-output\\screenshots\\%s\\%s\\%s.png",
            System.getProperty("user.dir"), DateTimeUtils.getTodayDate(), testMethodName, DateTimeUtils.getSystemTime());

    @Override
    public void onTestStart(ITestResult iTestResult) {
        this.testMethodName = iTestResult.getMethod().getMethodName();
        this.log = LogInstance.getLogger();
        log.info("[starting " + testMethodName + "]");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log.info("[test " + testMethodName + " finished successfully]");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        utilities.getScreenshotFile(screenshotPath);
        utilities.saveScreenshot(utilities.getScreenshotByte());
        log.info("[test " + testMethodName + " failed]");
        getErrorLog(iTestResult);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        utilities.getScreenshotFile(screenshotPath);
        log.info("[test " + testMethodName + " failed but with with success percentage]");
        getErrorLog(iTestResult);
    }

    @Override
    public void onStart(ITestContext iTestContext) {
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        log.removeAllAppenders();
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        this.testMethodName = iTestResult.getMethod().getMethodName();
        log.info("[test " + testMethodName + " skipped]");
    }

    private void getErrorLog(ITestResult iTestResult) {
        log.error(iTestResult.getThrowable().toString());
        StringBuilder stackTrace = new StringBuilder("\n");
        for (StackTraceElement element : iTestResult.getThrowable().getStackTrace()) {
            stackTrace.append(element.toString()).append("\n");
        }
        log.error(stackTrace.toString());
    }
}
