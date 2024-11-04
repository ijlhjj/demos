package com.sweetmanor.algorithm.csp;

import java.util.*;

/**
 * 约束满足问题 —— 《算法精粹》
 * <p>
 * V - 变量类型 ， D - 值域类型
 *
 * @author ijlhjj
 * @version 1.0 2023-12-13
 */
public class CSP<V, D> {

    private List<V> variables; //变量
    private Map<V, List<D>> domains; //值域
    private Map<V, List<Constraint<V, D>>> constraints = new HashMap<>();//约束

    public CSP(List<V> variables, Map<V, List<D>> domains) {
        this.variables = variables;
        this.domains = domains;

        //每个变量默认添加一个空的约束列表
        for (V v : variables) {
            //校验变量必须在值域字典中
            if (!domains.containsKey(v))
                throw new IllegalArgumentException("Every variable should have a domain assigned to it.");
            constraints.put(v, new ArrayList<>());
        }
    }

    /**
     * 增加约束
     */
    public void addConstraint(Constraint<V, D> constraint) {
        for (V v : constraint.variables) {
            //新增约束的变量必须包含在当前变量列表中
            if (!variables.contains(v))
                throw new IllegalArgumentException("Variable in constraint not in CSP");
            constraints.get(v).add(constraint); //约束添加到约束字典
        }
    }

    /**
     * 检查当前赋值是否满足所有约束
     */
    public boolean consistent(V v, Map<V, D> assignment) {
        for (Constraint<V, D> constraint : constraints.get(v)) {
            //校验是否满足约束条件
            if (!constraint.satisfied(assignment))
                return false;
        }
        return true;
    }

    /**
     * 递归回溯搜索
     */
    public Map<V, D> backtrackingSearch(Map<V, D> assignment) {
        //所有变量已赋值，完成搜索
        if (assignment.size() == variables.size())
            return assignment;

        //找一个未赋值的变量
        V unassigned = variables.stream().filter(v -> !assignment.containsKey(v)).findFirst().get();

        //尝试变量所有可能赋值
        for (D value : domains.get(unassigned)) {
            //创建副本，并增加当前赋值
            Map<V, D> localAssignment = new HashMap<>(assignment);
            localAssignment.put(unassigned, value);

            //检查当前赋值是否满足约束条件
            if (consistent(unassigned, localAssignment)) {
                Map<V, D> result = backtrackingSearch(localAssignment); //递归求解

                //找到满足约束的解
                if (result != null)
                    return result;
            }
        }

        //没有找到解时返回 null
        return Collections.emptyMap();
    }

    /**
     * 回溯搜索
     */
    public Map<V, D> backtrackingSearch() {
        return backtrackingSearch(new HashMap<>());
    }

}
