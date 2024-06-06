@SuppressWarnings("unchecked")
public class Treap<T extends Comparable<? super T>>{
    public Node<T> root;
    private Object[] dataArray;
    private int n;

    //to avoid random priorities when testing
    private int[] priorityArray = {0, 0, 0, 2, 4, 5, 0, 3};

    public Treap(){
        root = null;

        n = 0;
        dataArray = new Object[100];
    }

    public boolean isEmpty(){
        return (root == null);
    }

    public int getN(){
        return n;
    }
    
    public T getRightValue(T value){
        Node<T> node = find(value);

        if(node != null && node.right != null){
            return getData(node.right);
        }

        return null;
    }

    public T getLeftValue(T value){
        Node<T> node = find(value);

        if(node != null && node.left != null){
            return getData(node.left);
        }

        return null;
    }

    private Node<T> parentNode(Node<T> node, T value){
        if(node == null)
            return null;

        if(node.left != null && value.equals(getData(node.left))){
            //System.out.println("getData(n) = " + getData(n));
            return node;
        }

       if(node.right != null && value.equals(getData(node.right))){
          //System.out.println("getData(n) = " + getData(n));
           return node;
       }
            

       if(value.compareTo(getData(node)) < 0)
            return parentNode(node.left, value);
        else if(value.compareTo(getData(node)) > 0)
            return parentNode(node.right, value);
        else
            return null;
    }

    private int min(int x, int y){
        return ((x < y) ? x : y);
    }

    public void insert(T value){
        //System.out.println();
        //int v = (int)(Math.random() * n);
        //int v = (int)Math.round(Math.random() * n);
        int v = priorityArray[n];
        

        //int priority = (n == 0) ? 0 : (v);//change for array implementation
        //+1
        int priority = v;

        System.out.println();
        System.out.println("n = " + n);
        System.out.println("v = " + v);

        System.out.println("inserting(" + value + ", " + priority + ")");
        

        if(priority == n){
            //array
            dataArray[n] = value;
            n++; 

            System.out.println("priority == n");
        }
        else{
            System.out.println("else");
    


            //BST

            //(T)dataArray[n] or (T)dataArray[priority] ???
            Node<T> node = find((T)dataArray[priority]);
            node.priority = n;

            System.out.println((T)dataArray[priority] + " priority changed from " + priority + " to " + n);

            //array
            dataArray[n] = (T)dataArray[priority];
            dataArray[priority] = value;

            n++;

            while(true){
                if(node.left != null && node.right != null){
                    if(node.left.priority < node.right.priority)
                        rightRotation(node);
                    else
                        leftRotation(node);
                }
                else if(node.left != null){
                    rightRotation(node);
                }
                else if(node.right != null){
                    leftRotation(node);
                }
                else
                    break;
            }//moving node to the bottom
        }


        //insert newNode
        //Node<T> newNode = new Node<T>(value, priority);
        Node<T> newNode = new Node<T>(priority);

        if(this.isEmpty()){
            root = newNode;
            return;
        }
        else{
            Node<T> current = root;

            while(true){
                if(value.compareTo((T)dataArray[current.priority]) < 0){
                    if(current.left != null)
                        current = current.left;
                    else{
                        current.left = newNode;
                        //System.out.println("current.data = " + current.data);
                        break;
                    }
                }
                else{
                    if(current.right != null)
                        current = current.right;
                    else{
                        current.right = newNode;
                        break;
                    }
                }
            }//while not inserted

            Node<T> parent = parentNode(root, value);            

            while((newNode != root && parent != null) && newNode.priority < parent.priority){
                if(value.compareTo((T)dataArray[parent.priority]) < 0){
                    //System.out.println("rightRotation(" + parent.data + ")");
                    rightRotation(parent);
                }
                else{
                    //System.out.println("leftRotation(" + parent.data + ")");
                    leftRotation(parent);
                }

                parent = parentNode(root, value);
            }//moving newNode up as much as necessary

            //maintain heap property 1

            //newNode will not be at the root ???

            //System.out.println("current.data = " + current.data);

            /*
            Node<T> parent = parentNode(root, value);            

            while((newNode != root && parent != null) && newNode.priority > parent.priority){
                if(value.compareTo(parent.data) < 0){
                    //System.out.println("rightRotation(" + parent.data + ")");
                    rightRotation(parent);
                }
                else{
                    //System.out.println("leftRotation(" + parent.data + ")");
                    leftRotation(parent);
                }

                parent = parentNode(root, value);
            }
            */
        }
    }

