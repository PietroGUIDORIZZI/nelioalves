package ex.A15Arquivos.FileWritterBufferedWriter.Application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Program {
    static void main(String[] args) {
        String[] lines = new String[]{"Good morning", "Good afternoon",
                                      "Good Night"};

        String path =
                "C:\\dev\\NelioAlves\\nelioAlvesCurso\\src\\main\\java" +
                        "\\A15Arquivos\\FileWritterBufferedWriter\\OUT.txt";
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(path, true))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
