package me.ankur.datastructures;

/**
 * User: bobacadodl
 * Date: 11/1/14
 * Time: 4:34 PM
 */
public class ExpressionTree {
    Node<String> root;

    public ExpressionTree() {
        root = new Node<String>(null);
    }

    public Node<String> getRoot() {
        return root;
    }

}
