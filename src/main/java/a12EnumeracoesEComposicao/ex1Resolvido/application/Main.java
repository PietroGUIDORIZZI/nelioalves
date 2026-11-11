package a12EnumeracoesEComposicao.ex1Resolvido.application;

import a12EnumeracoesEComposicao.ex1Resolvido.entity.Department;
import a12EnumeracoesEComposicao.ex1Resolvido.entity.HourContract;
import a12EnumeracoesEComposicao.ex1Resolvido.entity.Worker;
import a12EnumeracoesEComposicao.ex1Resolvido.entity.enums.WorkerLevel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {
    static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sfdMY = new SimpleDateFormat("MM/yyyy");
        System.out.print("Enter department's name: ");
        Department department = new Department(sc.nextLine());
        Worker worker = new Worker();
        worker.setDepartment(department);
        System.out.println("Enter worker data:");
        System.out.print("Name: ");
        worker.setName(sc.nextLine());
        System.out.print("Level: ");
        worker.setWorkerLevel(WorkerLevel.valueOf(sc.nextLine()));
        System.out.print("Base salary: ");
        worker.setBaseSalary(Double.valueOf(sc.nextLine()));
        System.out.print("How many contracts to this worker? ");
        int n = Integer.valueOf(sc.nextLine());
        for (int i = 0; i < n; i++) {
            System.out.println("Enter contract #" + (i + 1) + " data:");
            System.out.print("Date (DD/MM/YYYY): ");
            Date contractDate = sdf.parse(sc.next());
            System.out.print("Value per hour: ");
            double valuePerHour = sc.nextDouble();
            System.out.print("Duration (hours): ");
            int hours = sc.nextInt();
            HourContract hc = new HourContract(contractDate, valuePerHour,
                                               hours);
            worker.addContract(hc);
        }
        System.out.println();
        System.out.print("Enter month and year to calculate income (MM/YYYY):" +
                                 " ");
        String yearMonth = sc.next();
        String[] date = yearMonth.split("/");
        System.out.println("Name: " + worker.getName());
        System.out.println("Department: " + worker.getDepartment().getName());
        System.out.println("Income for " + yearMonth + ": " + String.format(
                "%.2f",worker.income(Integer.parseInt(date[1]),
                                  Integer.parseInt(date[0]))));
        sc.close();
    }
}
