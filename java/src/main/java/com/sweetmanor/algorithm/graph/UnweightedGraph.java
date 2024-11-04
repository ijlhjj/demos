package com.sweetmanor.algorithm.graph;

import com.sweetmanor.algorithm.search.GraphSearch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * 无权图 —— 《算法精粹》
 *
 * @author ijlhjj
 * @version 1.0 2023-12-19
 */
public class UnweightedGraph<V> extends Graph<V, Edge> {
    private static final Logger logger = LogManager.getLogger();

    public UnweightedGraph(List<V> vertices) {
        super(vertices);
    }

    public void addEdge(Edge e) {
        edges.get(e.u).add(e);
        edges.get(e.v).add(e.reversed());
    }

    public void addEdge(int u, int v) {
        addEdge(new Edge(u, v));
    }

    public void addEdge(V u, V v) {
        addEdge(new Edge(indexOf(u), indexOf(v)));
    }

    public static void main(String[] args) {
        UnweightedGraph<String> cityGraph = new UnweightedGraph<>(List.of("Seattle", "San Francisco", "Los Angeles", "Riverside", "Phoenix", "Chicago", "Boston", "New York", "Atlanta", "Miami", "Dallas", "Houston", "Detroit", "Philadelphia", "Washington"));

        cityGraph.addEdge("Seattle", "Chicago");
        cityGraph.addEdge("Seattle", "San Francisco");
        cityGraph.addEdge("San Francisco", "Riverside");
        cityGraph.addEdge("San Francisco", "Los Angeles");
        cityGraph.addEdge("Los Angeles", "Riverside");

        cityGraph.addEdge("Los Angeles", "Phoenix");
        cityGraph.addEdge("Riverside", "Phoenix");
        cityGraph.addEdge("Riverside", "Chicago");
        cityGraph.addEdge("Phoenix", "Dallas");
        cityGraph.addEdge("Phoenix", "Houston");

        cityGraph.addEdge("Dallas", "Chicago");
        cityGraph.addEdge("Dallas", "Atlanta");
        cityGraph.addEdge("Dallas", "Houston");
        cityGraph.addEdge("Houston", "Atlanta");
        cityGraph.addEdge("Houston", "Miami");

        cityGraph.addEdge("Atlanta", "Chicago");
        cityGraph.addEdge("Atlanta", "Washington");
        cityGraph.addEdge("Atlanta", "Miami");
        cityGraph.addEdge("Miami", "Washington");
        cityGraph.addEdge("Chicago", "Detroit");

        cityGraph.addEdge("Detroit", "Boston");
        cityGraph.addEdge("Detroit", "Washington");
        cityGraph.addEdge("Detroit", "New York");
        cityGraph.addEdge("Boston", "New York");
        cityGraph.addEdge("New York", "Philadelphia");

        cityGraph.addEdge("Philadelphia", "Washington");

        logger.info(cityGraph);

        GraphSearch.Node<String> bfsResult = GraphSearch.bfs("Boston", v -> v.equals("Miami"), cityGraph::neighborsOf);

        if (bfsResult == null) {
            logger.info("No solution found using breadth-first search!");
        } else {
            List<String> path = GraphSearch.nodeToPath(bfsResult);
            logger.info("Path from Boston to Miami:");
            logger.info(path);
        }
    }

}
