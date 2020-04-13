package com.company.Day0413;

public class Jisuanqi {
    public static void main(String[] args) {
        System.out.println("삼각형의 면적은 " + calcTriangleArea(5.2, 3.1) + "cm²");
        System.out.println("원의 면적은 " + calcCircleArea(5.7) + "cm²");

    }

    public static double calcTriangleArea(double bottom, double height) {
        double triangleArea = (bottom * height) / 2;
        return triangleArea;
    }

    public static double calcCircleArea(double radius) {
        double wonArea = 3.14 * Math.pow(radius,2);
        return wonArea;
    }
}
