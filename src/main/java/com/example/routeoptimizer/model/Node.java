package com.example.routeoptimizer.model;
import java.util.*;

public class Node {
    private String id;
    private List<Edge> edges = new ArrayList<>();

    public Node(String id) {
        this.id = id;
    }

    public String getId() { return id; }
    public List<Edge> getEdges() { return edges; }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }
}