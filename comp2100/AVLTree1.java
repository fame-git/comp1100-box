/**
 * Welcome! Make sure to check out 'readme.md' for a rundown of requirements/description of this implementation
 * that may differ from normal implementations. Before starting, it may also be worth checking out Tree.java
 * and BinarySearchTree.java as all method description is contained in the superclass unless edited. For
 * example: the description for 'insert' cannot be found here. It is in the superclass!
 * <p>
 * Please note that you may edit this class as much as you like (i.e.create helper methods if you want!).
 * So long as you genuinely pass the tests (i.e. do not change existing methods signatures). Ask questions if you are
 * lost or unsure.
 * You SHALL NOT edit any other classes.
 * <p>
 * Lastly, if you are looking to better visualise the results of your insertion, you are free print the contents
 * of the method '.display()' (found in Tree.java which class, AVLTree, extends through BinarySearchTree). This
 * method will provide you with a graphical representation of the tree.
 */
public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {
    /*
        As a result of inheritance by using 'extends BinarySearchTree<T>,
        all class fields within BinarySearchTree are also present here.
        So while not explicitly written here, this class has:
            - value
            - leftNode
            - rightNode
     */

    public AVLTree(T value) {
        super(value);
        // Set left and right children to be of EmptyAVL as opposed to EmptyBST.
        this.leftNode = new EmptyAVL<>();
        this.rightNode = new EmptyAVL<>();
    }

    public AVLTree(T value, Tree<T> leftNode, Tree<T> rightNode) {
        super(value, leftNode, rightNode);
    }

    /**
     * @return balance factor of the current node.
     */
    public int getBalanceFactor() {
        /*
             Note:
             Calculating the balance factor and height each time they are needed is less efficient than
             simply storing the height and balance factor as fields within each tree node (as some
             implementations of the AVLTree do). However, although it is inefficient, it is easier to implement.
         */
        return leftNode.getHeight() - rightNode.getHeight();
    }



    @Override
    public AVLTree<T> insert(T element) {
        /*
            TODO: Write and or complete your insertion code here
            Note that what each method does is described in its superclass unless edited.
            E.g. what 'insert' does is described in Tree.java.
         */

        // Ensure input is not null.
        if (element == null)
            throw new IllegalArgumentException("Input cannot be null");
        AVLTree<T> a1;
        AVLTree<T> a2;
        if (element.compareTo(value) > 0) {
            a1 = new AVLTree<>(value,leftNode,rightNode.insert(element));
            int balance=a1.getBalanceFactor();
            if(balance>1 && element.compareTo(a1.leftNode.value)<0){
                a1= a1.rightRotate();
            }

            if(balance<-1 && element.compareTo(a1.rightNode.value)>0){
                a1=a1.leftRotate();
            }

            if(balance>1 && element.compareTo(a1.leftNode.value)>0){
                a1.leftNode=((AVLTree<T>)a1.leftNode).leftRotate();
                a1=a1.rightRotate();
            }

            if(balance<-1 && element.compareTo(a1.rightNode.value)<0){
                a1.rightNode=((AVLTree<T>)a1.rightNode).rightRotate();
                a1=a1.leftRotate();
            }
            return a1;
            // COMPLETE
        } else if (element.compareTo(value) < 0) {

            a2= new AVLTree<>(value, leftNode.insert(element), rightNode);
            int balance=a2.getBalanceFactor();
            if(balance>1 && element.compareTo(a2.leftNode.value)<0){
                a2= a2.rightRotate();
            }

            if(balance<-1 && element.compareTo(a2.rightNode.value)>0){
                a2=a2.leftRotate();
            }

            if(balance>1 && element.compareTo(a2.leftNode.value)>0){
                a2.leftNode=((AVLTree<T>)a2.leftNode).leftRotate();
                a2=a2.rightRotate();
            }

            if(balance<-1 && element.compareTo(a2.rightNode.value)<0){
                a2.rightNode=((AVLTree<T>)a2.rightNode).rightRotate();
                a2=a2.leftRotate();
            }
            return a2;
            // COMPLETE
        } else {
            return this;
            // COMPLETE
        }



        /*
        if (element.compareTo(value) > 0) {
            if(totalnew.rightNode.getHeight()!=-1){
                totalnew.rightNode.insert(element);

            }
            if(totalnew.rightNode.getHeight()==-1){
                totalnew.rightNode=newtree;

            }
            // COMPLETE
        } else if (element.compareTo(value) < 0) {
            if(totalnew.leftNode.getHeight()!=-1){

                totalnew.leftNode.insert(element);
            }
            if(totalnew.leftNode.getHeight()==-1){
                totalnew.leftNode=newtree;
            }
            // COMPLETE
        } else {
            return totalnew;
            // COMPLETE
        }
        */

        /*
        int balance=totalnew.getBalanceFactor();
        if(balance>1 && element.compareTo(totalnew.leftNode.value)<0){
            return rightRotate();
        }

        if(balance<-1 && element.compareTo(totalnew.rightNode.value)>0){
            return leftRotate();
        }

        if(balance>1 && element.compareTo(totalnew.leftNode.value)>0){
            totalnew.leftNode=((AVLTree<T>)totalnew.leftNode).leftRotate();
            return rightRotate();
        }

        if(balance<-1 && element.compareTo(totalnew.rightNode.value)<0){
            totalnew.rightNode=((AVLTree<T>)totalnew.rightNode).rightRotate();
            return leftRotate();
        }
        */


        //return totalnew; // Change to return something different
    }

    /**
     * Conducts a left rotation on the current node.
     *
     * @return the new 'current' or 'top' node after rotation.
     */
    public AVLTree<T> leftRotate() {
        /*
            TODO: Write and or complete this method so that you can conduct a left rotation on the current node.
            This can be quite difficult to get your head around. Try looking for visualisations
            of left rotate if you are confused.

            Note: if this is implemented correctly than the return MUST be an AVL tree. However, the
            rotation may move around EmptyAVL trees. So when moving trees, the type of the trees can
            be 'Tree<T>'. However, the return type should be of AVLTree<T>. To cast the return type into
            AVLTree<T> simply use: (AVLTree<T>).

            If you get an casting exception such as:
            'java.lang.ClassCastException: class AVLTree$EmptyAVL cannot be cast to class AVLTree
            (AVLTree$EmptyAVL and AVLTree are in unnamed module of loader 'app')'
            than something about your code is incorrect!
         */
        AVLTree<T> totalnew;
        totalnew=this;
        Tree<T> newParent = totalnew.rightNode;
        Tree<T> newRightOfCurrent = newParent.leftNode;

        //rotation
        newParent.leftNode=totalnew;
        totalnew.rightNode=newRightOfCurrent;

        //update heights



        // COMPLETE

        return (AVLTree<T>) newParent; // Change to return something different
    }

    /**
     * Conducts a right rotation on the current node.
     *
     * @return the new 'current' or 'top' node after rotation.
     */
    public AVLTree<T> rightRotate() {
        /*
            TODO: Write this method so that you can conduct a right rotation on the current node.
            This can be quite difficult to get your head around. Try looking for visualisations
            of right rotate if you are confused.

            Note: if this is implemented correctly than the return MUST be an AVL tree. However, the
            rotation may move around EmptyAVL trees. So when moving trees, the type of the trees can
            be 'Tree<T>'. However, the return type should be of AVLTree<T>. To cast the return type into
            AVLTree<T> simply use: (AVLTree<T>).

            If you get an casting exception such as:
            'java.lang.ClassCastException: class AVLTree$EmptyAVL cannot be cast to class AVLTree
            (AVLTree$EmptyAVL and AVLTree are in unnamed module of loader 'app')'
            than something about your code is incorrect!
         */
        AVLTree<T> totalnew;
        totalnew=this;
        Tree<T> newParent = totalnew.leftNode;
        Tree<T> newLeft=newParent.rightNode;

        newParent.rightNode=totalnew;
        totalnew.leftNode=newLeft;



        //update heights



        // COMPLETE

        return (AVLTree<T>) newParent;

         // Change to return something different
    }

    /**
     * Note that this is not within a file of its own... WHY?
     * The answer is: this is just a design decision. 'insert' here will return something specific
     * to the parent class inheriting Tree from BinarySearchTree. In this case an AVL tree.
     */
    public static class EmptyAVL<T extends Comparable<T>> extends EmptyTree<T> {
        @Override
        public Tree<T> insert(T element) {
            // The creation of a new Tree, hence, return tree.
            return new AVLTree<T>(element);
        }
    }

    public static void main(String[] args) {
        AVLTree<Integer> avl = new AVLTree<>(5);
        AVLTree<Integer> avl2 = new AVLTree<>(2);

        AVLTree<Integer> avl3 = new AVLTree<>(8);
        avl.leftNode=avl2;
        avl.rightNode=avl3;
        avl=avl.insert(9);
        avl=avl.insert(7);

        System.out.println(avl.display());
    }

}
