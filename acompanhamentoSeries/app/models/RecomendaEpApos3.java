package models;

import javax.persistence.Entity;
import java.util.List;

/**
 * Created by Bruno on 11/02/2015.
 */
@Entity
public class RecomendaEpApos3 extends Recomendacao{

    @Override
    public String getProximoEpisodio(Temporada temporada){
        List<Episodio> episodios = temporada.getEpisodios();

        String result = episodios.get(0).getNumero() + " - " + episodios.get(0).getNome();
        int proxEp = -1;
        int aux = 0;
        boolean flag = false;
        for (int i = 0; i <= temporada.getQtdEpisodios() - 1; i++) {
            if(!episodios.get(i).isAssistido()) {
                proxEp = i;
                flag = true;
                result = episodios.get(proxEp).getNumero() + " - " + episodios.get(proxEp).getNome();
            }else if (episodios.get(i).isAssistido() && flag == true){
                aux++;
            }
            if (aux == 3){
                proxEp = -1;
                aux = 0;
                flag = false;
            }
        }
        if (proxEp == -1){
            return null;
        }
        return result;
    }
}

