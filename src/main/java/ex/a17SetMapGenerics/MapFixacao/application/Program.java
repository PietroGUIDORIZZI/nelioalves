package ex.a17SetMapGenerics.MapFixacao.application;

import ex.a17SetMapGenerics.MapFixacao.entities.Politician;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Program {
    static void main(String[] args) {
        Map<String, Integer> election = new HashMap<>();
        Scanner sc = new Scanner(System.in);


        System.out.print("Enter full file path: ");
        String path = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                String name = fields[0];
                int votes = Integer.parseInt(fields[1]);
                if(election.containsKey(name)){
                    election.put(name, votes + election.get(name));
                }else {
                    election.put(name, votes);
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        List<Politician> politicianList = new ArrayList<>();

        for (String key : election.keySet()){
            politicianList.add(new Politician(key, election.get(key)));
        }

        Collections.sort(politicianList,
                         Comparator.comparingInt(Politician::getVotes).reversed());
        for (Politician p : politicianList) {
            System.out.println(p);
        }
    }
}
