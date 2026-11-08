package a05EstruturasCondicionais.ex8;

import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o salário para ver o imposto: ");
        double salary = sc.nextDouble();

        if(salary <= 2000){
            System.out.println("Isento");
        } else if (salary <= 3000) {
            System.out.printf("R$ %.2f%n", (salary - 2000.0) * 0.08);
        } else if (salary <= 4500) {
            System.out.printf("R$ %.2f%n",
                              (salary - 3000.0) * 0.18 + 1000.0 * 0.08);
        } else {
            System.out.printf("R$ %.2f%n",
                              (salary - 4500) * 0.28 + 1500.0 * 0.18 + 1000 * 0.08 );

        }
        sc.close();

    }
}
