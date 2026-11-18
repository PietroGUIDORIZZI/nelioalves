package a18ProgramacaoFuncional.Lambda.Consumer;


import java.util.ArrayList;
import java.util.List;

public class Main {
    static void main(String[] args) {
        List<Product> list = new ArrayList<>();
        list.add(new Product("TV", 900.00));
        list.add(new Product("Notebook", 1200.00));
        list.add(new Product("Tablet", 450.00));
        list.add(new Product("HD Case", 450.00));
        double factor = 1.1;
        list.forEach(System.out::println);
        System.out.println();
        list.forEach(p -> p.setPrice(p.getPrice() * factor));
        list.forEach(System.out::println);

    }
}
