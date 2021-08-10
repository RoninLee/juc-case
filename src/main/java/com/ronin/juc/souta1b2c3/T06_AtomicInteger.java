package com.ronin.juc.souta1b2c3;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Ronin
 * @date 2021/8/10 21:55
 * @description AtomicInteger方式实现(类CAS)
 */
public class T06_AtomicInteger {

    private static volatile AtomicInteger flag = new AtomicInteger(1);

    public static void main(String[] args) {
        char[] num = "123456789".toCharArray();
        char[] letter = "ABCDEFGHI".toCharArray();

        new Thread(() -> {
            for (char c : num) {
                while (flag.get() != 1) {
                }
                System.out.print(c);
                flag.set(2);
            }
        }).start();
        new Thread(() -> {
            for (char c : letter) {
                while (flag.get() != 2) {
                }
                System.out.print(c);
                flag.set(1);
            }
        }).start();

    }
}
