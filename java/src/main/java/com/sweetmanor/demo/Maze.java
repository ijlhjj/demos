package com.sweetmanor.demo;

import com.sweetmanor.algorithm.search.GraphSearch;
import com.sweetmanor.algorithm.search.GraphSearch.Node;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 迷宫求解 —— 《算法精粹》
 * <p>
 * 迷宫是随机生成的，所以不是每个迷宫都会有解
 *
 * @author ijlhjj
 * @version 1.0 2023-12-11
 */
public class Maze {
    private static final Logger logger = LogManager.getLogger();

    private final int rows;
    private final int columns;
    private final MazeLocation start;
    private final MazeLocation goal;
    private final Cell[][] grid;  //迷宫网格

    public Maze() {
        this(10, 10, new MazeLocation(0, 0), new MazeLocation(9, 9), 0.2);
    }

    public Maze(int rows, int columns, MazeLocation start, MazeLocation goal, double sparseness) {
        this.rows = rows;
        this.columns = columns;
        this.start = start;
        this.goal = goal;

        //填充迷宫
        grid = new Cell[rows][columns];
        for (Cell[] row : grid)
            Arrays.fill(row, Cell.EMPTY);

        randomlyFill(sparseness); //随机添加障碍

        grid[start.row][start.column] = Cell.START;
        grid[goal.row][goal.column] = Cell.GOAL;
    }

    /**
     * 随机填充障碍
     */
    private void randomlyFill(double sparseness) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++)
                if (Math.random() < sparseness)
                    grid[i][j] = Cell.BLOCKED;
        }
    }

    /**
     * 测试是否到达终点
     */
    public boolean goalTest(MazeLocation ml) {
        return goal.equals(ml);
    }

    /**
     * 查找可移动位置
     */
    public List<MazeLocation> successors(MazeLocation ml) {
        List<MazeLocation> locations = new ArrayList<>();

        if (ml.row + 1 < rows && grid[ml.row + 1][ml.column] != Cell.BLOCKED)
            locations.add(new MazeLocation(ml.row + 1, ml.column));

        if (ml.row - 1 >= 0 && grid[ml.row - 1][ml.column] != Cell.BLOCKED)
            locations.add(new MazeLocation(ml.row - 1, ml.column));

        if (ml.column + 1 < columns && grid[ml.row][ml.column + 1] != Cell.BLOCKED)
            locations.add(new MazeLocation(ml.row, ml.column + 1));

        if (ml.column - 1 >= 0 && grid[ml.row][ml.column - 1] != Cell.BLOCKED)
            locations.add(new MazeLocation(ml.row, ml.column - 1));

        return locations;
    }

    /**
     * 路径标注
     */
    public void mark(List<MazeLocation> path) {
        for (MazeLocation ml : path)
            grid[ml.row][ml.column] = Cell.PATH;

        grid[start.row][start.column] = Cell.START;
        grid[goal.row][goal.column] = Cell.GOAL;
    }

    /**
     * 网格清空
     */
    public void clear(List<MazeLocation> path) {
        for (MazeLocation ml : path)
            grid[ml.row][ml.column] = Cell.EMPTY;

        grid[start.row][start.column] = Cell.START;
        grid[goal.row][goal.column] = Cell.GOAL;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Cell[] row : grid) {
            for (Cell cell : row)
                sb.append(cell.toString()).append(" ");
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    /**
     * 欧氏距离
     */
    public double euclideanDistance(MazeLocation ml) {
        int xdist = ml.column - goal.column;
        int ydist = ml.row - goal.row;
        return Math.sqrt(((double) xdist * xdist) + ((double) ydist * ydist));
    }

    /**
     * 曼哈顿距离
     */
    public double manhattanDistance(MazeLocation ml) {
        int xdist = Math.abs(ml.column - goal.column);
        int ydist = Math.abs(ml.row - goal.row);
        return (xdist + ydist);
    }

    public static void main(String[] args) {
        Maze maze = new Maze();

        logger.info("生成随机迷宫：");
        logger.info(maze);
        logger.info("\n");

        logger.info("深度优先遍历解法：");
        Node<MazeLocation> solution1 = GraphSearch.dfs(maze.start, maze::goalTest, maze::successors);
        if (solution1 == null) {
            logger.info("No solution found using depth-first search!");
        } else {
            List<MazeLocation> path1 = GraphSearch.nodeToPath(solution1);

            maze.mark(path1);
            logger.info(maze);
            maze.clear(path1);
        }
        logger.info("\n");

        logger.info("广度优先遍历解法：");
        Node<MazeLocation> solution2 = GraphSearch.bfs(maze.start, maze::goalTest, maze::successors);
        if (solution2 == null) {
            logger.info("No solution found using depth-first search!");
        } else {
            List<MazeLocation> path2 = GraphSearch.nodeToPath(solution2);

            maze.mark(path2);
            logger.info(maze);
            maze.clear(path2);
        }
        logger.info("\n");

        logger.info("A*遍历解法：");
        Node<MazeLocation> solution3 = GraphSearch.aStar(maze.start, maze::goalTest, maze::successors, maze::manhattanDistance);
        if (solution3 == null) {
            logger.info("No solution found using A*!");
        } else {
            List<MazeLocation> path3 = GraphSearch.nodeToPath(solution3);
            maze.mark(path3);
            logger.info(maze);
            maze.clear(path3);
        }
    }

    enum Cell {
        EMPTY(" "), BLOCKED("X"), START("S"), GOAL("G"), PATH("*");

        private final String code;

        Cell(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return code;
        }
    }

    public record MazeLocation(int row, int column) {
    }

}
