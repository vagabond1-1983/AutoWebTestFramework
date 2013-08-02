import org.junit.Test;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: devin
 * Date: 7/29/13
 * Time: 9:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class CollectionTest {
    List<String> list;
    Set<String> set;

    @Test
    public void ArrayListTest() {
        list = new ArrayList<String>(1);
        System.out.println("Array List Test");
        fulfillList(list);
    }

    @Test
    public void LinkedListTest() {
        list = new LinkedList<String>();
        System.out.println("Linked List Test");
        fulfillList(list);
    }

    @Test
    public void VectorTest() {
        list = new Vector<String>(1, 1);
        System.out.println("Vector Test");
        fulfillList(list);
    }


    private void fulfillList(List<String> list) {
        list.add("test1");
        list.add("test2");
        list.add("test3");
        System.out.println("list======" + list.toString());
        System.out.println("get index 2: " + list.get(2));
        list.remove(1);
        for (String i : list) {
            System.out.println("print every item in the list");
            System.out.print(i + " ");
        }
    }

    @Test
    public void HashSetTest() {
        set = new HashSet<String>();
        System.out.println("Hash Set Test");
        fulfillSet(set);
    }


    private void fulfillSet(Set<String> set) {
        set.add("test1");
        set.add("test2");
        set.add("test3");
        System.out.println("Set======" + set.toString());
        System.out.println("contains test2: " + set.contains("test2"));
        set.remove("test1");
        for (String i : set) {
            System.out.println("print every item in the set");
            System.out.print(i + " ");
        }
    }


}
