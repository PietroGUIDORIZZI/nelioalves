package a04EstruturaSequencial.Ex1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite dois números para somar: ");
        int x = sc.nextInt();
        int y = sc.nextInt();
        System.out.println("Soma = " + (x + y));
    }

}
