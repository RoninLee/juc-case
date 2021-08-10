package com.ronin.juc.souta1b2c3;

/**
 * @author Ronin
 * @date 2021/8/9 20:52
 * @description
 */
public class T02_SyncWaitNotifyWithSwitch {
    private static volatile boolean t2Start = true;

    public static void main(String[] args) {
        char[] num = "123456789".toCharArray();
        char[] letter = "ABCDEFGHI".toCharArray();
        final Object lock = new Object();
        new Thread(() -> {
            synchronized (lock) {
                if (t2Start) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (char b : num) {
                    System.out.print(b);
                    try {
                        t2Start = true;
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
        }, "t1").start();
        new Thread(() -> {
            synchronized (lock) {
                if (!t2Start) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (char b : letter) {
                    System.out.print(b);
                    try {
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
        }, "t2").start();

    }
}
