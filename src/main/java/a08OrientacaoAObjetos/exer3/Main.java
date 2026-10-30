package a08OrientacaoAObjetos.exer3;

import java.util.Scanner;

public class Main {

    static void main() {
        Scanner sc = new Scanner(System.in);

        String name = sc.nextLine();
        double nota1 = Double.parseDouble(sc.nextLine());
        double nota2 = Double.parseDouble(sc.nextLine());
        double nota3 = Double.parseDouble(sc.nextLine());
        Aluno aluno = new Aluno(name, nota1,nota2,nota3);
        System.out.printf("FINAL GRADE = %.2f\n", aluno.finalGrade());
        System.out.print((aluno.passed()) ? "PASS\n" : "FAILED\n");
        if(!aluno.passed()){
            System.out.printf("MISSING %.2f POINTS", aluno.missingPoints());
        }
        sc.close();
    }
}
