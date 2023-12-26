/*
 * All Rights Reserved.
 *
 */
package tree;


/**
 * BinarySearchTree.
 *
 * @author Carl, 2023-12-26 17:08
 */
public class BinarySearchTree extends AbstractTree {

    public Node insert(int e) {
        if (null == root) {
            Node node = new Node();
            node.setValue(e);
            root = node;
            return node;
        }
        //搜索Node节点
        Node parent = root;
        Node search = root;
        while (search != null && search.value != null) {
            parent = search;
            if (e < search.value) {
                search = search.left;
            } else {
                search = search.right;
            }
        }
        // 插入元素
        Node newNode = new Node(e, parent, null, null);
        if (parent.value != null && parent.value > newNode.value) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        size++;
        return newNode;
    }

    public Node search(int e) {
        Node search = root;
        while (search != null && search.value != null) {
            if (e < search.value) {
                search = search.left;
            } else {
                search = search.right;
            }
        }
        return search;
    }

    //node节点的value不重复
    public Node deleteNode(int e) {
        Node node = search(e);
        if (node == null) {
            throw new RuntimeException("node value is error");
        }
        //删除节点时候,需要将后面的树节点进行移动位置
        return null;
    }


}
