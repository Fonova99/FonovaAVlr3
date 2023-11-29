public class RedBlackTree {
    private Node root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        int data;
        Node left, right;
        boolean color;

        Node(int data) {
            this.data = data;
            this.color = RED;
        }
    }

    private boolean isRed(Node node) {
        if (node == null) return false;
        return node.color == RED;
    }

    private Node rotateLeft(Node node) {
        Node rightChild = node.right;
        node.right = rightChild.left;
        rightChild.left = node;
        rightChild.color = node.color;
        node.color = RED;
        return rightChild;
    }

    private Node rotateRight(Node node) {
        Node leftChild = node.left;
        node.left = leftChild.right;
        leftChild.right = node;
        leftChild.color = node.color;
        node.color = RED;
        return leftChild;
    }

    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    public void insert(int data) {
        root = insertRec(root, data);
        root.color = BLACK;
    }
    private Node insertRec(Node node, int data) {
        if (node == null) return new Node(data);

        if (data < node.data) node.left = insertRec(node.left, data);
        else if (data > node.data) node.right = insertRec(node.right, data);
        else node.data = data;

        if (isRed(node.right) && !isRed(node.left)) node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right)) flipColors(node);

        return node;
    }

    public boolean search(int data) {
        return searchRec(root, data);
    }
    private boolean searchRec(Node node, int data) {
        if (node == null) return false;
        if (data < node.data) return searchRec(node.left, data);
        else if (data > node.data) return searchRec(node.right, data);
        else return true;
    }

    public void delete(int data) {
        root = deleteRec(root, data);
        root.color = BLACK;
    }
    private Node deleteRec(Node node, int data) {
        if (data < node.data) {
            if (!isRed(node.left) && !isRed(node.left.left)) node = moveRedLeft(node);
            node.left = deleteRec(node.left, data);
        } else {
            if (isRed(node.left)) node = rotateRight(node);
            if (data == node.data && (node.right == null)) return null;
            if (!isRed(node.right) && !isRed(node.right.left)) node = moveRedRight(node);
            if (data == node.data) {
                Node min = min(node.right);
                node.data = min.data;
                node.right = deleteMin(node.right);
            } else node.right = deleteRec(node.right, data);
        }
        return balance(node);
    }

    private Node balance(Node node) {
        if (isRed(node.right)) node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right)) flipColors(node);
        return node;
    }

    private Node moveRedLeft(Node node) {
        flipColors(node);
        if (isRed(node.right.left)) {
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
            flipColors(node);
        }
        return node;
    }

    private Node moveRedRight(Node node) {
        flipColors(node);
        if (isRed(node.left.left)) {
            node = rotateRight(node);
            flipColors(node);
        }
        return node;
    }

    private Node min(Node node) {
        if (node.left == null) return node;
        else return min(node.left);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) return null;
        if (!isRed(node.left) && !isRed(node.left.left)) node = moveRedLeft(node);
        node.left = deleteMin(node.left);
        return balance(node);
    }

    public int getHeight() {
        return getHeight(root);
    }
    private int getHeight(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }
}