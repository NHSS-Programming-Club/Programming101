package me.ankur.datastructures;

import java.util.*;

/**
 * User: bobacadodl
 * Date: 11/1/14
 * Time: 2:57 PM
 */
public class Trie {
    private Node<Character> root;

    public Trie() {
        root = new Node<Character>(null);
    }


    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.addString("jeffery");
        trie.addString("james");
        trie.addString("jonathan");
        trie.addString("jack");
        trie.addString("jackie");
        trie.addString("jayquelin");
        trie.addString("jelly");
        trie.addString("bob");

        trie.sort();


        for (String s : trie.traverse())
            System.out.println(s);
    }

    public void addString(String s) {
        Node<Character> currentNode = root;
        for (char c : s.toCharArray()) {
            //get the next character in the trie
            Node<Character> child = currentNode.findChild(c);

            //if it doesnt exist, add a new node into the trie
            if (child == null) {
                Node<Character> newChild = new Node<Character>(c);
                currentNode.addChild(newChild);
                currentNode = newChild;
            }
            //if it does, continue traversing the trie
            else {
                currentNode = child;
            }
        }
    }

    public String getString(int[] pos) {
        Node<Character> currentNode = root;
        StringBuilder ret = new StringBuilder();
        for (int i : pos) {
            currentNode = currentNode.getChildren().get(i);
            ret.append(currentNode.getData());
        }
        return ret.toString();
    }

    public List<String> traverse() {
        return traverse(root);
    }

    public List<String> traverse(Node<Character> node) {
        if (node.getChildren().size() == 0) {
            return new ArrayList<String>(Arrays.asList(new String[]{node.getData().toString()}));
        } else if (node.getData() != null) {
            List<String> strings = new ArrayList<String>();
            for (Node<Character> child : node.getChildren()) {
                for (String s : traverse(child)) {
                    strings.add(node.getData() + s);
                }
            }
            return strings;
        } else {
            List<String> strings = new ArrayList<String>();
            for (Node<Character> child : node.getChildren()) {
                strings.addAll(traverse(child));
            }
            return strings;
        }
    }

    public void sort() {
        sort(root);
    }

    //recursively sort each node alphabetically
    private void sort(Node<Character> node) {
        if (node.getChildren().size() > 1) {
            //useful method to sort alphabetically
            Collections.sort(node.getChildren(), new Comparator<Node<Character>>() {
                @Override
                public int compare(Node<Character> n1, Node<Character> n2) {
                    //returns
                    return n1.getData().compareTo(n2.getData());
                }
            });
        }
        for (Node<Character> child : node.getChildren()) {
            sort(child);
        }
    }
}
