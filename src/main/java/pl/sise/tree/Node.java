package main.java.pl.sise.tree;

import java.util.ArrayList;

public class Node<T> {
    T label;
    ArrayList<Node<T>> children;
    Node<T> parent;

    public Node() {

    }

    public Node(T label, Node<T> parent) {
        this.label = label;
        this.parent = parent;
    }

    public void addChild(T childLabel) {
        children.add(new Node<T>(childLabel, this));
    }

    public T getLabel() {
        return this.label;
    }

    public Node<T> getParent() {
        return this.parent;
    }

    public ArrayList<Node<T>> getChildren() {
        return this.children;
    }
}
