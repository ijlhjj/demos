package com.sweetmanor.algorithm.csp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/**
 * 单词字谜游戏 —— 《算法精粹》
 *
 * @author ijlhjj
 * @version 1.0 2023-12-16
 */
public class WordGrid {
    private static final Logger logger = LogManager.getLogger();

    private static final char ALPHABET_LENGTH = 26;
    private static final char FIRST_LETTER = 'A';
    private final int rows;
    private final int columns; //行列数
    private final char[][] grid; //网格

    public WordGrid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        grid = new char[rows][columns];
        fillRandom();
    }

    /**
     * 随机填充网格字符
     */
    private void fillRandom() {
        //初始化网格，随机填充字符
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                char letter = (char) (random.nextInt(ALPHABET_LENGTH) + FIRST_LETTER); //生成随机字符
                grid[i][j] = letter;
            }
        }
    }

    /**
     * 在指定网格填充单词
     */
    public void mark(String word, List<GridLocation> locations) {
        for (int i = 0; i < word.length(); i++) {
            GridLocation location = locations.get(i);
            grid[location.row][location.column] = word.charAt(i);
        }
    }

    /**
     * 生成单词值域
     */
    public List<List<GridLocation>> generateDomain(String word) {
        List<List<GridLocation>> domain = new ArrayList<>();

        //遍历网格，查找所有可以放置单词的值域列表
        //值域的搜索一共8个方向，添加4个方向即可，另4个方向与添加的4个反向
        int length = word.length();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (j + length <= columns) {
                    //从左向右的值域
                    fillRight(domain, i, j, length);
                    //向右下的值域
                    if (i + length <= rows)
                        fillDiagonalRight(domain, i, j, length);
                }

                if (i + length <= rows) {
                    //从上向下的值域
                    fillDown(domain, i, j, length);
                    //向左下的值域
                    if (j - length >= 0)
                        fillDiagonalLeft(domain, i, j, length);
                }
            }
        }

        return domain;
    }

    /**
     * 向右的值域列表
     */
    private void fillRight(List<List<GridLocation>> domain, int row, int column, int length) {
        List<GridLocation> locations = new ArrayList<>();
        for (int col = column; col < column + length; col++) {
            locations.add(new GridLocation(row, col));
        }
        domain.add(locations);
    }

    /**
     * 向右下的斜线值域列表
     */
    private void fillDiagonalRight(List<List<GridLocation>> domain, int row, int column, int length) {
        List<GridLocation> locations = new ArrayList<>();
        for (int col = column; col < column + length; col++, row++) {
            locations.add(new GridLocation(row, col));
        }
        domain.add(locations);
    }

    /**
     * 向下的值域列表
     */
    private void fillDown(List<List<GridLocation>> domain, int row, int column, int length) {
        List<GridLocation> locations = new ArrayList<>();
        for (int r = row; r < row + length; r++) {
            locations.add(new GridLocation(r, column));
        }
        domain.add(locations);
    }

    /**
     * 向左下的斜线值域列表
     */
    private void fillDiagonalLeft(List<List<GridLocation>> domain, int row, int column, int length) {
        List<GridLocation> locations = new ArrayList<>();
        for (int r = row; r < row + length; r++, column--) {
            locations.add(new GridLocation(r, column));
        }
        domain.add(locations);
    }

    public static void main(String[] args) {
        WordGrid grid = new WordGrid(9, 9);

        //变量为 5 个单词
        List<String> words = List.of("MATTHEW", "JOE", "MARY", "SARAH", "SALLY");

        //为每个单词生成值域
        Map<String, List<List<GridLocation>>> domains = new HashMap<>();
        for (String word : words)
            domains.put(word, grid.generateDomain(word));

        CSP<String, List<GridLocation>> csp = new CSP<>(words, domains); //创建解题框架

        //添加约束
        csp.addConstraint(new WordSearchConstraint(words));

        Map<String, List<GridLocation>> solution = csp.backtrackingSearch();
        if (solution == null) {
            logger.info("没有找到题解！");
        } else {
            Random random = new Random();
            // 随机逆序一些单词位置
            solution.keySet().forEach(word -> {
                List<GridLocation> locations = solution.get(word);
                if (random.nextBoolean())
                    Collections.reverse(locations);
                grid.mark(word, locations); //填充单词
            });

            logger.info(grid); //打印题解
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char[] rowArray : grid) {
            sb.append(rowArray);
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    /**
     * 网格约束
     */
    static class WordSearchConstraint extends Constraint<String, List<GridLocation>> {
        public WordSearchConstraint(List<String> words) {
            super(words);
        }

        @Override
        public boolean satisfied(Map<String, List<GridLocation>> assignment) {
            List<GridLocation> allLocations = assignment.values().stream().flatMap(Collection::stream).toList();
            Set<GridLocation> allLocationsSet = new HashSet<>(allLocations);
            //占用网格不存在重复
            return allLocations.size() == allLocationsSet.size();
        }
    }

    public record GridLocation(int row, int column) {
    }

}
