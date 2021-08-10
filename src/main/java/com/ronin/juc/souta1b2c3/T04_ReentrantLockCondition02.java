package com.ronin.juc.souta1b2c3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Ronin
 * @date 2021/8/10 21:27
 * @description ReentrantLock 案例1
 */
public class T04_ReentrantLockCondition02 {
    public static void main(String[] args) {
        char[] num = "123456789".toCharArray();
        char[] letter = "ABCDEFGHI".toCharArray();

        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();

        new Thread(() -> {
            try {
                lock.lock();
                for (char c : num) {
                    System.out.print(c);
                    try {
                        condition2.signal();
                        condition1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                condition2.signal();
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
                        condition1.signal();
                        condition2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                condition1.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }, "t2").start();

    }
}
