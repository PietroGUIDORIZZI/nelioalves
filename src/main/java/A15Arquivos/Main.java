package A15Arquivos;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

        static void main(String[] args) {
                File file = new File(
                        "C:\\Users\\pietr\\OneDrive"
                                + "\\Área de Trabalho\\New "
                                + "folder\\NelioAlves\\nelioA"
                                + "lvesCurso\\src\\main\\java"
                                + "\\A15Arquivos");
                Scanner sc = null;
                try {
                        sc = new Scanner(file);
                        while (sc.hasNextLine()) {
                                System.out.println(sc.nextLine());
                        }
                } catch (IOException e) {
                        System.out.println(
                                "Error: " + e.getMessage());
                } finally {
                        if (sc != null) {
                                sc.close();
                        }
                }
        }
}
