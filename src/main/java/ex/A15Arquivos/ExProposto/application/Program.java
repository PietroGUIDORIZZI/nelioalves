package ex.A15Arquivos.ExProposto.application;

import ex.A15Arquivos.ExProposto.entities.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Program {
    static void main(String[] args) {
        String src =
                "C:\\dev\\NelioAlves\\nelioAlvesCurso\\src\\main\\java\\A15Arquivos\\ExProposto\\source.txt";
        String srcPath =
                "C:\\dev\\NelioAlves\\nelioAlvesCurso\\src\\main\\java\\A15Arquivos\\ExProposto\\";

        List<Product> products = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(src))) {
            String line = br.readLine();
            while (line != null){
                String[] val = line.split(",");
                Product p = new Product(val[0], Double.parseDouble(val[1]),
                                        Integer.parseInt(val[2]) );
                products.add(p);
                line = br.readLine();
            }
            boolean success = new File(srcPath + "\\out").mkdir();
        } catch(IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        String out =
                "C:\\dev\\NelioAlves\\nelioAlvesCurso\\src\\main\\java" +
                        "\\A15Arquivos\\ExProposto\\out\\summary.csv";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(out))) {
            for (Product p : products) {
                bw.write(p.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
