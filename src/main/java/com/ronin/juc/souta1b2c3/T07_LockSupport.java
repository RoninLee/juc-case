package com.ronin.juc.souta1b2c3;

import java.util.concurrent.locks.LockSupport;

/**
 * @author Ronin
 * @date 2021/8/10 22:03
 * @description LockSupport方式实现
 */
public class T07_LockSupport {

    static Thread t1, t2;

    public static void main(String[] args) {
        char[] num = "123456789".toCharArray();
        char[] letter = "ABCDEFGHI".toCharArray();
        t1 = new Thread(() -> {
            for (char c : num) {
                System.out.print(c);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        });
        t1.start();
        t2 = new Thread(() -> {
            for (char c : letter) {
                LockSupport.park();
                System.out.print(c);
                LockSupport.unpark(t1);
            }
        });
        t2.start();

    }
}
