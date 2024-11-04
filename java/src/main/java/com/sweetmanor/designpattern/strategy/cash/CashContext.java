package com.sweetmanor.designpattern.strategy.cash;

/**
 * 策略模式示例
 *
 * @author ijlhjj
 * @version 1.0 2015-12-27
 */
public class CashContext {

    private Cash cash;

    public CashContext() {
    }

    public CashContext(Cash cash) {
        this.cash = cash;
    }

    public void setCash(Cash cash) {
        this.cash = cash;
    }

    public double getResult(double money) {
        return cash.acceptCash(money);
    }

}
