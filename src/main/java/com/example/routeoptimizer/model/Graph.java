package com.example.routeoptimizer.model;
import java.util.*;

public class Graph {
    private Map<String, Node> nodes = new HashMap<>();

    public void addNode(String id) {
        nodes.putIfAbsent(id, new Node(id));
    }

    public void addEdge(String from, String to, double weight) {
        nodes.get(from).addEdge(new Edge(nodes.get(to), weight));
    }

    public Node getNode(String id) {
        return nodes.get(id);
    }

    public Collection<Node> getAllNodes() {
        return nodes.values();
    }
}