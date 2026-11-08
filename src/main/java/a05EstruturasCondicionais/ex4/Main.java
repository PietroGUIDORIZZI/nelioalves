package a05EstruturasCondicionais.ex4;

import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite a hora inicial e a hora final: ");
        int horaInicial = sc.nextInt();
        int horaFinal = sc.nextInt();

        int duracao = 0;

        if(horaInicial == horaFinal) {
            duracao = 24;
        }else if(horaInicial < horaFinal){
            duracao = horaFinal - horaInicial;
        }else{
            duracao = 24 + horaFinal - horaInicial;
        }

        System.out.println("O JOGO DUROU " + duracao + " HORA(S)");
    }
}
