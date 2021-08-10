package com.ronin.juc.souta1b2c3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Ronin
 * @date 2021/8/10 21:27
 * @description ReentrantLock 案例1
 */
public class T03_ReentrantLockCondition01 {
    public static void main(String[] args) {
        char[] num = "123456789".toCharArray();
        char[] letter = "ABCDEFGHI".toCharArray();

        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> {
            try {
                lock.lock();
                for (char c : num) {
                    System.out.print(c);
                    try {
                        condition.signal();
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }, "t1").start();
        new Thread(() -> {
            try {
                lock.lock();
                for (char c : letter) {
                    System.out.print(c);
                    try {
                        condition.signal();
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }, "t2").start();

    }
}
