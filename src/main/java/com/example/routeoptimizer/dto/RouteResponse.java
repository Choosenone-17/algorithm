package com.example.routeoptimizer.dto;
import java.util.List;

public class RouteResponse {
    private List<String> route;
    private double totalDistance;
    private List<List<String>> paths;

    public RouteResponse(List<String> route, double totalDistance, List<List<String>> paths) {
        this.route = route;
        this.totalDistance = totalDistance;
        this.paths = paths;
    }

    public List<String> getRoute() { return route; }
    public double getTotalDistance() { return totalDistance; }
    public List<List<String>> getPaths() { return paths; }
}