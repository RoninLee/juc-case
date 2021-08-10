package com.ronin.juc.souta1b2c3;

/**
 * @author Ronin
 * @date 2021/8/10 21:44
 * @description 自旋锁方式实现
 */
public class T05_CAS {
    enum ThreadNode {T1, T2}

    private static volatile ThreadNode t = ThreadNode.T1;

    public static void main(String[] args) {
        char[] num = "123456789".toCharArray();
        char[] letter = "ABCDEFGHI".toCharArray();

        new Thread(() -> {
            for (char c : num) {
                while (t.equals(ThreadNode.T2)) {
                }
                System.out.print(c);
                t = ThreadNode.T2;
            }
        }).start();
        new Thread(() -> {
            for (char c : letter) {
                while (t.equals(ThreadNode.T1)) {
                }
                System.out.print(c);
                t = ThreadNode.T1;
            }
        }).start();

    }
}
