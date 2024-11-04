package com.sweetmanor.algorithm.search;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;

/**
 * 图遍历算法 —— 《算法精粹》
 *
 * @author ijlhjj
 * @version 1.0 2023-12-12
 */
public class GraphSearch {

    /**
     * 深度优先搜索
     */
    public static <T> Node<T> dfs(T initial, Predicate<T> goalTest, Function<T, List<T>> successors) {
        //使用栈记录当前遍历路径
        Stack<Node<T>> frontier = new Stack<>();
        frontier.push(new Node<>(initial, null));

        //使用集合记录已遍历节点
        Set<T> explored = new HashSet<>();
        explored.add(initial);

        while (!frontier.isEmpty()) {
            Node<T> currentNode = frontier.pop();
            T currentState = currentNode.state;

            //到达终点
            if (goalTest.test(currentState))
                return currentNode;

            //深度优先遍历
            for (T child : successors.apply(currentState)) {
                if (!explored.contains(child)) {//未访问节点
                    explored.add(child);
                    frontier.push(new Node<>(child, currentNode));
                }
            }
        }

        return null; //未找到路径
    }

    /**
     * 广度优先搜索
     */
    public static <T> Node<T> bfs(T initial, Predicate<T> goalTest, Function<T, List<T>> successors) {
        //使用队列记录当前遍历路径
        Queue<Node<T>> frontier = new LinkedList<>();
        frontier.offer(new Node<>(initial, null));

        //使用集合记录已遍历节点
        Set<T> explored = new HashSet<>();
        explored.add(initial);

        while (!frontier.isEmpty()) {
            Node<T> currentNode = frontier.poll();
            T currentState = currentNode.state;

            //到达终点
            if (goalTest.test(currentState))
                return currentNode;

            //广度优先遍历
            for (T child : successors.apply(currentState)) {
                if (!explored.contains(child)) {//未访问节点
                    explored.add(child);
                    frontier.offer(new Node<>(child, currentNode));
                }
            }
        }

        return null; //未找到路径
    }

    /**
     * A* 搜索
     */
    public static <T> Node<T> aStar(T initial, Predicate<T> goalTest, Function<T, List<T>> successors, ToDoubleFunction<T> heuristic) {
        //使用优先队列记录当前遍历路径
        PriorityQueue<Node<T>> frontier = new PriorityQueue<>();
        frontier.offer(new Node<>(initial, null, 0.0, heuristic.applyAsDouble(initial)));

        //使用映射记录已遍历节点
        Map<T, Double> explored = new HashMap<>();
        explored.put(initial, 0.0);

        while (!frontier.isEmpty()) {
            Node<T> currentNode = frontier.poll();
            T currentState = currentNode.state;

            //到达终点
            if (goalTest.test(currentState))
                return currentNode;

            for (T child : successors.apply(currentState)) {
                double newCost = currentNode.cost + 1;
                //未访问节点或找到更低成本
                if (!(explored.containsKey(child) && explored.get(child) <= newCost)) {
                    explored.put(child, newCost);
                    frontier.offer(new Node<>(child, currentNode, newCost, heuristic.applyAsDouble(child)));
                }
            }
        }

        return null;
    }

    /**
     * 生成成功遍历路径
     */
    public static <T> List<T> nodeToPath(Node<T> node) {
        List<T> path = new ArrayList<>();
        path.add(node.state);

        while (node.parent != null) {
            node = node.parent;
            path.add(0, node.state);
        }

        return path;
    }

    public static class Node<T> implements Comparable<Node<T>> {
        final T state;
        Node<T> parent;
        double cost;
        double heuristic;

        public Node(T state, Node<T> parent) {
            this.state = state;
            this.parent = parent;
        }

        public Node(T state, Node<T> parent, double cost, double heuristic) {
            this.state = state;
            this.parent = parent;
            this.cost = cost;
            this.heuristic = heuristic;
        }

        @Override
        public int compareTo(Node<T> other) {
            Double mine = cost + heuristic;
            Double theirs = other.cost + other.heuristic;
            return mine.compareTo(theirs);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Node<?> node = (Node<?>) o;
            return Double.compare(cost, node.cost) == 0 && Double.compare(heuristic, node.heuristic) == 0 && Objects.equals(state, node.state) && Objects.equals(parent, node.parent);
        }

        @Override
        public int hashCode() {
            return Objects.hash(state, parent, cost, heuristic);
        }
    }

}
