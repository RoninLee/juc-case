package com.ronin.juc.souta1b2c3;

/**
 * @author Ronin
 * @date 2021/8/9 20:52
 * @description 两个线程交替打印
 */
public class T01_SyncWaitNotify {
    public static void main(String[] args) {
        char[] num = "123456789".toCharArray();
        char[] letter = "ABCDEFGHI".toCharArray();
        final Object lock = new Object();
        new Thread(() -> {
            synchronized (lock) {
                for (char b : num) {
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
        }, "t1").start();
        new Thread(() -> {
            synchronized (lock) {
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
