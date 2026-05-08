package collections_w_exceptions.linkedlist;

import java.util.Comparator;

public class NodeComparator implements Comparator<Node>{
    @Override
    public int compare(Node a, Node b){
        return a.getData() - b.getData();
    }
}
