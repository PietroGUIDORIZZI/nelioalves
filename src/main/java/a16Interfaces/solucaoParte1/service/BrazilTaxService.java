package a16Interfaces.solucaoParte1.service;

public class BrazilTaxService {
    public double tax(double amount) {
        if (amount <= 100.00) {
            return amount * .2;
        }
        else {
            return amount * .15;
        }
    }
}
