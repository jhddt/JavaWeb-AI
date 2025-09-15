package com.itheima;

import java.util.Scanner;

public class ArithmeticoperatorDemo1 {
    public static void main(String[] args) {
        //数值拆分
      /*公式：
        个位：n % 10
        十位：n / 10 % 10
        百位：n /100 % 10
        以此类推...
        */
        System.out.println("拆分从键盘录入的数字");
        Scanner in = new Scanner(System.in);
        int number = in.nextInt();
        int ge = (number % 10);
        int shi = (number / 10 % 10);
        int bai = (number /100 % 10);
        System.out.println("个位：" + ge + '\n' + "十位：" + shi + '\n' + "百位：" + bai);
    }
}
