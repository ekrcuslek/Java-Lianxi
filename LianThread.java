package com.company.Day0414;

public class LianThread {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("3초간 기다림!");

        // 3초간 기다림
        Thread.sleep(3000); // 단위는 밀리세컨드 1000분의 1초

        System.out.println("끝");
    }
}
