package com.sweetmanor.algorithm.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 图 —— 《算法精粹》
 *
 * @author ijlhjj
 * @version 1.0 2023-12-19
 */
public abstract class Graph<V, E extends Edge> {

    //顶点列表的下标和边列表的下标一一对应，即顶点下标 1 的边的下标也是 1
    private final List<V> vertices = new ArrayList<>();
    protected List<List<E>> edges = new ArrayList<>();

    protected Graph() {
    }

    protected Graph(List<V> vertices) {
        //不直接使用入参的列表，避免后续外部变更造成混乱
        this.vertices.addAll(vertices);

        //对列表中的每个顶点添加一个空的边列表
        vertices.forEach(v -> edges.add(new ArrayList<>()));
    }

    public int getVertexCount() {
        return vertices.size();
    }

    public int getEdgeCount() {
        return edges.stream().mapToInt(List::size).sum();
    }

    public int addVertex(V v) {
        vertices.add(v);
        edges.add(new ArrayList<>());
        return getVertexCount() - 1;
    }

    public V vertexAt(int index) {
        return vertices.get(index);
    }

    public int indexOf(V v) {
        return vertices.indexOf(v);
    }

    public List<V> neighborsOf(int index) {
        return edges.get(index).stream().map(e -> vertexAt(e.v)).toList();
    }

    public List<V> neighborsOf(V v) {
        return neighborsOf(indexOf(v));
    }

    public List<E> edgesOf(int index) {
        return edges.get(index);
    }

    public List<E> edgesOf(V v) {
        return edgesOf(indexOf(v));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getVertexCount(); i++) {
            sb.append(vertexAt(i));
            sb.append(" -> ");
            sb.append(Arrays.toString(neighborsOf(i).toArray()));
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

}
