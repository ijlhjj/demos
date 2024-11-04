package com.sweetmanor.algorithm.graph;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.function.IntConsumer;

/**
 * 加权图 —— 《算法精粹》
 *
 * @author ijlhjj
 * @version 1.0 2023-12-19
 */
public class WeightedGraph<V> extends Graph<V, WeightedEdge> {
    private static final Logger logger = LogManager.getLogger();

    public WeightedGraph(List<V> vertices) {
        super(vertices);
    }

    public void addEdge(WeightedEdge e) {
        edges.get(e.u).add(e);
        edges.get(e.v).add(e.reversed());
    }

    public void addEdge(int u, int v, double weight) {
        addEdge(new WeightedEdge(u, v, weight));
    }

    public void addEdge(V u, V v, double weight) {
        addEdge(indexOf(u), indexOf(v), weight);
    }

    public double totalWeight(List<WeightedEdge> path) {
        return path.stream().mapToDouble(we -> we.weight).sum();
    }

    public void printWeightedPath(List<WeightedEdge> path) {
        for (WeightedEdge e : path)
            logger.info("{} {} -> {}", vertexAt(e.u), e.weight, vertexAt(e.v));
        logger.info("总计权重：{}", totalWeight(path));
    }

    /**
     * Prim算法：属于贪心算法
     */
    public List<WeightedEdge> mst(int start) {
        List<WeightedEdge> path = new LinkedList<>(); //最终路径

        //校验入参，超出顶点列表范围时返回空路径
        if (start < 0 || start > getVertexCount() - 1)
            return path;

        //使用优先队列查找最小权重的边，使用数组记录已加入连通图顶点（等同于图遍历中的Set）
        PriorityQueue<WeightedEdge> priorityQueue = new PriorityQueue<>();
        boolean[] visited = new boolean[getVertexCount()];

        //定义内部函数，方便后面多处引用
        IntConsumer visit = index -> {
            visited[index] = true; //标记顶点已访问
            for (WeightedEdge edge : edgesOf(index)) {
                //遍历邻近边，未访问的加入优先队列
                if (!visited[edge.v])
                    priorityQueue.offer(edge);
            }
        };

        visit.accept(start); //处理起始顶点
        //优先队列不为空时继续处理
        while (!priorityQueue.isEmpty()) {
            WeightedEdge edge = priorityQueue.poll();
            //对未访问的邻近边进行处理
            if (!visited[edge.v]) {
                path.add(edge);
                visit.accept(edge.v);
            }
        }

        return path;
    }

    /**
     * Dijkstra 算法
     */
    public DijkstraResult dijkstra(V root) {
        int first = indexOf(root); //获取索引

        int vertexCount = getVertexCount();

        //距离数组，起点为0
        double[] distances = new double[vertexCount];
        distances[first] = 0;
        //已访问标记
        boolean[] visited = new boolean[vertexCount];
        visited[first] = true;

        //路径映射
        Map<Integer, WeightedEdge> pathMap = new HashMap<>();
        //优先队列存放待遍历顶点
        PriorityQueue<DijkstraNode> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(new DijkstraNode(first, 0));

        while (!priorityQueue.isEmpty()) {
            int u = priorityQueue.poll().vertex;
            double distU = distances[u];
            //遍历邻接边
            for (WeightedEdge e : edgesOf(u)) {
                double pathWeight = e.weight + distU; //邻接边距离
                double distV = distances[e.v];
                //邻接边未访问或发现更短距离
                if (!visited[e.v] || distV > pathWeight) {
                    visited[e.v] = true;
                    distances[e.v] = pathWeight; //更新距离
                    pathMap.put(e.v, e);
                    priorityQueue.offer(new DijkstraNode(e.v, pathWeight));
                }
            }
        }

        return new DijkstraResult(distances, pathMap);
    }

    public Map<V, Double> distanceArrayToDistanceMap(double[] distances) {
        Map<V, Double> distanceMap = new HashMap<>();
        for (int i = 0; i < distances.length; i++) {
            distanceMap.put(vertexAt(i), distances[i]);
        }
        return distanceMap;
    }

