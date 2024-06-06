public class Main{
    public static void main(String[] args){
        Treap<Integer> t = new Treap<Integer>();

        System.out.println("output for treap t");
        System.out.println();

        t.insert(8);
        t.insert(5);
        
        t.insert(7);
        t.insert(6);
        
        t.insert(3);
        t.insert(12);

        t.insert(9);

        
        t.insert(13);
        /*
        */
        
        /*
        while(!t.isEmpty()){
            System.out.println("delete(" + t.root.data + ")");
            t.delete(t.root.data);
        }
        */
        //System.out.println("7.right.data = " + t.getRightValue(7));

        
        t.delete(7); 
        t.delete(6);
        t.delete(9);
        t.delete(8);
        t.delete(12);
        t.delete(13);
        t.delete(3);
        t.delete(5);
        /*
        */

        /*
        t.delete(8);
        t.delete(5);
        t.delete(12);
    
        t.delete(13);
        t.delete(3);
        t.delete(7);

        t.delete(6);
        t.delete(9);
        */

        System.out.println("preOrder traversal of t");
        t.preOrder(t.root);
        System.out.println();

        System.out.println("t.n = " + t.getN());
    }
}