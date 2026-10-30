package a08OrientacaoAObjetos.exer2;

import java.util.Scanner;

public class Main {

    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Gross Salary: ");
        double salary = Double.parseDouble(sc.nextLine());
        System.out.print("Tax: ");
        double tax = Double.parseDouble(sc.nextLine());
        System.out.println();
        Employee e = new Employee(name, salary, tax);

        System.out.printf("Employee: %s, $ %.2f\n", e.getName(), e.netSalary());
        System.out.print("\nWhich percentage to increase salary? ");
        double percentage = Double.parseDouble(sc.nextLine());
        e.increaseSalary(percentage);
        System.out.println();
        System.out.printf("Updated data: %s, $ %.2f\n", e.getName(), e.netSalary());
    }
}
