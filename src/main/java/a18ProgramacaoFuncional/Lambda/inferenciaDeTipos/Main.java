package a18ProgramacaoFuncional.Lambda.inferenciaDeTipos;

import a18ProgramacaoFuncional.Lambda.funcaoDePrimeiraOrdem.Product;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static void main(String[] args) {
        List<Product> list = new ArrayList<>();
        list.add(new Product("TV", 900.00));
        list.add(new Product("Notebook", 1200.00));
        list.add(new Product("Tablet", 450.00));

        list.sort((p1, p2) -> p1.getName().toUpperCase().compareTo(p2.getName().toUpperCase()));

        list.forEach(System.out::println);
    }
}
