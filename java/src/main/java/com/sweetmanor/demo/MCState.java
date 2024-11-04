package com.sweetmanor.demo;

import com.sweetmanor.algorithm.search.GraphSearch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * 传教士和食人族问题 —— 《算法精粹》
 *
 * @author ijlhjj
 * @version 1.0 2023-12-12
 */
public class MCState {
    private static final Logger logger = LogManager.getLogger();

    private static final int MAX_NUM = 3;
    private static int step = 0; //移动步骤计数
    private final int wm; //西岸传教士
    private final int wc; //西岸食人族
    private final int em; //东岸传教士
    private final int ec; //东岸食人族
    private final boolean boat; //西岸是否有船

    public MCState(int wm, int wc, boolean boat) {
        this.wm = wm;
        this.wc = wc;
        this.em = MAX_NUM - wm;
        this.ec = MAX_NUM - wc;
        this.boat = boat;
    }

    /**
     * 测试是否完成题解
     */
    public boolean goalTest() {
        return isLegal() && em == MAX_NUM && ec == MAX_NUM;
    }

    /**
     * 当前状态是否符合题目要求
     */
    public boolean isLegal() {
        //西岸 传教士 小于 食人族
        if (wm < wc && wm > 0)
            return false;

        //东岸 传教士 小于 食人族
        if (em < ec && em > 0)
            return false;

        return true;
    }

    /**
     * 查找当前状态可进行的操作：尝试添加所有可能的组合，添加完后滤掉不符合要求的步骤。
     */
    public static List<MCState> successors(MCState mcs) {
        List<MCState> sucs = new ArrayList<>();

        if (mcs.boat) { //西岸有船
            //坐2个传教士
            if (mcs.wm > 1)
                sucs.add(new MCState(mcs.wm - 2, mcs.wc, !mcs.boat));

            //坐1个传教士
            if (mcs.wm > 0)
                sucs.add(new MCState(mcs.wm - 1, mcs.wc, !mcs.boat));

            //坐2个食人族
            if (mcs.wc > 1)
                sucs.add(new MCState(mcs.wm, mcs.wc - 2, !mcs.boat));

            //坐1个食人族
            if (mcs.wc > 0)
                sucs.add(new MCState(mcs.wm, mcs.wc - 1, !mcs.boat));

            //坐1个传教士和1个食人族
            if (mcs.wm > 0 && mcs.wc > 0)
                sucs.add(new MCState(mcs.wm - 1, mcs.wc - 1, !mcs.boat));
        } else { //东岸有船
            //坐2个传教士
            if (mcs.em > 1)
                sucs.add(new MCState(mcs.wm + 2, mcs.wc, !mcs.boat));

            //坐1个传教士
            if (mcs.em > 0)
                sucs.add(new MCState(mcs.wm + 1, mcs.wc, !mcs.boat));

            //坐2个食人族
            if (mcs.ec > 1)
                sucs.add(new MCState(mcs.wm, mcs.wc + 2, !mcs.boat));

            //坐1个食人族
            if (mcs.ec > 0)
                sucs.add(new MCState(mcs.wm, mcs.wc + 1, !mcs.boat));

            //坐1个传教士和1个食人族
            if (mcs.em > 0 && mcs.ec > 0)
                sucs.add(new MCState(mcs.wm + 1, mcs.wc + 1, !mcs.boat));
        }

        sucs.removeIf(Predicate.not(MCState::isLegal));//移除不符合要求的移动

        return sucs;
    }

    /**
     * 打印题解步骤
     */
    public static void displaySolution(List<MCState> path) {
        if (path.isEmpty())
            return;

        MCState oldState = path.get(0);
        logger.info(oldState);

        for (MCState currentState : path.subList(1, path.size())) {
            if (currentState.boat)
                logger.info("{} 传教士 和 {} 食人族 从 东岸 移动到 西岸", oldState.em - currentState.em, oldState.ec - currentState.ec);
            else
                logger.info("{} 传教士 和 {} 食人族 从 西岸 移动到 东岸", oldState.wm - currentState.wm, oldState.wc - currentState.wc);
            logger.info(currentState);
            oldState = currentState;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MCState mcState = (MCState) o;
        return wm == mcState.wm && wc == mcState.wc && em == mcState.em && ec == mcState.ec && boat == mcState.boat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(wm, wc, em, ec, boat);
    }

    @Override
    public String toString() {
        return String.format("%d、 西岸传教士=%d, 西岸食人族=%d, 东岸传教士=%d, 东岸食人族=%d, %s有船", step++, wm, wc, em, ec, boat ? "西岸" : "东岸");
    }

    public static void main(String[] args) {
        MCState start = new MCState(MAX_NUM, MAX_NUM, true);
        GraphSearch.Node<MCState> solution = GraphSearch.bfs(start, MCState::goalTest, MCState::successors);
        if (solution == null) {
            logger.info("没有找到题解!");
        } else {
            List<MCState> path = GraphSearch.nodeToPath(solution);
            displaySolution(path);
        }
    }

}
