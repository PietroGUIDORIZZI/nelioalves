package a13HerancaEPolimorfismo.exFixacao144.entities;

public class Product {
    private String name;
    private Double price;

    public Product() {
    }
    public Product(Double price, String name) {
        this.price = price;
        this.name = name;
    }

    public String priceTag(){
        return name + " $ " + String.format("%.2f", price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
