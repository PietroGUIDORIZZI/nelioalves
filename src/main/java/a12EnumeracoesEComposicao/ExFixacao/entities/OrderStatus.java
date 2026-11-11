package a12EnumeracoesEComposicao.ExFixacao.entities;

public enum OrderStatus {
    PENDING_PAYMENT(0),
    PROCESSING(1),
    SHIPPED(2),
    DELIVERED(3);

    private final int index;

    OrderStatus(int index) {
        this.index = index;
    }

    public int index(){
        return index;
    }
}
