package week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int size;
    private Node head, tail;

    // construct an empty deque
    public Deque() {
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item can't be null");
        }
        Node newNode = new Node(item);
        if (size == 0) {
            tail = newNode;
            head = newNode;
        } else {
            head.next = newNode;
            newNode.prev = head;
            head = newNode;
        }
        ++size;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item can't be null");
        }
        Node newNode = new Node(item);
        if (size == 0) {
            tail = newNode;
            head = newNode;
        } else {
            tail.prev = newNode;
            newNode.next = tail;
            tail = newNode;
        }
        ++size;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Can't remove from an empty queue");
        }
        Node removeNode = head;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = removeNode.prev;
            head.next = null;
        }
        --size;
        return removeNode.data;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Can't remove from an empty queue");
        }
        Node removeNode = tail;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            tail = removeNode.next;
            tail.prev = null;
        }
        --size;
        return removeNode.data;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node iterator = head;

            @Override
            public boolean hasNext() {
                return iterator != null;
            }

            @Override
            public Item next() {
                if (iterator == null)
                    throw new NoSuchElementException("No more elements to iterate");
                Item value = iterator.data;
                iterator = iterator.prev;
                return value;
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(3);
        System.out.println("Size: " + deque.size());
        for (Integer integer : deque) {
            System.out.print(integer + "-");
        }
        System.out.println("end");
        System.out.println(deque.removeFirst());
        System.out.println("Size: " + deque.size());
        System.out.println(deque.removeFirst());
        System.out.println("Size: " + deque.size());
        System.out.println(deque.removeLast());
        System.out.println("Size: " + deque.size());
        try {
            deque.addFirst(null);
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        }

        try {
            deque.removeLast();
        } catch (NoSuchElementException nse) {
            System.out.println(nse.getMessage());
        }
    }

    private class Node {
        Item data;
        Node prev, next;

        Node(Item data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }
}
