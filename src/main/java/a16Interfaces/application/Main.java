package a16Interfaces.application;

import a16Interfaces.entity.Contract;
import a16Interfaces.entity.Installment;
import a16Interfaces.services.ContractService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        DateTimeFormatter fmt =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Entre os dados do contrato:");
        System.out.print("Número: ");
        int number = sc.nextInt();
        System.out.print("Data (dd/MM/yyyy): ");
        LocalDate date = LocalDate.parse(sc.next(),
                                         fmt);

        System.out.print("Valor do contrato: ");
        double totalValue = sc.nextDouble();

        Contract obj = new Contract(number,
                                    date,
                                    totalValue);

        System.out.print("Entre com o número de "
                                 + "parcelas: ");

        int n = sc.nextInt();

        ContractService contractService =
                new ContractService(null);
        contractService.processContract(obj,
                                        2);

        System.out.println("Parcelas:");
        for (Installment installment :
                obj.getInstallmentList()) {
            System.out.println(installment);
        }
        sc.close();
    }
}
