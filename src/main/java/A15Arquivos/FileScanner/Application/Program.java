package A15Arquivos.FileScanner.Application;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Program {
    
    static void main(String[] args) {

        File file = new File("C:\\dev\\NelioAlves\\nelioAlvesCurso\\src\\main" +
                                     "\\java" +
                                     "\\A15Arquivos\\FileScanner\\in.txt");
        Scanner sc = null;

        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }
}
