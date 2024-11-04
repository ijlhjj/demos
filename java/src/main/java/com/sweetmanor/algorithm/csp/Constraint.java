package com.sweetmanor.algorithm.csp;

import java.util.List;
import java.util.Map;

/**
 * 约束 —— 《算法精粹》
 * <p>
 * V - 变量类型 ， D - 值域类型
 *
 * @author ijlhjj
 * @version 1.0 2023-12-13
 */
public abstract class Constraint<V, D> {

    protected List<V> variables;

    protected Constraint(List<V> variables) {
        this.variables = variables;
    }

    //校验是否满足约束条件
    public abstract boolean satisfied(Map<V, D> assignment);

}
