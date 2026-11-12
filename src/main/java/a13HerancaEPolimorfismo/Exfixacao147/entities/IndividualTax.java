package a13HerancaEPolimorfismo.Exfixacao147.entities;

public class IndividualTax extends Tax {
    private Double healthExpenditures;

    public IndividualTax(String name, Double annualIncome,
                         Double healthExpenditures) {
        super(name, annualIncome);
        this.healthExpenditures = healthExpenditures;
    }

    @Override
    public double Tax() {
        double tax = 0;
        if(getAnnualIncome() > 20000.00){
            tax = getAnnualIncome() * 0.25 - (healthExpenditures * 0.50);
        }else{
            tax = getAnnualIncome() * 0.15 - (healthExpenditures * 0.50);
        }
        return tax;
    }
}
