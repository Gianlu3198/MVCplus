package controller.handlers;

import java.util.Scanner;

import controller.services.MobileRepository;
import model.Mobile;
import view.MobileView;

public class Handler 
{
    //PROPRIETÀ STATIC tutti i servizi
    static Scanner term = new Scanner(System.in);//servizio interazione console
    static MobileRepository repo = new MobileRepository();//servizio interazione db
    static MobileView view = new MobileView();//servizio grafico mobili

    public static void creaMobile()
    {
        //1) Input utente
        Mobile m = new Mobile();
        System.out.println("Inserisci Modello");
        m.modello = term.nextLine();
        System.out.println("Inserisci Materiale");
        m.materiale = term.nextLine();
        System.out.println("Inserisci Colore");
        m.colore = term.nextLine();
        System.out.println("Inserisci Larghezza");
        m.larghezza = Double.parseDouble(term.nextLine());
        System.out.println("Inserisci Altezza");
        m.altezza = Double.parseDouble(term.nextLine());
        System.out.println("Inserisci Profondità");
        m.profondita = Double.parseDouble(term.nextLine());
        System.out.println("Inserisci Peso");
        m.peso = Double.parseDouble(term.nextLine());

        //2) Utilizziamo i servizi necessari
        boolean inserimentoRiuscito = repo.insert(m);

        if(!inserimentoRiuscito)
        {
            System.out.println("Il mobile non è valido, mi rifiuto di inserirlo");
        }
        else
        {
            m.id = repo.maxId();

            //FACCIO GRAFICARE
            System.out.println("Inserimento Riuscito, ecco il mobile che hai inserito");
            System.out.println(view.render(m));
        }
    }
}