    public static List<WeightedEdge> pathMapToPath(int start, int end, Map<Integer, WeightedEdge> pathMap) {
        if (pathMap.isEmpty())
            return List.of();

        //逆序添加路径
        List<WeightedEdge> path = new LinkedList<>();
        WeightedEdge edge = pathMap.get(end);
        path.add(edge);

        while (edge.u != start) {
            edge = pathMap.get(edge.u);
            path.add(edge);
        }

        Collections.reverse(path);

        return path;
    }

    public static void main(String[] args) {
        WeightedGraph<String> cityGraph = new WeightedGraph<>(List.of("Seattle", "San Francisco", "Los Angeles", "Riverside", "Phoenix", "Chicago", "Boston", "New York", "Atlanta", "Miami", "Dallas", "Houston", "Detroit", "Philadelphia", "Washington"));

        cityGraph.addEdge("Seattle", "Chicago", 1737);
        cityGraph.addEdge("Seattle", "San Francisco", 678);
        cityGraph.addEdge("San Francisco", "Riverside", 386);
        cityGraph.addEdge("San Francisco", "Los Angeles", 348);
        cityGraph.addEdge("Los Angeles", "Riverside", 50);

        cityGraph.addEdge("Los Angeles", "Phoenix", 357);
        cityGraph.addEdge("Riverside", "Phoenix", 307);
        cityGraph.addEdge("Riverside", "Chicago", 1704);
        cityGraph.addEdge("Phoenix", "Dallas", 887);
        cityGraph.addEdge("Phoenix", "Houston", 1015);

        cityGraph.addEdge("Dallas", "Chicago", 805);
        cityGraph.addEdge("Dallas", "Atlanta", 721);
        cityGraph.addEdge("Dallas", "Houston", 225);
        cityGraph.addEdge("Houston", "Atlanta", 702);
        cityGraph.addEdge("Houston", "Miami", 968);

        cityGraph.addEdge("Atlanta", "Chicago", 588);
        cityGraph.addEdge("Atlanta", "Washington", 543);
        cityGraph.addEdge("Atlanta", "Miami", 604);
        cityGraph.addEdge("Miami", "Washington", 923);
        cityGraph.addEdge("Chicago", "Detroit", 238);

        cityGraph.addEdge("Detroit", "Boston", 613);
        cityGraph.addEdge("Detroit", "Washington", 396);
        cityGraph.addEdge("Detroit", "New York", 482);
        cityGraph.addEdge("Boston", "New York", 190);
        cityGraph.addEdge("New York", "Philadelphia", 81);

        cityGraph.addEdge("Philadelphia", "Washington", 123);

        logger.info(cityGraph);

        List<WeightedEdge> mst = cityGraph.mst(0);
        cityGraph.printWeightedPath(mst);

        logger.info("\n");

        DijkstraResult dijkstraResult = cityGraph.dijkstra("Los Angeles");
        Map<String, Double> nameDistance = cityGraph.distanceArrayToDistanceMap(dijkstraResult.distances);
        logger.info("Distances from Los Angeles:");
        nameDistance.forEach((name, distance) -> logger.info("{} : {}", name, distance));

        logger.info("\n");

        logger.info("Shortest path from Los Angeles to Boston:");
        List<WeightedEdge> path = pathMapToPath(cityGraph.indexOf("Los Angeles"), cityGraph.indexOf("Boston"), dijkstraResult.pathMap);
        cityGraph.printWeightedPath(path);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getVertexCount(); i++) {
            sb.append(vertexAt(i));
            sb.append(" -> ");
            sb.append(Arrays.toString(edgesOf(i).stream().map(we -> "(" + vertexAt(we.v) + ", " + we.weight + ")").toArray()));
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    public record DijkstraNode(int vertex, double distance) implements Comparable<DijkstraNode> {
        @Override
        public int compareTo(DijkstraNode other) {
            Double mine = distance;
            Double theirs = other.distance;
            return mine.compareTo(theirs);
        }
    }

    public record DijkstraResult(double[] distances, Map<Integer, WeightedEdge> pathMap) {
    }

}
