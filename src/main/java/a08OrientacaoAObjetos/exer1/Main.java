package a08OrientacaoAObjetos.exer1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter rectangle witdth and height:");
        double w = Double.valueOf(sc.nextLine());
        double h = Double.valueOf(sc.nextLine());
        Rectangle rect = new Rectangle(w, h);
        System.out.printf("Area = %.2f\n", rect.area() );
        System.out.printf("Perimeter = %.2f\n", rect.perimeter() );
        System.out.printf("Diagonal = %.2f\n", rect.diagonal() );
    }
}
