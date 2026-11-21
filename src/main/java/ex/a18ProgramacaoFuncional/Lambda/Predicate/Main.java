package ex.a18ProgramacaoFuncional.Lambda.Predicate;

import ex.a18ProgramacaoFuncional.Lambda.inferenciaDeTipos.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Main {

    public static class Predicated implements Predicate<Product> {
        @Override
        public boolean test(Product product) {
            return product.getPrice() > 600.00;
        }
    }

    static void main(String[] args) {
        List<Product> list = new ArrayList<>();
        list.add(new Product("TV", 900.00));
        list.add(new Product("Notebook", 1200.00));
        list.add(new Product("Tablet", 450.00));
        list.add(new Product("Tabletv", 550.00));
        double min = 860.00;
        list.removeIf(product -> product.getPrice() >= min);
        list.forEach(System.out::println);
    }
}
