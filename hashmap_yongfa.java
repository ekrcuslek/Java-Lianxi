import java.util.Random;
import java.util.Scanner;
import java.util.HashMap;

class Main{
  public static void main(String[] args){
    
    System.out.println("점을 보세요");
    System.out.println("이름을 입력해 주세요");
    String name = new Scanner(System.in).nextLine();
    System.out.println("나이를 입력 해 주세요");
    String ageString = new Scanner(System.in).nextLine();
    int age = Integer.parseInt(ageString);
    int fortune = new Random().nextInt(4);
    fortune++;
    System.out.println("점꾀가 나왔습니다!");
    System.out.println
    (age + "살의 " + name + "씨, 당신의 운세번호는 " + fortune + " 입니다");
    System.out.println("1:대박 2:중박 3:보통 4:망");
    System.out.println("");
    System.out.println("--------------------------------------");

    HashMap<Integer, String> fortuneMap = new HashMap<>();
    fortuneMap.put(1, "대박");
    fortuneMap.put(2, "중박");
    fortuneMap.put(3, "보통");
    fortuneMap.put(4, "망");
    if (fortune == 1 || fortune == 2 || fortune == 3 || fortune == 4) {
      System.out.println("즉 " + name +"씨의 운세는 " + fortuneMap.get(fortune) + " 이쥬");  
    }
  }
}
