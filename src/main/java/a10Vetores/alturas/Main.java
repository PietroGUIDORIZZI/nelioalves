package a10Vetores.alturas;

import java.util.Scanner;

public class Main   {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Quantas pessoas serao digitadas? ");
        int n = Integer.valueOf(sc.nextInt());
        sc.nextLine();
        String[] nomes = new String[n];
        int[] idades = new int[n];
        double[] alturas = new double[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Dados da " + (i+1) + "a pessoa: ");
            System.out.print("Nome: ");
            nomes[i] = sc.nextLine();
            System.out.print("Idade: ");
            idades[i] = Integer.valueOf(sc.nextLine());
            System.out.print("Altura: ");
            alturas[i] = Double.valueOf(sc.nextLine());
        }
        imprimirAlturaMedia(alturas);
        imprimirPorcentagemENomesDePessoasComMenosDe16Anos(idades, nomes);

        sc.close();
    }

    public static void imprimirAlturaMedia(double alturas[]) {
        double sum = 0;
        int count = 0;

        for (int i = 0; i < alturas.length; i++) {
            sum += alturas[i];
            count++;
        }
        System.out.println(String.format("Altura média: %.2f", sum / count));

    }

    public static void imprimirPorcentagemENomesDePessoasComMenosDe16Anos(int idades[], String names[]){
        int count= 0;
        int[] pessoas = new int[idades.length];
        for (int i = 0; i < idades.length; i++) {
            if(idades[i] < 16) {
                pessoas[i] = i;
                count++;
            }
            else{
                pessoas[i] = -1;
            }
        }
        System.out.println(String.format("Pessoas com menos de 16 anos: %" +
                                                 ".1f%%",
                                         (double) count / idades.length * 100.0));
        for (int i = 0; i < names.length; i++) {
            if(pessoas[i] != -1) {
                System.out.println(String.format("%s", names[i]));
            }
        }
    }
}
