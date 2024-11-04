package com.sweetmanor.algorithm.ortools.csp;

import com.google.ortools.Loader;
import com.google.ortools.sat.CpModel;
import com.google.ortools.sat.CpSolver;
import com.google.ortools.sat.CpSolverStatus;
import com.google.ortools.sat.IntVar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 约束编程
 * <p>
 * x、y 和 z 这三个变量，每个变量可取以下值：0、1 或 2。
 * 一项限制条件：x != y
 *
 * @author ijlhjj
 * @version 1.0 2023-12-24
 */
public class SatProgram1 {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        // 1、导入所需的库
        Loader.loadNativeLibraries();

        // 2、声明 CP-SAT 模型
        CpModel model = new CpModel();

        // 3、创建变量
        int numVal = 2;

        IntVar x = model.newIntVar(0, numVal, "x");
        IntVar y = model.newIntVar(0, numVal, "y");
        IntVar z = model.newIntVar(0, numVal, "z");

        // 4、定义限制条件
        model.addDifferent(x, y);

        // 5、定义目标函数

        // 6、调用求解器
        CpSolver solver = new CpSolver();
        CpSolverStatus status = solver.solve(model);

        // 7、显示结果
        if (status == CpSolverStatus.OPTIMAL || status == CpSolverStatus.FEASIBLE) {
            logger.info("x = {}", solver.value(x));
            logger.info("y = {}", solver.value(y));
            logger.info("z = {}", solver.value(z));
        } else {
            logger.info("No solution found.");
        }
    }

}
