package com.example.routeoptimizer.dto;

import java.util.Map;
import java.util.List;

public class RouteRequest {

    private String start;
    private List<String> locations;
    private Map<String, double[]> coordinates;

    public String getStart() { return start; }
    public List<String> getLocations() { return locations; }
    public Map<String, double[]> getCoordinates() { return coordinates; }
}