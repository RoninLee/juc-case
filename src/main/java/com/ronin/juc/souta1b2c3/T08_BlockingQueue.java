package com.ronin.juc.souta1b2c3;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author ronin
 * @date Created on 2021/8/11 10:42
 * @description BlockingQueue方式实现
 */
public class T08_BlockingQueue {
    public static void main(String[] args) {
        char[] num = "123456789".toCharArray();
        char[] letter = "ABCDEFGHI".toCharArray();

        BlockingQueue<Character> blockingQueueLetter = new LinkedBlockingQueue<>();
        BlockingQueue<Character> blockingQueueNum = new LinkedBlockingQueue<>();
        new Thread(() -> {
            for (char c : num) {
                try {
                    blockingQueueNum.put(c);
                    System.out.print(blockingQueueLetter.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            for (char c : letter) {
                try {
                    System.out.print(blockingQueueNum.take());
                    blockingQueueLetter.put(c);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
