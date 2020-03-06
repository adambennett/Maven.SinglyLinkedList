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
    private static SinglyLinkedList<Model> genericTest;
    private static String adam;
    private static String is;
    private static String cool;
    private static Model modelTest;
    private static Model modelTestBig;
    private static Model modelTestReallySmall;
    private static Model modelTestFalse;
    private static Model modelTestFalseB;

    @Before
    public void setup() {
        testList = new SinglyLinkedList<>();
        genericTest = new SinglyLinkedList<>();
        adam = "Adam";
        is = "Is";
        cool = "cool";
        modelTest = new Model(10, "Adam", true);
        modelTestBig = new Model(25, "Adam", true);
        modelTestReallySmall = new Model(2, "A", true);
        modelTestFalse = new Model(6, "A Long String of Words", true);
        modelTestFalseB = new Model(15, "Short", false);
        testList.add(adam);
        testList.add(is);
        testList.add(cool);
        genericTest.add(modelTestFalseB);
        genericTest.add(modelTest);
        genericTest.add(modelTestFalse);
        genericTest.add(modelTestBig);
        genericTest.add(modelTestReallySmall);
    }

    @Test
    public void remove() {
        testList.remove(adam);
        Integer expected = 2;
        Integer actual = testList.size();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void removeGeneric() {
        genericTest.remove(modelTest);
        Integer expected = 4;
        Integer actual = genericTest.size();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void contains() {
        Assert.assertTrue(testList.contains("Adam"));
        Assert.assertTrue(testList.contains(adam));
        Assert.assertTrue(genericTest.contains(modelTest));
    }

    @Test
    public void find() {
        Integer expected = 2;
        Integer actual = testList.find(cool);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findGeneric() {
        Integer expected = 1;
        Integer actual = genericTest.find(modelTest);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void size() {
        Assert.assertTrue(testList.size() == 3);
        Assert.assertTrue(genericTest.size() == 5);
        Assert.assertFalse(genericTest.size() == 2);
    }

    @Test
    public void get() {
        String actual = testList.get(1);
        String expected = is;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getGeneric() {
        Model actual = genericTest.get(1);
        Model expected = modelTest;
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
    public void copyGeneric() {
        SinglyLinkedList<Model> copy = genericTest.copy();
        Assert.assertTrue(copy.size() == genericTest.size());
        for (int i = 0; i < copy.size(); i++) {
            Assert.assertTrue(copy.get(i).equals(genericTest.get(i)));
        }
    }

    @Test
    public void sort() {
        testList.sort();
        Assert.assertTrue(testList.get(1).equals(cool));
    }

    @Test
    public void sortGeneric() {
        genericTest.sort();
        Assert.assertTrue(genericTest.get(0).equals(modelTestBig));
        Assert.assertTrue(genericTest.get(1).equals(modelTest));
        Assert.assertTrue(genericTest.get(2).equals(modelTestReallySmall));
        Assert.assertTrue(genericTest.get(3).equals(modelTestFalse));
        Assert.assertTrue(genericTest.get(4).equals(modelTestFalseB));
    }

    @Test
    public void reverse() {
        testList.reverse();
        Assert.assertTrue(testList.get(0).equals(cool));
    }

    @Test
    public void reverseGeneric() {
        genericTest.reverse();
        Assert.assertTrue(genericTest.get(0).equals(modelTestReallySmall));
        Assert.assertTrue(genericTest.get(1).equals(modelTestBig));
        Assert.assertTrue(genericTest.get(2).equals(modelTestFalse));
        Assert.assertTrue(genericTest.get(3).equals(modelTest));
        Assert.assertTrue(genericTest.get(4).equals(modelTestFalseB));
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

    @Test
    public void sliceGeneric() {
        SinglyLinkedList<Model> sliced = genericTest.slice(0, 2);
        Assert.assertTrue(sliced.size() == 2);

    }

    @Test(expected = IllegalArgumentException.class)
    public void sliceExceptionGeneric() {
        SinglyLinkedList<Model> sliced = genericTest.slice(0, 18);
        Assert.assertTrue(sliced.size() == 2);

    }
}