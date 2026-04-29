package com.example.routeoptimizer.controller;
import com.example.routeoptimizer.dto.RouteRequest;
import com.example.routeoptimizer.dto.RouteResponse;
import com.example.routeoptimizer.model.Graph;
import com.example.routeoptimizer.service.RouteOptimizerService;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
public class RouteController {

    private RouteOptimizerService optimizer = new RouteOptimizerService();

    @PostMapping("/optimize")
    public RouteResponse optimize(@RequestBody RouteRequest request) {

        Graph graph = buildGraph(request.getCoordinates());

        return optimizer.optimizeRoute(
                graph,
                request.getStart(),
                request.getLocations()
        );
    }
    private Graph buildGraph(Map<String, double[]> coords) {

        Graph g = new Graph();

        // Add nodes
        for (String node : coords.keySet()) {
            g.addNode(node);
        }

        // Connect all nodes (complete graph)
        for (String from : coords.keySet()) {
            for (String to : coords.keySet()) {

                if (!from.equals(to)) {
                    double distance = haversine(coords.get(from), coords.get(to));
                    g.addEdge(from, to, distance);
                }
            }
        }

        return g;
    }

    private double haversine(double[] c1, double[] c2) {

        double lat1 = c1[0];
        double lon1 = c1[1];
        double lat2 = c2[0];
        double lon2 = c2[1];

        double R = 6371; // Earth radius (km)

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) *
                        Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon/2) * Math.sin(dLon/2); //to calculate radial distance between two points on earth

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return R * c;
    }

    private Graph sampleGraph() {
        Graph g = new Graph();

        // Add ALL nodes first
        g.addNode("A");
        g.addNode("B");
        g.addNode("C");
        g.addNode("D");

        // Then add edges
        g.addEdge("A", "B", 5);
        g.addEdge("B", "A", 5);

        g.addEdge("A", "C", 10);
        g.addEdge("C", "A", 10);

        g.addEdge("B", "D", 3);
        g.addEdge("D", "B", 3);

        g.addEdge("C", "D", 1);
        g.addEdge("D", "C", 1);

        return g;
    }
}