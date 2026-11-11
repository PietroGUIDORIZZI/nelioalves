package a12EnumeracoesEComposicao.ExFixacao.entities;

public class OrderItem {
    private Integer quantity;
    private Double price;
    private Order order;
    //PRODUCT TEM QUE ESTAR AQUI!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private Product product;

    public OrderItem() {
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OrderItem(Integer quantity, Double price, Order order,
                     Product product) {
        this.quantity = quantity;
        this.price = price;
        this.order = order;
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Order getOrder() {
        return order;
    }


    public Double subTotal(){
        return quantity * price;
    }
    public String toString(){
        return getProduct().getName()
                + ", $"
                + String.format("%.2f", price)
                + ", Quantity: "
                + quantity
                + ", Subtotal: $"
                + String.format("%.2f", subTotal());
    }

}
