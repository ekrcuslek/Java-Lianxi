package com.company.연습장;

import java.util.Scanner;

public class ArrayDuplicate {
    public static void main(String[] args) {
        String j = new Scanner(System.in).nextLine();
        String[] line = j.split("");
        arraysDupicatecheck(line);
        numberCount(line, 2)
    }
        // line[]를 아래 메서드에 넣음
    public static void arraysDupicatecheck(String[] line) {
        // line[0]일 때 line[1~n]까지 반복, line[1]일 때 또 반복 이런 식의 반복
        for (int i = 0; i < line.length; i++) {
            String a = line[i];  // line[i]를 변수a에 넣어, 다른 변수와 비교하기 위함
            for (int j = 0; j < line.length; j++) {
                if (i == j) continue; // 0 == 0은 건너뛰고
                // line[j]를 변수 b에 넣어, a랑 비교하기 위함// 배열 내 요소는 변하지 않고 그냥 값만 변수에 넣는겨
                // 하나 하나 넣어서 비교하는 거지비
                String b = line[j];
                if (a.equals(b)) {
                    System.out.println("중복 =>" + i + "번째 요소 " + a);
                    break;
                }
            }
        }

    }
    public static void numberCount(String[] line, int num) {
        int count = 0;
        String numStr = num + "";   // 숫자를 String으로 변환
        for (String a : line) {
            if (numStr.equals(a)) { // 특정 문자가 line[i]와 같으면 count 1증가
                count++;
            }
        }
        System.out.println("중복 개수 : " + count);
    }
}

