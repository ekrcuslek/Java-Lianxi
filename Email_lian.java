package com.company.Day0413;

public class Email_lian {
    public static void main(String[] args) {
        email("hohoahah@hanmail.net",
                "자바 마스터가 되기 위한 험난한 과정!");
    }
    // 오버로드
    public static void email(String address, String text) {
        System.out.println(address + "에 아래의 메일을 송신한다.");
        System.out.println("제목 : 제목 없음");
        System.out.println("본문 : " + text);

    }
    public static void email(String title, String address, String text) {
        System.out.println(address + "에 아래의 메일을 송신한다.");
        System.out.println("제목 : " + title);
        System.out.println("본문 : " + text);

    }
}
