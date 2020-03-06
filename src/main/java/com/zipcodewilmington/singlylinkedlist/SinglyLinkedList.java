package com.zipcodewilmington.singlylinkedlist;

import java.util.Arrays;
import java.util.logging.Logger;

/**
 * Created by leon on 1/10/18.
 */
public class SinglyLinkedList<K> {

    private Node<K>[] nodes;
    private Integer arrSize;
    private Node head;
    private Node tail;

    public SinglyLinkedList() {
        nodes = new Node[0];
        arrSize = 0;
    }

    public Integer add(K data) {
        Node<K>[] newArr = Arrays.copyOf(nodes, nodes.length +1);
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
        Node head = this.head;

        if (head != null && head.getData().equals(data)) {
            this.head = head.getNext();
            this.head.setIndex(this.head.getIndex() - 1);
            Node headRef = this.head;
            while (headRef.hasNext()) {
                headRef = headRef.getNext();
                headRef.setIndex(headRef.getIndex() - 1);
            }
            return true;
        }

        while (head.hasNext()) {
            Node updateRef = head.getNext();
            if (updateRef.getData().equals(data)) {
                head.setNext(updateRef.getNext());
                updateRef.setIndex(head.getIndex() + 1);
                while (updateRef.hasNext()) {
                    Node nextt = updateRef.getNext();
                    nextt.setIndex(updateRef.getIndex() + 1);
                    updateRef = nextt;
                }
                arrSize--;
                return true;
            }
            head = head.getNext();
        }
        return false;
    }


    public Boolean contains(K data) {
        for (Node n : nodes) {
            if (n.getData().equals(data)) {
                return true;
            }
        }
        return false;
    }

    public Integer find(int index) {
        Node head = nodes[0];
        if (head != null && head.getIndex() == index) {
            return head.getIndex();
        }

        while (head.hasNext()) {
            Node next = head.getNext();
            if (next.getIndex() == index) {
                return next.getIndex();
            }
            head = head.getNext();
        }
        return -1;
    }

    public Integer size() {
        return arrSize;
    }

    public K get(int index) {
        Integer indToCheck = find(index);
        if (indToCheck > -1 && indToCheck < nodes.length) {
            return nodes[indToCheck].getData();
        }
        return null;
    }

    public SinglyLinkedList<K> copy() {
        SinglyLinkedList<K> newList = new SinglyLinkedList<>();
        for (Node<K> n : nodes) {
            newList.add(n.getData());
        }
        return newList;
    }

    public void sort() {

    }

    public void reverse() {

    }

    public SinglyLinkedList<K> slice(int startIndex, int endIndex) {
        if (startIndex < 0 || endIndex < startIndex || endIndex > nodes.length) {
            throw new IllegalArgumentException("Illegal arguments for slice()!");
        }
        SinglyLinkedList<K> newList = new SinglyLinkedList<>();
        int counter = startIndex;
        while (counter < endIndex) {
            newList.add(get(find(counter)));
            counter++;
        }
        return newList;
    }


    private class Node<K> {

        private K data;
        private Node next;
        private Integer index;

        public Node(K data) {
           this(data, -1);
        }

        public Node(K data, int index) {
            this.data = data;
            this.index = index;
            this.next = null;
        }

        public K getData() {
            return data;
        }

        public void setData(K data) {
            this.data = data;
        }


        public Node<K> getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Boolean hasNext() {
            return this.next != null;
        }

        public Integer getIndex() {
            return index;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }
    }

}
