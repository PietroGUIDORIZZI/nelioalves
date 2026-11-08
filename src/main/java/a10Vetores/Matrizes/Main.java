package a10Vetores.Matrizes;

import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Type two integers to initialize the matrix:");
        int m = sc.nextInt();
        int n = sc.nextInt();

        int[][] mn = new int[m][n];

        System.out.println("Fill the matrix:");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("Position [" + i + "][" + j + "]: ");
                mn[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < m; i++) {
            System.out.println();
            for (int j = 0; j < n; j++) {
                System.out.print(String.format("[%3d  ]", mn[i][j]) + " ");
            }
        }
        System.out.println();
        System.out.println();
        System.out.print("Pick a number: ");
        int x = sc.nextInt();
        System.out.println();
        int found = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mn[i][j] == x) {

                    System.out.println("Position: [" + i + "][" + j + "]");
                    if (j > 0) {
                        System.out.println("Left: " + mn[i][j - 1]);
                    }
                    if (j < n -1) {
                        System.out.println("Right: " + mn[i][j + 1]);
                    }
                    if (i > 0) {
                        System.out.println("Up: " + mn[i - 1][j]);
                    }
                    if (i < m -1) {
                        System.out.println("Down: " + mn[i + 1][j]);
                    }
                    found = 1;
                }
            }
        }
        if(found == 0){
            System.out.println("X is not on this matrix.");
        }

        sc.close();
    }
}
