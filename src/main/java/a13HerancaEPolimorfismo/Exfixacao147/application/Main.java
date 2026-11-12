package a13HerancaEPolimorfismo.Exfixacao147.application;

import a13HerancaEPolimorfismo.Exfixacao147.entities.CompanyTax;
import a13HerancaEPolimorfismo.Exfixacao147.entities.IndividualTax;
import a13HerancaEPolimorfismo.Exfixacao147.entities.Tax;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of tax payers: ");
        int n = Integer.parseInt(sc.nextLine());
        List<Tax> taxes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.println("Tax payer #" + (i + 1) + " data:");
            System.out.print("Individual or company (i/c)? ");
            String ans = sc.nextLine();
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Annual income: ");
            double income = Double.parseDouble(sc.nextLine());
            if(ans.equals("i")){
                System.out.print("Health expenditures: ");
                double healthExpenditures = Double.parseDouble(sc.nextLine());
                taxes.add(new IndividualTax(name, income, healthExpenditures));
                
            }else{
                System.out.print("Number of employees: ");
                int numberOfEmployees = Integer.parseInt(sc.nextLine());
                taxes.add(new CompanyTax(name, income, numberOfEmployees));
            }

        }
        System.out.println();
        System.out.println("TAXES PAID:");
        double total = 0;
        for (Tax t : taxes) {
            total += t.Tax();
            System.out.println(t.getName() + ": $ " + String.format("%.2f",
                                                                    t.Tax() ));
        }
        System.out.println();
        System.out.println("TOTAL TAXES: $ " + String.format("%.2f",total));

        sc.close();
    }
}
