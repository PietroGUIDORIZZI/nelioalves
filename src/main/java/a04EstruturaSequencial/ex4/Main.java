package a04EstruturaSequencial.ex4;

import java.util.Scanner;

public class Main {
    static void main() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o número do funcionário: ");
        int numeroDoFuncionario = sc.nextInt();
        System.out.print("Digite o número de horas trabalhadas: ");
        int horas = sc.nextInt();
        System.out.print("Digite o valor recebido por hora: ");
        double valorPorHora = sc.nextDouble();
        System.out.println("NUMBER = " + numeroDoFuncionario);
        System.out.println("SALARY =  U$" + (horas * valorPorHora));
        sc.close();

    }
}
