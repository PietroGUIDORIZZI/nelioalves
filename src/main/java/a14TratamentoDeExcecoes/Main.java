package a14TratamentoDeExcecoes;

import java.util.Scanner;

public class Main {

    static void main(String[] args) throws StupidLogicException {
        Scanner sc = new Scanner(System.in);
        Account ac = new Account(8415,
                "Pietro Guidorizzi",
                50.0, 41.0);
        ac.withdraw(52);
        System.out.println(ac);
            }
}
