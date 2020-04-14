package com.company.Day0414;
import java.util.Scanner;

public class Gugudan {
    public static void main(String[] args) {
        Gugudan();
    }

    public static void Gugudan() {
        for (int i = 1; i < 10 ; i++) {
            for (int j = 2; j < 10; j++) {
                int gugu = i * j;
                System.out.printf("%d * %d = %2d \t\t", j, i, gugu);
            }
            System.out.println();
        }
    }
}

