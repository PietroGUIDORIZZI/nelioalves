package a05EstruturasCondicionais.ex5;

import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o código do produto: ");
        int cod = sc.nextInt();
        System.out.print("Digite a quantidade do produto: ");
        int qtd = sc.nextInt();
        double price = 0;
        switch(cod){
            case 1:
                price = qtd * 4.00;
                break;
            case 2:
                price = qtd * 4.50;
                break;
            case 3:
                price = qtd * 5.00;
                break;
            case 4:
                price = qtd * 2.00;
                break;
            case 5:
                price = qtd * 1.50;
                break;
            default:
                price = 0;
        }
        System.out.printf("Total: R$ %.2f", price);
    }
}
