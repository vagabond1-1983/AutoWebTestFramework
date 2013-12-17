import com.kong.util.xmlDataParsing.MethodAutoMatches;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 12/10/13
 * Time: 9:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestMethodAutoMatches {
    @Test
    public void methodExecTest() {
        try {
            Class<?> clazz = Class.forName("com.LoginPage");
            MethodAutoMatches.methodExec(null, clazz, "loginForm", "vagabond1_1983@163.com", "Vagabond1");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
