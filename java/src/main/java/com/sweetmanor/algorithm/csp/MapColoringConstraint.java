package com.sweetmanor.algorithm.csp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 地图着色问题 —— 《算法精粹》
 *
 * @author ijlhjj
 * @version 1.0 2023-12-14
 */
public class MapColoringConstraint extends Constraint<String, String> {
    private static final Logger logger = LogManager.getLogger();

    private String place1;
    private String place2; //两个相邻的地区

    public MapColoringConstraint(String place1, String place2) {
        super(List.of(place1, place2));
        this.place1 = place1;
        this.place2 = place2;
    }

    @Override
    public boolean satisfied(Map<String, String> assignment) {
        //如果有未着色地区，不会违反约束
        if (!assignment.containsKey(place1) || !assignment.containsKey(place2))
            return true;
        //两个地区着色不同，满足约束
        return !assignment.get(place1).equals(assignment.get(place2));
    }

    public static void main(String[] args) {
        //变量为7个地区
        List<String> variables = List.of("Western Australia", "Northern Territory", "South Australia", "Queensland", "New South Wales", "Victoria", "Tasmania");

        //每个变量的值域都是红、绿、蓝三色
        Map<String, List<String>> domains = new HashMap<>();
        for (String v : variables)
            domains.put(v, List.of("red", "green", "blue"));

        CSP<String, String> csp = new CSP<>(variables, domains); //创建解题框架

        //添加约束，每对相邻地区为一个约束
        csp.addConstraint(new MapColoringConstraint("Western Australia", "Northern Territory"));
        csp.addConstraint(new MapColoringConstraint("Western Australia", "South Australia"));
        csp.addConstraint(new MapColoringConstraint("South Australia", "Northern Territory"));
        csp.addConstraint(new MapColoringConstraint("Queensland", "Northern Territory"));
        csp.addConstraint(new MapColoringConstraint("Queensland", "South Australia"));
        csp.addConstraint(new MapColoringConstraint("Queensland", "New South Wales"));
        csp.addConstraint(new MapColoringConstraint("New South Wales", "South Australia"));
        csp.addConstraint(new MapColoringConstraint("Victoria", "South Australia"));
        csp.addConstraint(new MapColoringConstraint("Victoria", "New South Wales"));
        csp.addConstraint(new MapColoringConstraint("Victoria", "Tasmania"));

        Map<String, String> solution = csp.backtrackingSearch();
        if (solution == null)
            logger.info("没有找到题解！");
        else
            logger.info(solution);
    }

}
