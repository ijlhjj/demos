package com.sweetmanor.algorithm.csp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * 数字字谜问题 —— 《算法精粹》
 *
 * @author ijlhjj
 * @version 1.0 2023-12-16
 */
public class SendMoreMoneyConstraint extends Constraint<Character, Integer> {
    private static final Logger logger = LogManager.getLogger();

    private final List<Character> letters;

    public SendMoreMoneyConstraint(List<Character> letters) {
        super(letters);
        this.letters = letters;
    }

    @Override
    public boolean satisfied(Map<Character, Integer> assignment) {
        //不同字符不能有相同值
        if ((new HashSet<>(assignment.values())).size() < assignment.size())
            return false;

        //全部赋值后判断字谜等式
        if (assignment.size() == letters.size()) {
            int s = assignment.get('S');
            int e = assignment.get('E');
            int n = assignment.get('N');
            int d = assignment.get('D');
            int m = assignment.get('M');
            int o = assignment.get('O');
            int r = assignment.get('R');
            int y = assignment.get('Y');

            int send = s * 1000 + e * 100 + n * 10 + d;
            int more = m * 1000 + o * 100 + r * 10 + e;
            int money = m * 10000 + o * 1000 + n * 100 + e * 10 + y;

            return send + more == money;
        }

        return true;
    }

    public static void main(String[] args) {
        //变量
        List<Character> letters = List.of('S', 'E', 'N', 'D', 'M', 'O', 'R', 'Y');

        //值域
        Map<Character, List<Integer>> domains = new HashMap<>();
        for (Character letter : letters)
            domains.put(letter, List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));

        // 和不以 0 为前缀，两个4位数的和 M 只能是 1
        domains.replace('M', List.of(1));

        CSP<Character, Integer> csp = new CSP<>(letters, domains); //创建解题框架

        //添加约束
        csp.addConstraint(new SendMoreMoneyConstraint(letters));

        Map<Character, Integer> solution = csp.backtrackingSearch();
        if (solution == null)
            logger.info("没有找到题解！");
        else
            logger.info(solution);
    }

}
