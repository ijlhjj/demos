package com.sweetmanor.algorithm.csp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 八皇后问题 —— 《算法精粹》
 *
 * @author ijlhjj
 * @version 1.0 2023-12-14
 */
public class QueensConstraint extends Constraint<Integer, Integer> {
    private static final Logger logger = LogManager.getLogger();

    private List<Integer> columns;

    public QueensConstraint(List<Integer> columns) {
        super(columns);
        this.columns = columns;
    }

    @Override
    public boolean satisfied(Map<Integer, Integer> assignment) {
        //检查当前所有参数赋值
        for (var item : assignment.entrySet()) {
            int q1c = item.getKey(); //列号
            int q1r = item.getValue(); //行号
            for (int q2c = q1c + 1; q2c <= columns.size(); q2c++) {
                if (assignment.containsKey(q2c)) { //当前列已赋值
                    int q2r = assignment.get(q2c);
                    //同一行
                    if (q1r == q2r)
                        return false;
                    //同一斜线
                    if (Math.abs(q1r - q2r) == Math.abs(q1c - q2c))
                        return false;
                }
            }
        }
        return true; //不违反约束
    }

    public static void main(String[] args) {
        //变量为8列
        var columns = List.of(1, 2, 3, 4, 5, 6, 7, 8);

        //每个变量的值域都是8行
        Map<Integer, List<Integer>> rows = new HashMap<>();
        for (int col : columns)
            rows.put(col, List.of(1, 2, 3, 4, 5, 6, 7, 8));

        CSP<Integer, Integer> csp = new CSP<>(columns, rows);//创建解题框架

        //添加约束
        csp.addConstraint(new QueensConstraint(columns));

        Map<Integer, Integer> solution = csp.backtrackingSearch();
        if (solution == null)
            logger.info("没有找到题解！");
        else
            logger.info(solution);
    }

}
