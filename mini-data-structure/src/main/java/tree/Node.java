/*
 * All Rights Reserved.
 *
 */
package tree;


/**
 * Node.
 *
 * @author Carl, 2023-12-26 16:54
 */
public class Node {
    Integer value;
    Node parent;
    Node left;
    Node right;

    public Node() {
        this.value = 0;
    }

    public Node(Integer value, Node parent, Node left, Node right) {
        this.value = value;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
