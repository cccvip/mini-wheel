/*
 * All Rights Reserved.
 *
 */
package tree;


/**
 * AbstractTree.
 *
 * @author Carl, 2023-12-26 16:58
 */
public abstract class AbstractTree {

    public Node root;

    public int size;

    protected String printSubTree(Node node) {
        StringBuilder tree = new StringBuilder();
        if (node.right != null) {
            printTree(node.right, true, "", tree);
        }
        printNodeValue(node, tree);
        if (node.left != null) {
            printTree(node.left, false, "", tree);
        }
        return tree.toString();
    }

    private void printTree(Node node, boolean isRight, String indent, StringBuilder tree) {
        if (node.right != null) {
            printTree(node.right, true, indent + (isRight ? "        " : " |      "), tree);
        }
        tree.append(indent);
        if (isRight) {
            tree.append(" /");
        } else {
            tree.append(" \\");
        }
        tree.append("----- ");
        printNodeValue(node, tree);
        if (node.left != null) {
            printTree(node.left, false, indent + (isRight ? " |      " : "        "), tree);
        }
    }

    private void printNodeValue(Node node, StringBuilder tree) {
        if (null == node.value) {
            tree.append("<NIL>");
        } else {
            tree.append(node.value);
//            if (root.clazz.equals(AVLTree.class)) {
//                tree.append("(").append(node.height).append(")");
//            } else if (root.clazz.equals(RedBlackTree.class)) {
//                tree.append("(").append(node.color == Node.Color.BLACK ? "黑" : "红").append(")");
//            }
        }
        tree.append("\r\n");
    }


}
