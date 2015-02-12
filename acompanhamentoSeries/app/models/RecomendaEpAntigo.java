package models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Bruno on 10/02/2015.
 */
@Entity
public class RecomendaEpAntigo extends Recomendacao{

    @Override
    public String getProximoEpisodio(Temporada temporada){
        List<Episodio> episodios = temporada.getEpisodios();

        String result = episodios.get(0).getNumero() + " - " + episodios.get(0).getNome();

        for (int i = 0; i <= temporada.getQtdEpisodios() - 1; i++) {
            if(!episodios.get(i).isAssistido()){
                result = episodios.get(i).getNumero() + " - " + episodios.get(i).getNome();
                break;
            }
        }
        return result;
    }
}
