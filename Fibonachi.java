package com.company.연습장;

import org.w3c.dom.ls.LSOutput;

public class Fibonachi {
    public static void main(String[] args) {
        // 피보나치 수열 구하기
        // 0, 1, 1, 2, 3, 5, 8, 13
        int num = new java.util.Scanner(System.in).nextInt();
        for (int i = 0; i < num; i++) {
            System.out.print(fibonachi(i) + " ");
        }

    }
    public static int fibonachi(int x) {
        if(x <= 1){
            return x;
        } else {
            return fibonachi(x-2) +fibonachi(x-1);
        }
    }
}
