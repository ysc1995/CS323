/**
 * The RBTree class implements the Red-Black Tree.
 *
 * @author: Shaocheng Yang-syan248 -- I did the homework by myself.
 * @reference: textBook
 */
public class RBTree {

    private TreeNode root;
    private TreeNode nil=new TreeNode(null,null);

    /**
     * The default constructer constructs a tree with an empty root
     * colored black.
     */
    public RBTree(){
        root=nil;
        nil.color=true;
        nil.key="hi";
        nil.value=null;
    }

    /**
     * Creat a RBTree node using the given key-value pair and add the new
     * node to the existing tree.
     * If the tree is empty, set the new node as the root of the tree. If not,
     * start by comparing the new node against the
     * root node; if the key of the new node is smaller than the key of the
     * root, compare the new node to the root of the left subtree; otherwise,
     * compare the new node to the root of the right subtree. Repeat this step
     * until either reaching the correct position or reaching a leaf node and
     * insert the new node in that position. If the key already exists, update
     * the original value associated to the given value.
     * After insertion, call the fixUp method to retain the property of the
     * Red-Black tree.
     * @param key
     * @param value
     */
    public void add(String key,String value){

        TreeNode x = this.root;
        TreeNode y = this.nil;
        TreeNode z = new TreeNode(key,value);	//create a new node

        while (!x.equals(this.nil)){			//search the tree to find the place to insert the node
            y=x;
            if (z.key.compareTo(x.key)<0){
                x= x.left;
            }
            else if (z.key.compareTo(x.key)==0){		//if already in the tree, update the value
                x.value=z.value;
                return;
            }
            else{
                x=x.right;
            }
        }
        z.p=y;
        if(y.equals(this.nil)){					//if it is an empty tree, add the node
            this.root = z;
        }
        else if (z.key.compareTo(y.key)<0){		//add the node
            y.left=z;

        }
        else{
            y.right=z;
        }
        z.left=this.nil;
        z.right=this.nil;
        z.color=false;							//set its color to red which is false
        insertFixUp(z);

    }

    /**
     * Left rotate the subtree rooted the given node x. The rotation is done
     * through three steps. Let y be x's right child. First set y's right
     * child as x's left child; then link x's parent as y's parent; finally,
     * set x as y's left child and y as x's parent.
     * @param x
     */
    private void LeftRotate(TreeNode x) {
        TreeNode y = x.right;
        x.right=y.left;
        if (!y.left.equals(this.nil)){
            y.left.p=x;
        }
        y.p=x.p;
        if (x.p.equals(this.nil)){
            this.root=y;
        }
        else if (x.equals(x.p.left)){
            x.p.left=y;
        }
        else {
            x.p.right=y;
        }
        y.left=x;
        x.p=y;
    }

    /**
     * Right rotate the subtree rooted in the given node x. The rotation is
     * done in three steps. Let y be the left child of the given node x. First
     * set y's right child as x's left child; then link x's parent as y's parent;
     * finally, set x as y's right child and y as x's parent.
     * @param x
     */
    private void RightRotate(TreeNode x) {
        TreeNode y = x.left;
        x.left=y.right;
        if (!y.right.equals(this.nil)){
            y.right.p=x;
        }
        y.p=x.p;
        if (x.p.equals(this.nil)){
            this.root=y;
        }
        else if (x.equals(x.p.right)){
            x.p.right=y;
        }
        else {
            x.p.left=y;
        }
        y.right=x;
        x.p=y;

    }

    /**
     * This method fixes the RBTree after an insertion of a new node
     * in order to maintain the color property of the red-black tree
     * and in the mean time, balance the RBTree. There are three possible cases.
     * In case 1, the given node z has a Red parent whose sibling is also
     * Red. In case 2, the given node z has a Red parent who has a Black sibling
     * while z is an inside grandchild. In case 3, the given node z has a Red
     * parent who has a Black sibling but z is an outside grandchild. Since
     * fixing the color of the current node might cause violation further up the tree,
     * a while-loop is used to keep fixing the color until there is no violation or
     * we have reach the root of the tree.
     * @param z
     */
    private void insertFixUp(TreeNode z) {
        while (z.p.color==false){
            // considering if z is a left grandchild
            if (z.p.equals(z.p.p.left)){
                TreeNode y=z.p.p.right;
                // case 1: recolor z's parent and parent's sibling as Black
                // recolor z's grandparent as Red
                if(y.color==false){
                    z.p.color=true;
                    y.color=true;
                    z.p.p.color=false;
                    z=z.p.p;
                }
                else {
                    // case 2: left-rotating around z's parent
                    if(z.equals(z.p.right)){
                        z=z.p;
                        LeftRotate(z);
                    }
                    // case 3: right-rotating around z's parent
                    z.p.color=true;
                    z.p.p.color=false;
                    RightRotate(z.p.p);

                }
            }
            // considering if z is a right grandchild
            else {
                TreeNode y=z.p.p.left;
                if(y.color==false){
                    z.p.color=true;
                    y.color=true;
                    z.p.p.color=false;
                    z=z.p.p;
                }
                else {
                    if(z.equals(z.p.left)){
                        z=z.p;
                        RightRotate(z);
                    }
                    z.p.color=true;
                    z.p.p.color=false;
                    LeftRotate(z.p.p);

                }

            }
        }
        this.root.color=true;

    }

