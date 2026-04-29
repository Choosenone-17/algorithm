package com.example.routeoptimizer.service;

import com.example.routeoptimizer.model.Edge;
import com.example.routeoptimizer.model.Graph;
import com.example.routeoptimizer.model.Node;

import java.util.*;

public class DijkstraService {

    public Map<String, PathResult> shortestPath(Graph graph, String start) {

        Map<String, Double> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        Set<String> visited = new HashSet<>();

        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingDouble(p -> p.distance));

        for (Node node : graph.getAllNodes()) {
            distances.put(node.getId(), Double.MAX_VALUE);
        }

        distances.put(start, 0.0);
        pq.add(new Pair(start, 0.0));

        while (!pq.isEmpty()) {
            Pair current = pq.poll();

            if (visited.contains(current.nodeId)) continue;
            visited.add(current.nodeId);

            System.out.println("Processing: " + current.nodeId);

            Node currentNode = graph.getNode(current.nodeId);
            if (currentNode == null) continue;

            for (Edge edge : currentNode.getEdges()) {
                double newDist = distances.get(current.nodeId) + edge.getWeight();

                if (newDist < distances.get(edge.getTarget().getId())) {
                    distances.put(edge.getTarget().getId(), newDist);
                    previous.put(edge.getTarget().getId(), current.nodeId);
                    pq.add(new Pair(edge.getTarget().getId(), newDist));
                }
            }
        }

        // 🔥 Build final result with paths
        Map<String, PathResult> result = new HashMap<>();

        for (String nodeId : distances.keySet()) {
            List<String> path = buildPath(nodeId, previous);
            result.put(nodeId, new PathResult(distances.get(nodeId), path));
        }

        return result;
    }

    private List<String> buildPath(String target, Map<String, String> previous) {
        List<String> path = new ArrayList<>();

        while (target != null) {
            path.add(target);
            target = previous.get(target);
        }

        Collections.reverse(path);
        return path;
    }

    static class Pair {
        String nodeId;
        double distance;

        Pair(String nodeId, double distance) {
            this.nodeId = nodeId;
            this.distance = distance;
        }
    }
}