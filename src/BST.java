import java.util.Iterator;
import java.util.Stack;

public class BST<T extends Comparable<T>> implements Iterable<T> {
    private Node<T> root;

    // Constructor
    public BST() {
        root = null; // Initialize tree as empty
    }

    // Insert a value into the BST
    public void insert(T value) {
        root = insertRec(root, value);
    }

    // Recursive method for insertion
    private Node<T> insertRec(Node<T> root, T value) {
        if (root == null) {
            return new Node<>(value); // Create new node if root is null
        }
        if (value.compareTo(root.value) < 0) {
            root.left = insertRec(root.left, value); // Insert into left subtree
        } else if (value.compareTo(root.value) > 0) {
            root.right = insertRec(root.right, value); // Insert into right subtree
        }
        return root;
    }

    // Search for a value in the BST
    public T search(T value) {
        return searchRec(root, value);
    }

    private T searchRec(Node<T> root, T value) {
        if (root == null || root.value.compareTo(value) == 0) {
            return root != null ? root.value : null; // Return null if not found
        }
        if (value.compareTo(root.value) < 0) {
            return searchRec(root.left, value); // Search in left subtree
        } else {
            return searchRec(root.right, value); // Search in right subtree
        }
    }

    // Remove a value from the BST
    public void remove(T value) {
        root = removeRec(root, value);
    }

    private Node<T> removeRec(Node<T> root, T value) {
        if (root == null) return null;
        if (value.compareTo(root.value) < 0) {
            root.left = removeRec(root.left, value); // Traverse left subtree
        } else if (value.compareTo(root.value) > 0) {
            root.right = removeRec(root.right, value); // Traverse right subtree
        } else {
            // Node found, perform removal
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            root.value = minValue(root.right); // Find min value in right subtree
            root.right = removeRec(root.right, root.value); // Remove the min value
        }
        return root;
    }

    // Helper method to find the minimum value in a subtree
    private T minValue(Node<T> root) {
        T minValue = root.value;
        while (root.left != null) {
            root = root.left;
            minValue = root.value;
        }
        return minValue;
    }

    // In-order traversal (for printing)
    public void printInOrder() {
        for (T value : this) {
            System.out.println(value);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new BSTIterator<T>(root);
    }

    // Inner class for the BST iterator
    private class BSTIterator<T extends Comparable<T>> implements Iterator<T> {
        private Stack<Node<T>> stack = new Stack<>();

        public BSTIterator(Node<T> root) {
            pushLeft(root);
        }

        private void pushLeft(Node<T> node) {
            while (node != null) {
                stack.push(node);  // Push node onto the stack
                node = node.left;  // Move to the left child
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();  // Check if there are more nodes to visit
        }

        @Override
        public T next() {
            Node<T> node = stack.pop();  // Pop the node from the stack
            pushLeft(node.right);  // Push left children of the right subtree
            return node.value;  // Return the node value
        }
    }
}