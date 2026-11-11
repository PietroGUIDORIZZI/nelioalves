package a13HerancaEPolimorfismo.exResolvido143.application;

import a13HerancaEPolimorfismo.exResolvido143.entities.Employee;
import a13HerancaEPolimorfismo.exResolvido143.entities.OutsourcedEmployee;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Employee> employees = new ArrayList<>();

        System.out.print("Enter the number of employees: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("Employee #" + (i + 1) + " data:");
            System.out.print("Outsourced (y/n)? ");
            String ans = sc.nextLine();
            if (ans.equals("y")) {
                System.out.print("Name: ");
                String name = sc.nextLine();
                System.out.print("Hours: ");
                int hours = Integer.parseInt(sc.nextLine());
                System.out.print("Value per hour: ");
                double valuePerHour = Double.parseDouble(sc.nextLine());
                System.out.print("Additional charge: ");
                double additionalCharge = Double.parseDouble(sc.nextLine());
                OutsourcedEmployee outEmp = new OutsourcedEmployee(name,
                                                                   hours,
                                                                   valuePerHour,
                                                                   additionalCharge);
                employees.add(outEmp);
            }
            else {
                System.out.print("Name: ");
                String name = sc.nextLine();
                System.out.print("Hours: ");
                int hours = Integer.parseInt(sc.nextLine());
                System.out.print("Value per hour: ");
                double valuePerHour = Double.parseDouble(sc.nextLine());

                Employee emp = new Employee(name,
                                            hours,
                                            valuePerHour);
                employees.add(emp);
            }
        }

        System.out.println("PAYMENTS:");
        for (Employee e : employees) {
            System.out.println(e.getName() + " - $ " + String.format("%.2f",
                                                                     e.payment()));
        }
        sc.close();
    }
}
