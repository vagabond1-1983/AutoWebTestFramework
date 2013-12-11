package com.kong.util.image;

import com.kong.util.log.LogUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageUtil {
    private static final Logger logger = LogUtil.getLogger(ImageUtil.class);

    /**
     * fileName capture screen shot
     */
    public static void captureScreenshot(WebDriver driver, String fileName) {
        String screenshotPath = System.getProperty("user.dir") + File.separator
                + "screenshot";
        if (!new File(screenshotPath).exists()) {
            new File(screenshotPath).mkdir();
        }
        // Add date and time in current picture file name
        SimpleDateFormat sdf = new SimpleDateFormat(
                "yyyyMMdd HHmmss");
        String currentDateTime = sdf.format(new Date());
        fileName = fileName + "-" + currentDateTime;
        String imagePath = screenshotPath + File.separator + fileName + ".png";
        try {
            String base64Screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BASE64);
            byte[] decodedScreenshot = Base64.decodeBase64(base64Screenshot
                    .getBytes());
            File imageFile = new File(imagePath);
            if (!imageFile.exists()) {
                if (imageFile.createNewFile()) {
                    logger.debug("New File: " + imageFile.getName() + " has been created!");
                }
            }
            FileOutputStream fos = new FileOutputStream(imageFile);
            fos.write(decodedScreenshot);
            fos.close();
            logger.debug("Put screen shot to " + imagePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
