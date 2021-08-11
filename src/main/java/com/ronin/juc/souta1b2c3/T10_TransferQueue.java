package com.ronin.juc.souta1b2c3;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * @author ronin
 * @date Created on 2021/8/11 13:40
 * @description TransferQueue方式实现
 */
public class T10_TransferQueue {
    public static void main(String[] args) {
        char[] num = "123456789".toCharArray();
        char[] letter = "ABCDEFGHI".toCharArray();

        TransferQueue<Character> numTransferQueue = new LinkedTransferQueue<>();
        TransferQueue<Character> letterTransferQueue = new LinkedTransferQueue<>();

        new Thread(() -> {
            for (char c : num) {
                try {
                    numTransferQueue.put(c);
                    System.out.print(letterTransferQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            for (char c : letter) {
                try {
                    System.out.print(numTransferQueue.take());
                    letterTransferQueue.put(c);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
