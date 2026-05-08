package collections_w_exceptions.linkedlist;

import collections_w_exceptions.linkedlist.Exceptions.ErrorIndexException;

public class Main {
    public static void main(String[] args){
        MyLinkedList list = new MyLinkedList(5);

        list.addToTail(11);
        list.addToTail(62);
        list.addToTail(23);
        list.addToTail(65);
        list.addToTail(32);
        list.addToTail(29);
        
        print(list);

        list.sort();
        print(list);

        list.sort(new NodeComparator());
        print(list);

        System.out.println("Size: " + list.getSize());

        list.addtohead(374);

        
        print(list);
        list.sort();
        print(list);
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

    public static void print(MyLinkedList list) {
        for (int i = 0; i < list.getSize(); i++) {
            System.out.print(list.getbyId(i));
            if (i < list.getSize() - 1) System.out.print(" - ");
        }
        System.out.println();
    }
}
