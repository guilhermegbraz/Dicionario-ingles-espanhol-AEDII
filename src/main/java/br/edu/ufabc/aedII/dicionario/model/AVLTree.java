package br.edu.ufabc.aedII.dicionario.model;

import java.util.List;

public class AVLTree implements SearchStruct{
    private NodeDictionary root;

    private int height(NodeDictionary nodeDictionary) {
        return (nodeDictionary == null) ? 0 : nodeDictionary.height;
    }

    private int max(int a, int b) {
        return Math.max(a, b);
    }

    private NodeDictionary rightRotate(NodeDictionary right) {
        NodeDictionary x = right.left;
        NodeDictionary T2 = x.right;

        x.right = right;
        right.left = T2;

        right.height = max(height(right.left), height(right.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private NodeDictionary leftRotate(NodeDictionary x) {
        NodeDictionary y = x.right;
        NodeDictionary T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    private int getBalance(NodeDictionary nodeDictionary) {
        return (nodeDictionary == null) ? 0 : height(nodeDictionary.left) - height(nodeDictionary.right);
    }

    private NodeDictionary insert(NodeDictionary nodeDictionary, String key, List<String> value) {

        if (nodeDictionary == null)
            return new NodeDictionary(key, value);

        if (key.compareTo(nodeDictionary.key) < 0)
            nodeDictionary.left = insert(nodeDictionary.left, key, value);
        else if (key.compareTo(nodeDictionary.key) > 0)
            nodeDictionary.right = insert(nodeDictionary.right, key, value);
        else
            return nodeDictionary;

        nodeDictionary.height = 1 + max(height(nodeDictionary.left), height(nodeDictionary.right));

        int balance = getBalance(nodeDictionary);

        if (balance > 1) {
            if (key.compareTo(nodeDictionary.left.key) < 0)
                return rightRotate(nodeDictionary);
            if (key.compareTo(nodeDictionary.left.key) > 0) {
                nodeDictionary.left = leftRotate(nodeDictionary.left);
                return rightRotate(nodeDictionary);
            }
        }

        if (balance < -1) {
            if (key.compareTo(nodeDictionary.right.key) > 0)
                return leftRotate(nodeDictionary);
            if (key.compareTo(nodeDictionary.right.key) < 0) {
                nodeDictionary.right = rightRotate(nodeDictionary.right);
                return leftRotate(nodeDictionary);
            }
        }

        return nodeDictionary;
    }

    public void insert(String key, List<String> value) {
        root = insert(root, key, value);
    }


    private List<String> search(NodeDictionary nodeDictionary, String key) {
        if (nodeDictionary == null) {
            return null;
        }

        int comparison = key.compareTo(nodeDictionary.key);

        if (comparison < 0) {
            return search(nodeDictionary.left, key);
        }

        else if (comparison > 0) {
            return search(nodeDictionary.right, key);
        }

        else {
            return nodeDictionary.value;
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

    private NodeDictionary clearRecursive(NodeDictionary nodeDictionary) {
        if (nodeDictionary == null) return null;

        nodeDictionary.setLeft(clearRecursive(nodeDictionary.getLeft()));
        nodeDictionary.setRight(clearRecursive(nodeDictionary.getRight()));

        return null;
    }

    private void inOrderTraversal(NodeDictionary nodeDictionary) {
        if (nodeDictionary != null) {
            inOrderTraversal(nodeDictionary.left);
            System.out.println(nodeDictionary.key + ": " + nodeDictionary.value);
            inOrderTraversal(nodeDictionary.right);
        }
    }

    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

}
