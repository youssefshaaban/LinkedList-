package implement_collectionl;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joe
 * @param <E>
 */
public class LinkedListStructure<E> {

    private final Node head;
    private int listcount;

    // constructor to intilize list coutn and head to referance frist node
    public LinkedListStructure() {
        this.listcount = 0;
        head = new Node(null);
    }

    // add object in list 
    public void add(E object) {

        Node preparedNoded = new Node(object);   // prepare object node first 

        // check first head referance to null 
        if (head.next == null) {
            head.next = preparedNoded;
        } else {
            Node counterrefrance = head.next;
            while (counterrefrance.next != null) {
                counterrefrance = counterrefrance.next;
            }
            counterrefrance.next = preparedNoded;
        }
        listcount++;
    }

    // add object into list with index
    public void add(int index, E object) {
        Node preparedNoded = new Node(object);   // prepare object node first 
        if (head.next == null) {// to put first node in list
            head.next = preparedNoded;
        } else if (index == 0) {
            Node temp = head.next;
            head.next = preparedNoded;
            head.next.next = temp;
        } else {  // when to insert into 

            checkRangeAddIndex(index);
            Node previousref = head.next;
            Node frontref = head.next;
            for (int i = 0; i < index; i++) {
                previousref = frontref;
                frontref = frontref.next;
            }
            previousref.next = preparedNoded;
            preparedNoded.next = frontref;
        }
        listcount++;
    }

    public E get(int index) {
        E token = null;

        checkRangeIndex(index);
        Node counterRef = head.next;
        for (int i = 0; i < index; i++) {
            counterRef = counterRef.next;
        }
        token = counterRef.data;
        return token;

    }

    public void remove(int index) {
        if (!isEmpty()) {
            checkRangeIndex(index);
            Node preRef=head.next;
            Node froRef=head.next;
            if (index==0) {
                head.next=head.next.next;
            }
            for (int i = 0; i < index; i++) {
                preRef=froRef;
                froRef=froRef.next;
            }
            preRef.next=froRef.next;
        }
    }

// like traverse on list
    @Override
    public String toString() {
        String result = "[";
        if (head.next == null) {
            return result.concat("]");
        } else {
            try {
                Node reCounter = head.next;
                while (reCounter.next != null) {
                    E object = reCounter.data;
                    result = result + object + ",";
                    reCounter = reCounter.next;
                }
                E object = reCounter.data;
                result = result + object;
            } catch (Exception e) {
                System.err.println("" + e.getMessage());
            }

            return result = result.concat("]");
        }

    }

    public int getSize() {
        return listcount;
    }

    // check bound of index out of range from 0 to size of list
    private void checkRangeAddIndex(int index) {
        if (index > listcount || index < 0) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    // check bound of index out of range from 0 to size of list
    private void checkRangeIndex(int index) {
        if (index >= listcount || index < 0) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }

    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + listcount;
    }

    public boolean isEmpty() {
        return head.next == null && listcount == 0;
    }

    private class Node {

        Node next;
        E data;

        public Node(E data) {
            next = null;
            this.data = data;
        }
    }

}
