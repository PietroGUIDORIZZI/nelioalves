package ex.a18ProgramacaoFuncional.Lambda.Streams.StreamsExResolvido.application;

import ex.a18ProgramacaoFuncional.Lambda.Streams.StreamsExResolvido.entities.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Program {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String path = sc.nextLine();

        List<Product> productList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                Product p = new Product(fields[0],
                                        Double.parseDouble(fields[1]));
                productList.add(p);
                line = br.readLine();
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        double avg =
                productList.stream()
                        .map(product -> product.getPrice())
                        .reduce(0.0, (x, y) -> x + y) / productList.size();

        System.out.println("Average price: " + String.format("%.2f", avg));

        Comparator<String> comp = (s1, s2) -> s1.compareToIgnoreCase(s2);
        List<String> names = productList.stream()
                .filter(p -> p.getPrice() < avg)
                .map(product -> product.getName())
                .sorted(comp.reversed())
                .collect(Collectors.toList());

        names.forEach(System.out::println);
        sc.close();
    }
}
