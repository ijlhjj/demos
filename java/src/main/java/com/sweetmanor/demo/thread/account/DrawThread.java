package com.sweetmanor.demo.thread.account;

/**
 * 模拟多线程对同一个账号进行取款操作，不做线程同步的情况下将出现错误结果
 *
 * @author wenz
 * @version 1.0 2016-06-18
 */
public class DrawThread extends Thread {
    private Account account; // 账户
    private double drawAmount; // 取款数额

    public DrawThread(String name, Account account, double drawAmount) {
        super(name);
        this.account = account;
        this.drawAmount = drawAmount;
    }

    // 没有对共享数据加锁，将涉及线程安全问题
    @Override
    public void run() {
        // 账户余额大于取款数额
        if (account.getBalance() >= drawAmount) {
            // 吐出钞票
            System.out.println(getName() + "取款成功！吐出钞票：" + drawAmount);
            // 暂停当前线程，强制切换到另一个线程，以使结果明显
            try {
                Thread.sleep(1);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            // 修改余额
            account.setBalance(account.getBalance() - drawAmount);
            System.out.println("\t余额为：" + account.getBalance());
        } else {
            System.out.println(getName() + "取款失败！余额不足。");
        }
    }

    public static void main(String[] args) {
        Account acct = new Account("1234567", 1000); // 创建一个账户
        // 模拟两个线程对同一个账户取款
        new DrawThread("甲", acct, 800).start();
        new DrawThread("乙", acct, 800).start();
    }

}
