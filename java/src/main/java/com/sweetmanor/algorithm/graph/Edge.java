package com.sweetmanor.algorithm.graph;

public class Edge {

    public final int u; //起点
    public final int v; //终点

    public Edge(int u, int v) {
        this.u = u;
        this.v = v;
    }

    public Edge reversed() {
        return new Edge(v, u);
    }

    @Override
    public String toString() {
        return u + " -> " + v;
    }

}
