package com.company.Day0414;

import java.util.Scanner;
import java.util.Random;

public class WonPI {
    public static void main(String[] args) {
        System.out.println("던질 횟수를 입력해주세요");
        Scanner scanner = new Scanner(System.in);
        long tries = scanner.nextLong();
        long hits = 0;
        Random ran = new Random();
        for (long i = 0; i < tries; i++) {
            double x = ran.nextDouble() * 2 - 1;
            double y = ran.nextDouble() * 2 - 1;
            if (Math.sqrt((0 - x) * (0 - x) + (0 - y) * (0 - y)) < 1.0) {
                hits++;
            }
        }
        double piEstimate = 4 * (double)hits / (double)tries;
        System.out.printf("%.6f\n", piEstimate);
    }
}
