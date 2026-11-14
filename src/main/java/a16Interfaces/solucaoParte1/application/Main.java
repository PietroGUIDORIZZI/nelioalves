package a16Interfaces.solucaoParte1.application;

import a16Interfaces.solucaoParte1.model.entities.CarRental;
import a16Interfaces.solucaoParte1.model.entities.Vehicle;
import a16Interfaces.solucaoParte1.service.BrazilTaxService;
import a16Interfaces.solucaoParte1.service.RentalService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    static void main() {
        Scanner sc = new Scanner(System.in);


        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        System.out.println("Entre com os dados do aluguel");
        System.out.print("Modelo do carro: ");
        String carModel = sc.nextLine();
        System.out.print("Retirada (dd/MM/yyyy hh:mm): ");
        LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);
        System.out.print("Retorno (dd/MM/yyyy hh:mm): ");
        LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);

        CarRental cr = new CarRental(start, finish, new Vehicle(carModel));

        System.out.print("Entre com o preço por hora: ");
        double pricePerHour = Double.parseDouble(sc.nextLine());
        System.out.print("Entre com o preço por dia: ");
        double pricePerDay = Double.parseDouble(sc.nextLine());

        RentalService rentalService = new RentalService(pricePerHour,
                                                        pricePerDay,
                                                        new BrazilTaxService());
        rentalService.processInvoice(cr);

        System.out.println("FATURA:");
        System.out.println(
                "Pagamento básico: " + String.format("%.2f",
                                                     cr.getInvoice().getBasicPayment()));
        System.out.println("Imposto: " +
                                   String.format("%.2f",
                                                 cr.getInvoice().getTax()));
        System.out.print("Pagamento total: " + String.format(
                "%.2f",cr.getInvoice().getTotalPayment()));
        
        sc.close();
    }

}
