package com.company;



// 0~100까지 숫자에서 1이 몇개고 2가 몇개고 문제
public class Lianxilianxixi {
    public static void main(String[] args) {
        String num = "";
        for (int i = 1; i < 100; i++) {
            num += Integer.toString(i);
        }

        String[] numbers = num.split("");

        int count1 = 0, count2 = 0, count3 = 0, count4 = 0, count5 = 0, count6 = 0, count7 = 0, count8 = 0, count9 = 0;
        for (String number : numbers) {
            if (Integer.toString(1).equals(number)) {
                count1++;
            }
        }
        for (String number : numbers) {
            if (Integer.toString(2).equals(number)) {
                count2++;
            }
        }
        for (String number : numbers) {
            if (Integer.toString(3).equals(number)) {
                count3++;
            }
        }
        for (String number : numbers) {
            if (Integer.toString(4).equals(number)) {
                count4++;
            }
        }
        System.out.println(count1 + " " + count2 + " " + count3 + " " + count4);
    }
}
