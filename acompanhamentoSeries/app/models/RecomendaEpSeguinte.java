package models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Bruno on 10/02/2015.
 */
@Entity
public class RecomendaEpSeguinte extends Recomendacao {

    @Override
    public String getProximoEpisodio(Temporada temporada) {
        List<Episodio> episodios = temporada.getEpisodios();

        String result = episodios.get(0).getNumero() + " - " + episodios.get(0).getNome();

        for (int i = temporada.getQtdEpisodios() - 1; i >= 0; i--) {
            if (episodios.get(i).isAssistido()) {
                if ((i + 1) == temporada.getQtdEpisodios())
                    result = "Você já assistiu ao último episódio desta temporada.";
                else
                    result = episodios.get(i + 1).getNumero() + " - " + episodios.get(i + 1).getNome();
                break;
            }
        }
        return result;

    }
}
