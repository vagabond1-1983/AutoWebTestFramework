import com.kong.common.handle.DriverHandler;
import com.kong.util.webpage.JSExecutor.JQuery;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 11/12/13
 * Time: 10:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class JQueryTest {

    public static void main(String[] args) {
        try {
            WebDriver driver = DriverHandler.getDriver("firefox");
            driver.get("http://www.baidu.com");
            JQuery.getInstance((JavascriptExecutor)driver).runJs("jQuery('#kw').val('软件测试');");
            JQuery.getInstance((JavascriptExecutor)driver).runJs("jQuery('#su').click();");

            Thread.sleep(5000);

            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
