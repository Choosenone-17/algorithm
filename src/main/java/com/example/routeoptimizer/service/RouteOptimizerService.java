package com.example.routeoptimizer.service;
import com.example.routeoptimizer.dto.RouteResponse;
import com.example.routeoptimizer.model.Graph;

import java.util.*;

public class RouteOptimizerService {

    private DijkstraService dijkstraService = new DijkstraService();

    public RouteResponse optimizeRoute(Graph graph, String start, List<String> locations) {

        List<String> route = new ArrayList<>();
        Set<String> remaining = new HashSet<>(locations);
        List<List<String>> allPaths = new ArrayList<>();

        String current = start;
        route.add(current);

        double totalDistance = 0;

        while (!remaining.isEmpty()) {
            Map<String, PathResult> results = dijkstraService.shortestPath(graph, current);

            String nearest = null;
            double minDist = Double.MAX_VALUE;

            for (String loc : remaining) {
                if (results.get(loc).getDistance() < minDist) {
                    minDist = results.get(loc).getDistance();
                    nearest = loc;
                }
            }

            if (nearest == null) break;

            totalDistance += minDist;
            route.add(nearest);
            remaining.remove(nearest);
            current = nearest;
            allPaths.add(results.get(nearest).getPath());
        }

        return new RouteResponse(route, totalDistance, allPaths);
    }
}