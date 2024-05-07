package model;

public class Mobile 
{
    public Integer id;
    public String modello,materiale,colore;
    public Double larghezza,altezza,profondita,peso;

    //COSTRUTTORI
    //VUOTO - COMPLETO - SENZA ID - CSV
    public Mobile(){}


    public Mobile
    (
        Integer id, String modello, String materiale, 
        String colore,Double larghezza, 
        Double altezza,Double profondita, Double peso
    ) 
    {
        this.id = id;
        this.modello = modello;
        this.materiale = materiale;
        this.colore = colore;
        this.larghezza = larghezza;
        this.altezza = altezza;
        this.profondita = profondita;
        this.peso = peso;
    }

    public Mobile
    (
        String modello, String materiale, 
        String colore,Double larghezza, 
        Double altezza,Double profondita, Double peso
    ) 
    {
        this.modello = modello;
        this.materiale = materiale;
        this.colore = colore;
        this.larghezza = larghezza;
        this.altezza = altezza;
        this.profondita = profondita;
        this.peso = peso;
    }

    public Mobile
    (
        String csv
    ) 
    {
        String[] parti = csv.split(",");
        this.id = Integer.parseInt(parti[0]);
        this.modello = parti[1];
        this.materiale = parti[2];
        this.colore = parti[3];
        this.larghezza = Double.parseDouble(parti[4]);
        this.altezza = Double.parseDouble(parti[5]);
        this.profondita = Double.parseDouble(parti[6]);
        this.peso = Double.parseDouble(parti[7]);
    }
    
    //METODI STANDARD
    //i 3 genera query
    //isValid
    //rimpiazzaPlaceholder

    public String generaInsert()
    {
        String template = "INSERT INTO mobili (modello,materiale,colore,larghezza,altezza,profondita,peso) VALUES "+
                           "('[modello]','[materiale]','[colore]',[larghezza],[altezza],[profondita],[peso])";

        template =  rimpiazzaPlaceholder(template);

        return template;
    }

    public String generaUpdate()
    {
        String template = "UPDATE mobili SET modello='[modello]',materiale='[materiale]',colore='[colore]',"+
                           " larghezza=[larghezza],altezza=[altezza],profondita=[profondita],peso=[peso] "+
                           " WHERE id=[id]";
                           

        template =  rimpiazzaPlaceholder(template);
                    

        return template;
    }

    public String generaDelete()
    {
        return "DELETE FROM mobili WHERE id="+id;
    }

    public String rimpiazzaPlaceholder(String template)
    {
        template =  template
        .replace("[modello]", modello+"")
        .replace("[materiale]", materiale+"")
        .replace("[colore]", colore+"")
        .replace("[larghezza]", larghezza+"")
        .replace("[altezza]", altezza+"")
        .replace("[profondita]", profondita+"")
        .replace("[peso]", peso+""); 

        if(template.contains("[id]"))
            template.replace("[id]", id+"");

        return template;
    }

    public boolean isValid()
    {
        return  stringaValida(modello)      &&
                stringaValida(materiale)    &&
                stringaValida(colore)       &&
                numeroValido(larghezza)     &&
                numeroValido(altezza)       &&
                numeroValido(profondita)    &&
                numeroValido(peso)          &&
                (id==null || id>0)           ;
    }

    //metodini di supporto
    public boolean numeroValido(Double d)
    {
        return d!=null && d>0;
    }

    public boolean stringaValida(String s)
    {
        return s!=null && !s.isBlank();
    }

    //metodi specifici
    public double calcolaPesoSpecifico()
    {
        return peso /(altezza*larghezza*profondita);
    }

    //toString
    //toString è per gli SVILUPPATORI, non per gli utenti
    //serve quando proviamo il programma o facciamo debug
    public String toString() 
    {
        return "Mobile [id=" + id + ", modello=" + modello + ", materiale=" + materiale + ", colore=" + colore
                + ", larghezza=" + larghezza + ", altezza=" + altezza + ", profondita=" + profondita + ", peso=" + peso
                + ", isValid()=" + isValid() + "]";
    }
}
