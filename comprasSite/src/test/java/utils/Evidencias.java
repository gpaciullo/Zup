package utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Evidencias {
    final static String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";
    public static String imgname = null;

    /*
     * Method to Capture Screenshot for the failures in Selenium with TestName and Timestamp appended.
     */
    // take screen shot on the test failures
    public void takeScreenShot(WebDriver driver, String fileName) {
        File screenShot = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);
        try {
        	
            FileUtils.copyFile(screenShot, new File("src/main/webapp/screen-captures/" + fileName + ".png"));

        } catch (IOException ioe) {
            throw new RuntimeException(ioe.getMessage(), ioe);
        }
    }

}
