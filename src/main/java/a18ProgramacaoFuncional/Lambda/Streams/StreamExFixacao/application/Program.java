package a18ProgramacaoFuncional.Lambda.Streams.StreamExFixacao.application;

import a18ProgramacaoFuncional.Lambda.Streams.StreamExFixacao.entities.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Program {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter full file path: ");
        String path = sc.nextLine();
        System.out.print("Enter salary: ");
        double max = Double.parseDouble(sc.nextLine());

        List<Employee> employees = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();

            while (line != null) {
                String[] fields = line.split(",");
                Employee e = new Employee(fields[0], fields[1],
                                          Double.parseDouble(fields[2]));
                employees.add(e);
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Email of people whose salary is more than $" +
                                   String.format("%.2f", max) + ":");

        Stream<String> st = employees.stream()
                .filter(employee -> employee.getSalary() > max)
                .map(e -> e.getEmail())
                .sorted();

        st.forEachOrdered(System.out::println);

        System.out.print(
                "Sum of salary of people whose name starts with 'M': ");

        System.out.println("$" + String.format("%.2f", employees.stream()
                                   .filter(employee -> employee.getName()
                                           .startsWith(
                                                   "M"))
                                   .map(e -> e.getSalary())
                                   .reduce(0.0, (a
                                           , b) -> a + b)));

        System.out.println();

        sc.close();
    }
}
