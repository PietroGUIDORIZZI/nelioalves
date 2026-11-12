package a13HerancaEPolimorfismo.Exfixacao147.entities;

public class CompanyTax extends Tax {
    private Integer employees;

    public CompanyTax(String name, Double annualIncome, Integer employees) {
        super(name, annualIncome);
        this.employees = employees;
    }

    @Override
    public double Tax() {
        double tax = 0;
        if (employees > 10) {
            tax = getAnnualIncome() * 0.14;
        }else{
            tax = getAnnualIncome() * 0.16;
        }
        return tax;
    }
}
