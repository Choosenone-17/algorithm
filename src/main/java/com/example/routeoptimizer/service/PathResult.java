package com.example.routeoptimizer.service;

import java.util.*;

public class PathResult {
    private double distance;
    private List<String> path;

    public PathResult(double distance, List<String> path) {
        this.distance = distance;
        this.path = path;
    }

    public double getDistance() { return distance; }
    public List<String> getPath() { return path; }
}