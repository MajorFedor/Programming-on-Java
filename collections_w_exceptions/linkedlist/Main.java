package collections_w_exceptions.linkedlist;

import collections_w_exceptions.linkedlist.Exceptions.ErrorIndexException;

public class Main {
    public static void main(String[] args){
        MyLinkedList list = new MyLinkedList(5);

        list.addToTail(11);
        list.addToTail(26);
        list.addToTail(65);

        System.out.println("Size: " + list.getSize());

        list.addtohead(374);

        System.out.println("Head: " + list.getbyId(0));

        try {
            list.addtoMiddle(4, 134);
        } catch (ErrorIndexException e) {
            System.out.println(e.getMessage());
        }
        

        System.out.println("Index 2: " + list.getbyId(2));
        System.out.println("Size: " + list.getSize());


        try {
            list.rempveByIndex(2);
        } catch (ErrorIndexException e) {
            System.out.println(e.getMessage());
        }
        

        System.out.println("After remove index 2: " + list.getbyId(2));
        System.out.println("Size: " + list.getSize());

        list.rempveAllElements();

        System.out.println("Size after clear: " + list.getSize());
    }
}
