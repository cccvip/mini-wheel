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
        elementList.add(e);
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
        elementList.remove(e);
        return delete(node);
    }

    /**
     * 第一种情况 左子树为空,则将右子树挂载到当前根节点
     * 第二种情况 右子树为空,则将左子树挂载到当前被删除的根节点
     * 第三种情况 没有子树节点,则需要找到兄弟节点,将兄弟节点的树进行一个变化,进行树的重构
     * 详细解释一下
     * @param deleteNode
     * @return
     */
    public Node delete(Node deleteNode){
        Node result=null;
        if(deleteNode.left==null){
            result = transplant(deleteNode, deleteNode.right);
        }else if(deleteNode.right==null){
            result = transplant(deleteNode, deleteNode.left);
        }else {
            //找到兄弟树的左孩子节点,左孩子小于根节点
            Node miniNode = getMiniNode(deleteNode.right);
            if (miniNode.parent != deleteNode) {
                transplant(miniNode, miniNode.right);
                miniNode.right = deleteNode.right;
                miniNode.right.parent = miniNode;
            }
            transplant(deleteNode, miniNode);
            miniNode.left = deleteNode.left;
            miniNode.left.parent = miniNode;
            result = miniNode;
        }
        return result;
    }

    protected Node getMiniNode(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    protected Node transplant(Node delNode, Node addNode) {
        if (delNode.parent == null) {
            this.root = addNode;
        } else if (delNode.parent.left == delNode) {
            delNode.parent.left = addNode;
        } else {
            delNode.parent.right = addNode;
        }
        // 设置父节点
        if (null != addNode) {
            addNode.parent = delNode.parent;
        }
        return addNode;
    }

    @Override
    public String toString() {
        String str = elementList.toString();
        str = str.substring(str.indexOf("[") + 1, str.lastIndexOf("]")).replace(" ", "");
        int nullIdx = str.indexOf("null");
        if (nullIdx > 0) {
            str = str.substring(0, str.indexOf("null"));
            str = str.substring(0, str.lastIndexOf(","));
        }
        System.out.println(this.getClass().getSimpleName() + "，输入节点：" + str + "\r\n");
        return printSubTree(root);
    }

}
