package a16Interfaces.solucaoParte1.service;

import a16Interfaces.solucaoParte1.model.entities.CarRental;
import a16Interfaces.solucaoParte1.model.entities.Invoice;

import java.time.Duration;

public class RentalService {
    private Double pricePerHour;
    private Double pricePerDay;

    private BrazilTaxService taxService;


    public RentalService(Double pricePerHour, Double pricePerDay,
                         BrazilTaxService taxService) {
        this.pricePerHour = pricePerHour;
        this.pricePerDay = pricePerDay;
        this.taxService = taxService;
    }

    public void processInvoice(CarRental cr ) {
        double minutes =
                Duration.between(cr.getStart(), cr.getFinish()).toMinutes();
        double hours = minutes / 60.0;

        double basicPayment;

        if (hours <= 12.00) {
            basicPayment = pricePerHour * Math.ceil(hours);
        }
        else {
            basicPayment = pricePerDay * Math.ceil(hours / 24);
        }

        double tax = taxService.tax(basicPayment);

        cr.setInvoice(new Invoice(basicPayment, tax));
    }


}