    public Node<T> find(T value){
        Node<T> current = root;

        while(true){
            if(value.equals(getData(current))){
                return current;
            }

            if(value.compareTo(getData(current)) < 0){
                if(current.left != null)
                    current = current.left;
                else
                    return null;
            }
            else{
                if(current.right != null)
                    current = current.right;
                else
                    return null;
            }
        }
    }

    public void delete(T value){
        if(!this.isEmpty()){
            Node<T> node = find(value);

            System.out.println("node data = " + getData(node));

            if(node != null){
                while(node.left != null && node.right != null){
                    if(node.left.priority < node.right.priority)
                        rightRotation(node);
                    else
                        leftRotation(node);

                    
                }//while has 2 children

                //now has 1 or no children
                
                Node<T> parent = parentNode(root, value);

               

                Node<T> NODE_VALUE = (node.left != null) ? node.left : node.right;//:)

                if(parent != null){
                    System.out.println("parent.data = " + getData(parent));

                    if(getData(node).compareTo(getData(parent)) < 0)
                        parent.left = NODE_VALUE;
                    else
                        parent.right = NODE_VALUE;

                    /*
                    parent.left = (node.data.compareTo(parent.data) < 0) ? NODE_VALUE : parent.left;
                    parent.right = (parent.left != NODE_VALUE) ? NODE_VALUE : parent.right;
                    
                    //difficult to read
                    */

                    /*
                    Node<T> PARENT_LINK = (node.data.compareTo(parent.data) < 0) ? parent.left : parent.right;
                    PARENT_LINK = NODE_VALUE;

                    //does not work this way
                    */
                }
                else
                    root = NODE_VALUE;

                /*
                for(int i = node.priority; i < n; i++){
                    dataArray[i] = dataArray[i+1];
                }
                n--;
                */

                //array
                if(node.priority == n-1){
                    n--;
                    return;
                }
                    

                dataArray[node.priority] = dataArray[n-1];

                int keep = node.priority;

                //BST
                node = find((T)dataArray[n-1]);

                int y = node.priority;

                node.priority = keep;

                
                System.out.println(getData(node) + " priority changed from " + y + " to " + keep);
                /*
                System.out.println("preOrder traversal of t - before change");
                this.preOrder(root);
                System.out.println();
                System.out.println();
                */

                //move as much as up as necessary
                T newValue = getData(node);
                Node<T> p = parentNode(root, newValue);  
                
                System.out.println("p data = " + getData(p));

               

                while((node != root && p != null) && node.priority < p.priority){
                    System.out.println("this does run");
                    if(newValue.compareTo(getData(p)) < 0){
                        //System.out.println("rightRotation(" + parent.data + ")");
                        rightRotation(p);
                    }
                    else{
                        System.out.println("leftRotation(" + getData(p) + ")");
                        System.out.println("parent of p: " + getData(parentNode(root, getData(p))));
                        leftRotation(p);
                    }
    
                    p = parentNode(root, newValue);
                }//moving newNode up as much as necessary

                n--;

            }//node found

            System.out.println("preOrder traversal of t, after deleting " + value);
            if(this.isEmpty())
                System.out.println("the treap is empty!");  
            else{
                this.preOrder(this.root);
                System.out.println();
            } 
            
            System.out.println();
        }//not empty                 
    }

    public void rightRotation(Node<T> p){
        Node<T> g = parentNode(root, getData(p));
        Node<T> c = p.left;

        if(g != null){
            if(getData(p).compareTo(getData(g)) > 0)
                g.right = c;
            else
                g.left = c;
        }
        else{
            root = c;
        }

        p.left = c.right;
        c.right = p;
    }

    public void leftRotation(Node<T> p){
        Node<T> g = parentNode(root, getData(p));
        Node<T> c = p.right;

        if(g != null){
            if(getData(p).compareTo(getData(g)) > 0)
                g.right = c;
            else
                g.left = c;
        }
        else
            root = c;

        p.right = c.left;
        c.left = p;
    }    

    public void preOrder(Node<T> node){
        if(node != null){
            System.out.print(getData(node) + "("+ node.priority + ") ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public T getData(Node<T> node){
        if(node == null)
            return null;
        else
            return (T)(dataArray[node.priority]);
    }
}