public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
    T value; //Value stored
    Node<T> left, right; //L and R references

    //Constructor for value
    public Node(T value) {
        this.value = value;
        left = right = null; //Initialize as null
    }

    //Comparing two nodes on value
    @Override
    public int compareTo(Node<T> other) {
        return this.value.compareTo(other.value); //comparing values
    }
}