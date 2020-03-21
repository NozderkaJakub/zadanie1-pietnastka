package pl.sise;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
    T label;
    List<Node<T>> children;
    Node<T> parent;

    public Node(T label, Node<T> parent) {
        this.label = label;
        this.parent = parent;
        children = new ArrayList<Node<T>>();
    }

    public void addChild(T childLabel) {
        children.add(new Node<T>(childLabel, this));
    }

    public T getLabel() {
        return this.label;
    }

    public boolean isRoot() {
        if(parent == null) {
            return true;
        }
        return false;
    }

    public Node<T> getParent() {
        return this.parent;
    }

    public List<Node<T>> getChildren() {
        return this.children;
    }

    public boolean hasChildren() {
        if(children.size() > 0) {
            return true;
        }
        return false;
    }
}
