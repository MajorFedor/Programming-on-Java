package collections_w_exceptions.linkedlist;

import java.util.Scanner;

import collections_w_exceptions.linkedlist.Exceptions.ErrorIndexException;
import collections_w_exceptions.linkedlist.Exceptions.IdOutOfSizeError;
import collections_w_exceptions.linkedlist.Exceptions.InvalidCapacityException;
import collections_w_exceptions.linkedlist.Exceptions.InvalidDataException;

public class MyLinkedList {
    private Node head;
    private Node tail;
    private int size;
    private int capacity;

    public MyLinkedList(int cap){
        if(cap > 0){
            this.capacity = cap;
        } else {
            throw new InvalidCapacityException(cap);
        }
    }

    public void addToTail(Integer data) throws InvalidDataException {
        if(data == null || data < 0){
            throw new InvalidDataException(data);
        }
        Node newNode = new Node(data);
        if(head == null){
            head = newNode;
            tail = newNode;
        } else {
            newNode.setPreviousNode(tail);
            tail.setNextNode(newNode);
            tail = newNode;
        }
        size++;
    }

    public void addtoMiddle(Integer index, Integer data) throws ErrorIndexException {
        if(data == null || data < 0){
            throw new InvalidDataException(data);
        }
        if(index < 0 || index >= size){
            throw new ErrorIndexException(index);
        }
        Node newNode = new Node(data);
        Node current = head;
        for(int i = 0; i < index; i++){
            current = current.getNextNode();
        }
        Node previousNode = current.getPreviousNode();
        Node nextNode = current.getNextNode();
        newNode.setNextNode(previousNode);
        newNode.setPreviousNode(current);
        newNode.setNextNode(nextNode);
        size++;
    }

    public void addtohead(Integer data) {
        if(data == null || data < 0){
            throw new InvalidDataException(data);
        }
        Node newNode = new Node(data);
        if(head == null){
            head = newNode;
            tail = newNode;
            size++;
        } else {
            newNode.setNextNode(head);
            head.setPreviousNode(newNode);
            head = newNode;
            size++;
        }
    }

    public int getbyId(Integer id) {
        if(id < 0 || id >= size){
            throw new IdOutOfSizeError(id);
        }
        Node currentNode = head;
        for(int i = 0; i < id; i++){
            currentNode = currentNode.getNextNode();
        }
        return currentNode.getData();
    }

    public Integer getSize(){
        return size;
    }

    public Integer getCapacity(){
        return capacity;
    }

    public void rempveByIndex(Integer index) throws ErrorIndexException {
        if(index < 0 || index > size){
            throw new ErrorIndexException(index);
        }
        Node currentNode = head;
        Node prevNode;
        Node nextNode;
        for(int i = 0; i < index; i++){
            currentNode = currentNode.getNextNode();
        }
        prevNode = currentNode.getPreviousNode();
        nextNode = currentNode.getNextNode();
        if(index == 0){
            head = currentNode.getNextNode();
        } else {
            prevNode.setNextNode(nextNode);
        }
        if(index >= size){
            tail = currentNode.getPreviousNode();
        } else {
            nextNode.setPreviousNode(prevNode);
        }
        size++;
    }

    public void rempveAllElements(){
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("You sure want delete all elements? Type Y or N");
            char agreement = sc.next().charAt(0);
            if(agreement == 'Y'){
                Node current = head;
                while(current != null){
                    Node next = current.getNextNode();
                    current.setPreviousNode(null);
                    current.setNextNode(null);
                    current = next;
                }
                size = 0;
            } else {
                return;
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Type valid char");
            sc.next();
        } finally {
            sc.close();
        }
    }
}