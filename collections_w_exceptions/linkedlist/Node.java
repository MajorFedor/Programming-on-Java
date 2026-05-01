package collections_w_exceptions.linkedlist;

class Node {
    private Node previous;
    private Integer data;
    private Node next;

    public Node(Integer data){
        this.previous = null;
        this.data = data;
        this.next = null;
    }

    public Node getPreviousNode(){
        return previous;
    }

    public void setPreviousNode(Node node){
        this.previous = node;
    }

    public Integer getData(){
        return data;
    }

    public void setData(Integer data){
        this.data=data;
    }

    public Node getNextNode(){
        return next;
    }

    public void setNextNode(Node node){
        this.next = node;
    }
}