public class Node<T extends Comparable<? super T>>{
    protected Node<T> left;
    protected Node<T> right;

    //double-threaded 
    /*
    protected boolean hasLeftThread = true;
    protected boolean hasRightThread = true;
    */

    protected int priority;

    public Node(int p){
        left = null;
        right = null;

        priority = p;
    }
}