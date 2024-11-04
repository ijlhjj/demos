package com.sweetmanor.algorithm.chocosolver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.variables.IntVar;

/**
 * 八皇后问题
 *
 * @author ijlhjj
 * @version 1.0 2024-01-11
 */
public class NQueenExample {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        final int N = 8;  // 定义 N 为 8

        // 定义问题模型
        Model model = new Model(N + "-queens problem");

        // 定义变量数组，并添加变量值域范围
        // a modelling trick is to fix the row a queen can go to and only question on their column.
        IntVar[] vars = new IntVar[N];
        for (int q = 0; q < N; q++) {
            vars[q] = model.intVar("Q_" + q, 1, N); // 值域范围 1..N
        }

        // 添加约束
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                model.arithm(vars[i], "!=", vars[j]).post(); // 列不能相同
                model.arithm(vars[i], "!=", vars[j], "-", j - i).post(); //不能在同一斜线
                model.arithm(vars[i], "!=", vars[j], "+", j - i).post();
            }
        }

        // 求解并打印结构
        Solution solution = model.getSolver().findSolution();
        if (solution != null) {
            logger.info(solution);
        }
    }

}
