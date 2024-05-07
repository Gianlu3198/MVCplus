package controller.main;

import java.util.Scanner;

import controller.handlers.Handler;

public class Main 
{
    public static void main(String[] args) 
    {
        Scanner term = new Scanner(System.in);
        String cmd;

        do 
        {
            System.out.println("Inserisci comando");
            cmd = term.nextLine().toLowerCase();

            switch (cmd) 
            {
                case "creamobile":
                    Handler.creaMobile();
                break;
                case "quit":
                    System.out.println("Programma terminato");
                break;
                default:
                    System.out.println("Comando non riconosciuto");
                break;
            }
        } while (!cmd.equals("quit"));
    }
}
