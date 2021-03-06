package com.zipcodewilmington.singlylinkedlist;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Logger;

/**
 * Created by leon on 1/10/18.
 */
public class SinglyLinkedList<K extends Comparable<K>> implements Comparator<K> {

    private Integer arrSize;
    private Node head;
    private Node tail;

    public SinglyLinkedList() {
        arrSize = 0;
    }

    public Integer add(K data) {
        if (head == null) {
            Node newNode = new Node(data, 0);
            head = newNode;
            tail = newNode;
            arrSize++;
            return 0;
        } else if (tail != null) {
            Node newNode = new Node(data, tail.index + 1);
            tail.next = newNode;
            tail = newNode;
            arrSize++;
            return newNode.index;
        } else {
            Logger.getGlobal().info("Head was not null, but tail was... something bad happened!");
        }
        return -1;
    }

    public Boolean remove(K data) {
        if (head != null) {
            if (head.data.equals(data)) {
                boolean toRet = removeInnerIf();
                if (toRet) { arrSize--; }
                return toRet;
            }
            else {
                boolean toRet = removeInnerElse(data);
                if (toRet) { arrSize--; }
                return toRet;
            }
        }
        return false;
    }

    private Boolean removeInnerIf() {
        if (head.equals(tail)) {
            head = null;
            tail = null;
            arrSize = 0;
            return true;
        } else if (head.hasNext()) {
            head = head.next;
            Node current = head;
            while (current.hasNext()) {
                current.index--;
                current = current.next;
            }
            current.index--;
            return true;
        } else { Logger.getGlobal().info("Bad list behavior, head was the element to be removed, didn't have a next but was also not equal to tail. Investigate remove() method logic");return false; }
    }

    private Boolean removeInnerElse(K data) {
        Node current = head;
        while (current.hasNext()) {
            Node previous = current;
            current = current.next;
            if (current.data.equals(data)) {
                previous.next = current.next;
                while (current.hasNext()) {
                    previous = current;
                    current = current.next;
                    current.index--;
                }
                tail = previous;
                return true;
            }
        }
        return false;
    }


    public Boolean contains(K data) {
        if (head != null) {
            if (head.data.equals(data)) {
                return true;
            }
            Node current = head;
            while (current.hasNext()) {
                current = current.next;
                if (current.data.equals(data)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Integer find(K data) {
        if (contains(data)) {
            Node current = head;
            while (current != null && !current.data.equals(data)) {
                current = current.next;
            }
            if (current != null && current.data.equals(data)) {
                return current.index;
            }
        }
        return -1;
    }

    public Integer size() {
        return arrSize;
    }

    public K get(int index) {
        Node current = head;
        while (current != null && current.index != index) {
            current = current.next;
        }
        if (current != null && current.index == index) {
            return (K) current.data;
        }
        return null;
    }

    public SinglyLinkedList<K> copy() {
        SinglyLinkedList<K> newList = new SinglyLinkedList<>();
        Node current = head;
        while (current != null) {
            newList.add((K) current.data);
            current = current.next;
        }
        return newList;
    }

    public void sort() {
        Node current = head;
        Node index;
        K temp;

        if (head == null) {
            return;
        } else {
            while (current != null) {
                index = current.next;
                while (index != null) {
                    if (compare((K)current.data, (K)index.data) > 0) {
                        temp = (K) current.data;
                        current.data = index.data;
                        index.data = temp;
                    }
                    index = index.next;
                }
                current = current.next;
            }
        }
        updateIndices();
    }

    public void updateIndices() {
        int newIndex = 0;
        Node newHead = head;
        while (newHead.hasNext()) {
            newHead.index = newIndex;
            newIndex++;
            newHead = newHead.next;
        }
    }



    public void reverse() {
        int startIndex = tail.index;
        Node current = tail;
        int oldIndex = current.index;
        current.index = 0;
        while (startIndex > -1 && current != null) {
            current.next = new Node(get(oldIndex - 1), current.index + 1);
            startIndex--;
            current = current.next;
            oldIndex = current.index;
        }

        Node newHead = tail;
        Node newTail = head;
        tail = newTail;
        head = newHead;
    }


    public SinglyLinkedList<K> slice(int startIndex, int endIndex) {
        if (startIndex < 0 || endIndex < startIndex || endIndex > arrSize - 1) {
            throw new IllegalArgumentException("Illegal arguments for slice()!");
        }
        SinglyLinkedList<K> newList = new SinglyLinkedList<>();
        int counter = startIndex;
        while (counter < endIndex) {
            K data = get(counter);
            if (data != null) {
                newList.add(data);
            }
            counter++;
        }
        return newList;
    }

    @Override
    public int compare(K o1, K o2) {
        return o1.compareTo(o2);
    }


    private class Node<K extends Comparable<K>> implements  Comparator<K> {

        private K data;
        private Node next;
        private Integer index;

        public Node(K data, int index) {
            this.data = data;
            this.index = index;
            this.next = null;
        }

        public Boolean hasNext() {
            return this.next != null;
        }

        @Override
        public int compare(K o1, K o2) {
            return o1.compareTo(o2);
        }
    }

}
