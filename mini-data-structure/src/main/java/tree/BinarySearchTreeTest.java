package tree;


import java.util.Random;

public class BinarySearchTreeTest {

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
//        tree.insert(1);
//        tree.insert(2);
//        tree.insert(3);
//        tree.insert(4);
//        tree.insert(6);
//        tree.insert(7);
//        System.out.println(tree);

        for (int i = 0; i < 10; i++) {
            tree.insert(new Random().nextInt(100));
        }
        System.out.println(tree);
    }

}
