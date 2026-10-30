package a09ConstrutoresThisOverLoadEncapsuling.Banco;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter account number: ");
        int acN = Integer.parseInt(sc.nextLine());
        System.out.print("Enter account holder: ");
        String acName = sc.nextLine().trim();
        System.out.print("Is there an initial deposit (y/n)? ");
        String ans = sc.nextLine().toLowerCase(Locale.ROOT);
        char a = ans.charAt(0);
        Conta cc = null;
        if(a == 'y'){
            System.out.print("\nEnter initial deposit value: ");
            double iDeposit = Double.parseDouble(sc.nextLine());
            cc = new Conta(acName, iDeposit, acN);
            System.out.println();
            System.out.printf("Account %d, Holder: %s, Balance: $ %.2f\n", cc.getNumber(), cc.getName(), cc.getBalance());
            System.out.println();
        }else{
            System.out.println("Account data: ");
            cc = new Conta(acName, acN);
            System.out.printf("Account %d, Holder: %s, Balance: $ %.2f\n", cc.getNumber(), cc.getName(), cc.getBalance());
            System.out.println();
        }
        System.out.print("Enter a deposit value: ");
        double deposit = Double.parseDouble(sc.nextLine());
        cc.deposit(deposit);
        System.out.println("Updated account data:");
        System.out.printf("Account %d, Holder: %s, Balance: $ %.2f", cc.getNumber(), cc.getName(), cc.getBalance());
        System.out.println();

        System.out.print("Enter a withdraw value: ");
        double withdraw = Double.parseDouble(sc.nextLine());
        cc.cashout(withdraw);
        System.out.println("Updated account data:");
        System.out.printf("Account %d, Holder: %s, Balance: $ %.2f", cc.getNumber(), cc.getName(), cc.getBalance());
        System.out.println();







        sc.close();


    }
}
