package com.sweetmanor.algorithm.ortools.linearoptimization;

import com.google.ortools.Loader;
import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 线性规划
 * <p>
 * 尽可能增加 3x + y，但需遵循以下限制条件：
 * 0 ≤ x ≤ 1
 * 0 ≤ y ≤ 2
 * x + y ≤ 2
 *
 * @author ijlhjj
 * @version 1.0 2023-12-24
 */
public class LinearExample1 {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        // 1、导入所需的库
        Loader.loadNativeLibraries();

        // 2、声明求解器
        // Create the linear solver with the GLOP backend.
        MPSolver solver = MPSolver.createSolver("GLOP");

        // 3、创建变量
        // Create the variables x and y.
        MPVariable x = solver.makeNumVar(0.0, 1.0, "x");
        MPVariable y = solver.makeNumVar(0.0, 2.0, "y");

        logger.info("Number of variables = {}", solver.numVariables());

        // 4、定义限制条件
        // 前两个约束条件 (0 ≤ x ≤ 1 和 0 ≤ y ≤ 2) 已由变量的定义设置。以下代码定义了约束条件 x + y ≤ 2：
        // Create a linear constraint, 0 <= x + y <= 2.
        MPConstraint ct = solver.makeConstraint(0.0, 2.0, "ct");
        ct.setCoefficient(x, 1); // setCoefficient 方法可在约束条件表达式中设置 x 和 y 的系数。
        ct.setCoefficient(y, 1);

        logger.info("Number of constraints = {}", solver.numConstraints());

        // 5、定义目标函数
        // Create the objective function, 3 * x + y.
        MPObjective objective = solver.objective();
        objective.setCoefficient(x, 3);
        objective.setCoefficient(y, 1);
        objective.setMaximization(); // setMaximization 方法声明这是个最大化问题。

        // 6、调用求解器并显示结果
        solver.solve();

        // 7、显示结果
        logger.info("Solution:");
        logger.info("Objective value = {}", objective.value());
        logger.info("x = {}", x.solutionValue());
        logger.info("y = {}", y.solutionValue());
    }

}
