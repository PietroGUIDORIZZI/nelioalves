package a13HerancaEPolimorfismo.exFixacao144.entities;

public class ImportedProduct extends Product {
    private Double customsFee;

    public ImportedProduct(Double price, String name, Double customsFee) {
        super(price, name);
        this.customsFee = customsFee;
    }

    @Override
    public String priceTag(){
        return getName() + " $ " + String.format("%.2f", totalPrice()) + " " +
                "(Customs fee: $ " + String.format("%.2f", getCustomsFee()) + ")";
    }

    public Double totalPrice(){
        return getPrice() + customsFee;
    }

    public Double getCustomsFee() {
        return customsFee;
    }

    public void setCustomsFee(Double customsFee) {
        this.customsFee = customsFee;
    }
}
