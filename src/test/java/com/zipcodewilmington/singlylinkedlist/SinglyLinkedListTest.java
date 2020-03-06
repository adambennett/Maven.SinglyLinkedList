package com.zipcodewilmington.singlylinkedlist;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by leon on 1/10/18.
 */
public class SinglyLinkedListTest
{

    private static SinglyLinkedList<String> testList;

    @Before
    public void setup() {
        testList = new SinglyLinkedList<>();
        testList.add("Adam");
        testList.add("Is");
        testList.add("Cool");
    }

    @Test
    public void remove() {
        testList.remove("Adam");
        Integer expected = 2;
        Integer actual = testList.size();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void contains() {
        Assert.assertTrue(testList.contains("Adam"));
    }

    @Test
    public void find() {
        Integer expected = 2;
        Integer actual = testList.find("Cool");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void size() {
        Assert.assertTrue(testList.size() == 3);
    }

    @Test
    public void get() {
        String actual = testList.get(1);
        String expected = "Is";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void copy() {
        SinglyLinkedList<String> copy = testList.copy();
        Assert.assertTrue(copy.size() == testList.size());
        for (int i = 0; i < copy.size(); i++) {
            Assert.assertTrue(copy.get(i).equals(testList.get(i)));
        }
    }

    @Test
    public void sort() {
    }

    @Test
    public void reverse() {
    }

    @Test
    public void slice() {
        SinglyLinkedList<String> sliced = testList.slice(0, 2);
        Assert.assertTrue(sliced.size() == 2);

    }

    @Test(expected = IllegalArgumentException.class)
    public void sliceException() {
        SinglyLinkedList<String> sliced = testList.slice(0, 18);
        Assert.assertTrue(sliced.size() == 2);

    }
}