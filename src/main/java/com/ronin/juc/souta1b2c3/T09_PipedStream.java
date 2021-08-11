package com.ronin.juc.souta1b2c3;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @author ronin
 * @date Created on 2021/8/11 13:23
 * @description PipedStream方式实现
 */
public class T09_PipedStream {
    public static void main(String[] args) {
        char[] num = "123456789".toCharArray();
        char[] letter = "ABCDEFGHI".toCharArray();

        PipedInputStream numInput = new PipedInputStream();
        PipedInputStream letterInput = new PipedInputStream();
        PipedOutputStream numOutput = new PipedOutputStream();
        PipedOutputStream letterOutput = new PipedOutputStream();
        try {
            numOutput.connect(numInput);
            letterOutput.connect(letterInput);
            String msg = "ok";
            new Thread(() -> {
                for (char c : num) {
                    try {
                        byte[] bytes = new byte[2];
                        numInput.read(bytes);
                        if (new String(bytes).equals(msg)) {
                            System.out.print(c);
                            letterOutput.write(msg.getBytes());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            new Thread(() -> {
                for (char c : letter) {
                    try {
                        byte[] bytes = new byte[2];
                        numOutput.write(msg.getBytes());
                        letterInput.read(bytes);
                        if (new String(bytes).equals(msg)) {
                            System.out.print(c);
                            continue;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
