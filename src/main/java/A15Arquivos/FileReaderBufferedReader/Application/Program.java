package A15Arquivos.FileReaderBufferedReader.Application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Program {
    static void main(String[] args) {

        String path =
                "C:\\dev\\NelioAlves\\nelioAlvesCurso\\src\\main\\java\\A15Arquivos\\FileReaderBufferedReader\\in.txt";



        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line = br.readLine();

            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
