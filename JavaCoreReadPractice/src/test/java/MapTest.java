import org.junit.Test;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 7/30/13
 * Time: 9:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class MapTest {
    @Test
    public void HashMapTest() {
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, "test1");
        map.put(2, "test2");
        map.put(1, "testEx");
        map.put(3, "test3");
        map.get(1);
        for (Integer i : map.keySet()) {
            System.out.println(map.get(i));
        }
    }


}
