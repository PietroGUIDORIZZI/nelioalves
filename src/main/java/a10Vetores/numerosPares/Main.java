package a10Vetores.numerosPares;

import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Quantos numeros voce vai digitar? ");
        int n = Integer.parseInt(sc.nextLine());
        int nums[] = new int[n];


        for (int i = 0; i < n ; i++) {
            System.out.print("Digite um numero: ");
            nums[i] = sc.nextInt();
        }
        System.out.println("NUMEROS PARES:");
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                if (count == 0) {
                    System.out.print(nums[i]);
                    count++;
                    continue;
                }
                System.out.print(String.format("  %d", nums[i]));
                count++;
            }
        }
        System.out.println();
        System.out.print("QUANTIDADE DE PARES = " + count);

        sc.close();
    }
}