    /**
     * This method deletes a given treenode u and replaces
     * it with a given treenode v.
     * @param u
     * @param v
     */
    public void transplant(TreeNode u, TreeNode v){
        if (u.p.equals(this.nil)){
            this.root=v;
        }
        else if (u.equals(u.p.left)){
            u.p.left=v;
        }
        else{
            u.p.right = v;
        }
        v.p=u.p;
    }

    /**
     * Public-facing method that calls a private delete method to
     * remove the node that has the given key from the tree.
     * If the node is not found, do nothing.
     * @param key
     */
    public void remove(String key){
        TreeNode target=searchnode(key);
        if(target !=null){
        delete(target);
        }
    }

    /**
     * The private method delete removes the given treenode from the
     * tree. This is the same as removing a node from a normal BSTree
     * where we are essentially removing either a leaf or a node with only
     * one child.
     * @param z
     */
    private void delete(TreeNode z) {
        TreeNode x;
        TreeNode y=z;
        Boolean yoriginalcolor = y.color;
        if (z.left.equals(this.nil)){
            x=z.right;
            transplant(z,z.right);
        }
        else if(z.right.equals(this.nil)){
            x =z.left;
            transplant(z,z.left);
        }
        else {
            y=minimum(z.right);
            yoriginalcolor = y.color;
            x=y.right;
            
            if(y.p.equals(z)){
                x.p=y;
            }
            
            else {
                transplant(y,y.right);
                y.right=z.right;
                y.right.p=y;
            }
            transplant(z,y);
            y.left=z.left;
            y.left.p=y;
            y.color=z.color;

        }
        if(yoriginalcolor==true){		//if the node deleted is black 
            deleteFixUp(x);
        }

    }
    /**
     * The private method deleteFixUp fix the problem that method delete produced.
     * This method deals with 4 cases that could cause the problem. 
     * @param x
     */
    private void deleteFixUp(TreeNode x) {
        while (!x.equals(this.root)&&x.color==true){
            if (x.equals(x.p.left)){
                TreeNode w = x.p.right;
                if (w.color==false){
                    w.color=true;				//case 1 when x’s sibling w is red
                    x.p.color=false;
                    LeftRotate(x.p);
                    w=x.p.right;
                }
                if(w.left.color==true&&w.right.color==true){	//Case 2: x's sibling w is black, and both of w's children are black
                    w.color=false;
                    x=x.p;
                }
                else if(w.right.color==true){	//Case 3: x's sibling w is black, w's left child is red, and w's right child is black
                    w.left.color=true;
                    w.color=false;
                    RightRotate(w);
                    w=x.p.right;
                }
                w.color=x.p.color;	//Case 4: x's sibling w is black, and w's right child is red
                x.p.color=true;
                w.right.color=true;
                LeftRotate(x.p);
                x=this.root;
            }
            else {							//Symmetric as above
                TreeNode w = x.p.left;
                if (w.color==false){
                    w.color=true;
                    x.p.color=false;
                    RightRotate(x.p);
                    w=x.p.left;
                }
                if(w.right.color==true&&w.left.color==true){
                    w.color=false;
                    x=x.p;
                }
                else if(w.left.color==true){
                    w.right.color=true;
                    w.color=false;
                    LeftRotate(w);
                    w=x.p.left;
                }
                w.color=x.p.color;
                x.p.color=true;
                w.left.color=true;
                RightRotate(x.p);
                x=this.root;
            }

        }
        x.color=true;

    }

    /**
     * This methods keeps iterating until it finds and returns the
     * leftmost node in the subtree rooted at the given node n.
     * @param n
     * @return the leftmost descendant
     */
    private TreeNode minimum(TreeNode n) {
        if(n.left!=this.nil){
            n=minimum(n.left);
        }
        return n;
    }

