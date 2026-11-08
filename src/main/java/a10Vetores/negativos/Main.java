package a10Vetores.negativos;

import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Quantos numeros voce vai digitar? ");
        int N = sc.nextInt();
        int nums[] = new int[N];
        for (int i = 0; i < N; i++) {
            System.out.print("Digite um numero: ");
            nums[i] = sc.nextInt();
        }
        System.out.println("NUMEROS NEGATIVOS: ");
        for (int i = 0; i < N; i++) {
            if (nums[i] < 0) {
                System.out.println(nums[i]);
            }
        }
        sc.close();
    }
}
