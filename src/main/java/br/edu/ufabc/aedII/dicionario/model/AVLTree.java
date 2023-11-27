package br.edu.ufabc.aedII.dicionario.model;

import java.util.List;

class Node {
    String key;
    List<String> value;
    int height;
    Node left, right;

    Node(String k, List<String> v) {
        key = k;
        value = v;
        height = 1;
    }
    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }


}

public class AVLTree implements SearchStruct{
    private Node root;

    private int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    private int max(int a, int b) {
        return Math.max(a, b);
    }

    private Node rightRotate(Node right) {
        Node x = right.left;
        Node T2 = x.right;

        x.right = right;
        right.left = T2;

        right.height = max(height(right.left), height(right.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    private int getBalance(Node node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    private Node insert(Node node, String key, List<String> value) {

        if (node == null)
            return new Node(key, value);

        if (key.compareTo(node.key) < 0)
            node.left = insert(node.left, key, value);
        else if (key.compareTo(node.key) > 0)
            node.right = insert(node.right, key, value);
        else
            return node;

        node.height = 1 + max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1) {
            if (key.compareTo(node.left.key) < 0)
                return rightRotate(node);
            if (key.compareTo(node.left.key) > 0) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }

        if (balance < -1) {
            if (key.compareTo(node.right.key) > 0)
                return leftRotate(node);
            if (key.compareTo(node.right.key) < 0) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }

        return node;
    }

    public void insert(String key, List<String> value) {
        root = insert(root, key, value);
    }


    private List<String> search(Node node, String key) {
        if (node == null) {
            return null;
        }

        int comparison = key.compareTo(node.key);

        if (comparison < 0) {
            return search(node.left, key);
        }

        else if (comparison > 0) {
            return search(node.right, key);
        }

        else {
            return node.value;
        }
    }

    @Override
    public List<String> search(String key) {
        return search(root, key);
    }

    @Override
    public void clear() {
        root = clearRecursive(root);
    }

    private Node clearRecursive(Node node) {
        if (node == null) return null;


        node.setLeft(clearRecursive(node.getLeft()));
        node.setRight(clearRecursive(node.getRight()));

        return null;
    }

    private void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.println(node.key + ": " + node.value);
            inOrderTraversal(node.right);
        }
    }

    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

}