    /**
     * This method returns the value associated with a given key
     * that is stored in the tree. If the key doesn't exist, the
     * method return null instead.
     * @param key
     */
    public String search(String key) {
        TreeNode n=find(key,root);
        return n.value;

    }

    /**
     * This method returns the node where the given key string is stored.
     * @param key
     */
    private TreeNode searchnode(String key){
        return find(key,root);
    }

    /**
     * Traverse through the tree until finding the node that contains the
     * given key value or reaching a leaf node. Return that node if found
     * or null if that node doesn't exist.
     * @param key
     * @param n
     * @return
     */
    private TreeNode find(String key, TreeNode n){
        if(n == null) {
            // the element is not in the tree
            return null;
        } else if (key.equals(n.key)) {
            // we found the element
            return n;
        } else if (key.compareTo(n.key) < 0){
            // go to the left
            return find(key, n.left);
        } else {
            // go to the right
            return find(key, n.right);
        }
    }

    /**
     * The public-facing toString() method calls the private method
     * to print a string representation of the current tree.
     */
    public String toString(){
        return toString(this.root);
    }

    /**
     * Starting from the root node, visit each node in the tree in a pre-order traversal.
     * Construct a string that contains the key, value and color information of each node.
     * @param n
     * @return
     */
    private String toString(TreeNode n){
        if(n==null){
            return	"";
        }
        else if(n.equals(nil)){
            return "";
        }
        else {
            if(n.color==false){
                return "(*"+n.key+":"+n.value+ "("+toString(n.left)+")("+toString(n.right)+"))";
            }
            else{
                return "(#"+n.key+":"+n.value+"("+ toString(n.left)+")("+toString(n.right)+"))";
            }
        }
    }


    /**
     * The treeNode class constructs nodes in the red-black tree.
     */
    class TreeNode{
        boolean color;
        TreeNode left;
        TreeNode right;
        TreeNode p;
        String key;
        String value;
        TreeNode nil;

        /**
         * Default constructor. Construct an empty tree node.
         */
        public TreeNode(){
        }

        /**
         * Construct a node that stores the given key-value pair.
         * @param key
         * @param value
         */
        public TreeNode(String key, String value){
            this.value=value;
            this.key=key;
        }

        /**
         * Override the toString() method so that the node can be printed.
         */
        public String toString(){
            return value;
        }
    }

    /**
     * The main method tests the add, print, remove and search methods of the RBTree class.
     * @param args
     */
    public static void main(String[] args) {
        RBTree tree= new RBTree();
        tree.add("5", "apple");
        System.out.println(tree);
        tree.add("3", "pi");
        System.out.println(tree);
        tree.add("6","hi");
        System.out.println(tree);
        tree.add("7","h");
        tree.add("2","i");
        System.out.println(tree);

        System.out.println("Find 5:");
        if(tree.searchnode("5")==null){
            System.out.println("Node 5 Not Found!");
        }
        else{
            System.out.println(tree.search("5"));
        }
        System.out.println("Find 11:");
        if(tree.searchnode("11")==null){
            System.out.println("Node 11 Not Found!");
        }
        else{
            System.out.println(tree.search("11"));
        }

        System.out.println("remove 5:");
        tree.remove("5");
        System.out.println(tree);
        System.out.println("remove 7:");
        tree.remove("7");
        System.out.println(tree);
        tree.remove("133");
    	
//	second test case------------------------------------
        
//        RBTree t = new RBTree();
//        t.add("K","11");
//        System.out.println(t);
//        t.add("B", "2");
//        System.out.println(t);
//        t.add("N", "14");
//        System.out.println(t);
//        t.add("A", "1");
//        System.out.println(t);
//        t.add("G", "7");
//        System.out.println(t);
//        t.add("O", "15");
//        System.out.println(t);
//        t.add("E", "5");
//        System.out.println(t);
//        t.add("H", "8");
//        System.out.println(t);
//        t.add("D", "4");
//        
//        System.out.println(t);

//	third test case----------------------------------------------------------
//    	RBTree t = new RBTree();
//    	t.add("k", "1");
//    	System.out.println(t);
//    	t.add("m", "2");
//    	System.out.println(t);
//    	t.add("h", "2");
//    	System.out.println(t);
//    	t.add("x", "2");
//    	System.out.println(t);
//    	t.add("r", "2");
//    	System.out.println(t);
//    	t.add("t", "2");
//    	System.out.println(t);
    	
    }

}
