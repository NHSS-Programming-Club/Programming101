package me.ankur.datastructures;

import java.util.ArrayList;
import java.util.List;

/**
 * User: bobacadodl
 * Date: 11/1/14
 * Time: 2:59 PM
 */
public class Node<T> {
    private T data;
    private Node<T> parent;
    private List<Node<T>> children;

    public Node(T data) {
        this.data = data;
        this.children = new ArrayList<Node<T>>();
    }

    public Node addChild(Node<T> node) {
        node.parent = this;
        children.add(node);
        return node;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getParent() {
        return parent;
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    public Node<T> findChild(T value) {
        for (Node<T> child : children)
            if (child.data == value)
                return child;
        return null;
    }
}