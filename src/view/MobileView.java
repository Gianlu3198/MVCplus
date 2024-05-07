package view;

import java.util.ArrayList;

import model.Mobile;

public class MobileView 
{
    public String render(Mobile m)
    {
        String template =   "Mobile [modello], costruito in lussuoso  [materiale] di colore [colore]\n" +
                            "Altezza: [altezza] metri \n"                                               +
                            "Larghezza: [larghezza] metri\n"                                            +
                            "Profondità: [profondita] metri\n"                                          +
                            "Peso: [peso] kg"                                                     ;

        template = m.rimpiazzaPlaceholder(template);
        return template;
    }

    public String renderMany(ArrayList<Mobile> mobili)
    {
        String res = "";

        for (int i = 0; i < mobili.size(); i++) 
        {
            res+= render(mobili.get(i))+"\n\n";
        }

        return res;
    }
}
