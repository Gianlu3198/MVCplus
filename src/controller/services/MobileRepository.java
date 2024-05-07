package controller.services;

import java.util.ArrayList;

import com.generation.db.Database;

import model.Mobile;

//UN OGGETTO CHE OFFRE AL RESTO DEL PROGRAMMA UNA DETERMINATA FUNZIONALITÀ
//Fa parte, insieme all'HANDLER, della Business Logic

//REPOSITORY o D.A.O. (Data Access Object)
//Oggetti che si occupano dell'interazione con il database per specifiche entità
public class MobileRepository 
{
    Database db = new Database("config.txt");
    //selectAll
    //insert
    //update 
    //delete

    //legga tutti i mobili da Database
    public ArrayList<Mobile> selectAll()
    {
        ArrayList<String> tutteRighe = db.select("SELECT * FROM mobili");
        ArrayList<Mobile> res = new ArrayList<>();
        //convertire ogni riga in un mobile e aggiungerlo ad una lista di mobili da restituire

        //for each
        //è un ciclo specializzato per lo scorrimento
        //lo UTILIZZEREMO SEMPRE quando vogliamo eseguire la stessa operazione su tutti gli elementi
        //di una lista o vettore
        // for (int i = 0; i < tutteRighe.size(); i++) 
        // {
        //     String singolaRiga = tutteRighe.get(i);
        //     Mobile m = new Mobile(singolaRiga);   
        //     res.add(m); 
        // }

        //for
        //TipoSingoloElemento nomeSingoloElemento      :       lista
        for(String               singolaRiga           :       tutteRighe)
        //per ogni String che chiamiamo singolaRiga    dento   lista tutteRighe
        {
            Mobile m = new Mobile(singolaRiga);   
            res.add(m); 
        }

        return res;
    }

    public ArrayList<Mobile> selectWhere(String condizione)
    {
        ArrayList<String> tutteRighe = db.select("SELECT * FROM mobili WHERE "+condizione);
        ArrayList<Mobile> res = new ArrayList<>();
        
        for(String singolaRiga : tutteRighe)
        {
            Mobile m = new Mobile(singolaRiga);   
            res.add(m); 
        }

        return res;
    }

    public Mobile selectById(int id)
    {
        ArrayList<Mobile> res =  selectWhere("id="+id);
        
        if(res.size()==0)
            return null;
        
        return res.get(0);
    }

    public boolean insert(Mobile daInserire)
    {
        if(!daInserire.isValid())
            return false;

        db.insert(daInserire.generaInsert());

        return true;
    }

    public boolean insertAll(ArrayList<Mobile> listaDaInserire)
    {

        for(Mobile daInserire : listaDaInserire)
            if(!daInserire.isValid())
                return false;//termina il metodo

        for(Mobile daInserire : listaDaInserire)
            db.insert(daInserire.generaInsert());

        return true;
    }

    public boolean update(Mobile daModificare)
    {
        if(!daModificare.isValid())
            return false;

        db.update(daModificare.generaUpdate());

        return true;
    }

    public boolean updateAll(ArrayList<Mobile> listadaModificare)
    {

        for(Mobile daModificare : listadaModificare)
            if(!daModificare.isValid())
                return false;//termina il metodo

        for(Mobile daModificare : listadaModificare)
            db.update(daModificare.generaUpdate());

        return true;
    }

    public void deleteByObject(Mobile daCancellare)
    {
        db.delete(daCancellare.generaDelete());
    }

    public void deleteById(int id)
    {
        db.delete("DELETE FROM mobili WHERE id="+id);
    }

    /**
     * Restiuisce l'id più alto nella tabella mobile (id dell'ultimo mobile inserito)
     * @return
     */
    public int maxId()
    {
        return Integer.parseInt( db.select("SELECT id FROM mobili ORDER BY id DESC limit 1").get(0).split(",")[0]);
    }
}
