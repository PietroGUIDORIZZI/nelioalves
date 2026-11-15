package a16Interfaces.exFixacao.application;

import a16Interfaces.exFixacao.entities.Contract;
import a16Interfaces.exFixacao.services.ContractService;
import a16Interfaces.exFixacao.services.PaypalPayment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Program {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Entre os dados do contrato:");
        System.out.print("Número: ");
        int number = Integer.parseInt(sc.nextLine());

        System.out.print("Data (dd/MM/yyyy): ");
        LocalDate date = LocalDate.parse(sc.nextLine(), dtf);

        System.out.print("Valor do contrato: ");
        double contractValue = Double.parseDouble(sc.nextLine());

        Contract obj = new Contract(number, date, contractValue);
        
        System.out.print("Entre com o número de parcelas: ");
        int installments = Integer.parseInt(sc.nextLine());

        ContractService contractService =
                new ContractService(new PaypalPayment()) ;
        contractService.processContract(obj, installments);

        System.out.println("Parcelas:");

        System.out.println(obj.toString());



        sc.close();
    }
}
