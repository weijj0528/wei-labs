package com.weiun.guava.multimap;

import com.google.common.collect.*;

/**
 * @author William
 * @Date 2019/3/8
 * @Description
 */
public class MultimapTest {

    public static void main(String[] args) {
        HashMultimap<Integer, String> hashMultimap = HashMultimap.create();
        hashMultimap.put(1, "1");
        hashMultimap.put(1, "2");
        hashMultimap.put(1, "3");
        hashMultimap.put(2, "4");
        System.out.println(hashMultimap);

        ListMultimap<Integer, String> listMultimap = ArrayListMultimap.create();
        listMultimap.put(2, "4");
        listMultimap.put(1, "2");
        listMultimap.put(1, "1");
        listMultimap.put(1, "3");
        System.out.println(listMultimap);

        ListMultimap<Integer, String> linkedListMultimap = LinkedListMultimap.create();
        linkedListMultimap.put(2, "4");
        linkedListMultimap.put(1, "2");
        linkedListMultimap.put(1, "3");
        linkedListMultimap.put(1, "1");
        System.out.println(linkedListMultimap);

        LinkedHashMultimap<Integer, String> linkedHashMultimap = LinkedHashMultimap.create();
        linkedHashMultimap.put(2, "4");
        linkedHashMultimap.put(1, "3");
        linkedHashMultimap.put(1, "2");
        linkedHashMultimap.put(1, "1");
        System.out.println(linkedHashMultimap);

        TreeMultimap<Integer, String> treeMultimap = TreeMultimap.create();
        treeMultimap.put(2, "4");
        treeMultimap.put(1, "3");
        treeMultimap.put(1, "1");
        treeMultimap.put(1, "2");
        System.out.println(treeMultimap);

    }

}
