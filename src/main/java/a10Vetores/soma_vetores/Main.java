package a10Vetores.soma_vetores;

import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Quantos valores vai ter cada vetor? ");
        int n = sc.nextInt();
        int a[] = new int[n];
        int b[] = new int[n];
        int res[] = new int[n];
        System.out.println("Digite os valores do vetor A:");
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        System.out.println("Digte os valores do vetor B:");
        for (int i = 0; i < n; i++) {
            b[i] = sc.nextInt();
        }
        System.out.println("VETOR RESULTANTE:");
        for (int i = 0; i < n; i++) {
            res[i] = a[i] + b[i];
            System.out.println(res[i]);
        }

        sc.close();
    }
}
