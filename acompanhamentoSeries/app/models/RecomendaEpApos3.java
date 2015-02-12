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
        int proxEp = 0;
        int aux = 0;
        boolean flag = false;
        for (int i = 0; i <= temporada.getQtdEpisodios() - 1; i++) {
            if(!episodios.get(i).isAssistido()){
                proxEp = i;
                result = episodios.get(i).getNumero() + " - " + episodios.get(i).getNome();
                for (int j = proxEp; j <= temporada.getQtdEpisodios() - 1; j++){
                    if (episodios.get(i).isAssistido()){
                        aux++;
                        if (aux == 3){
                            break;
                        }else{
                            flag = true;
                        }
                    }
                }
                if (flag == true){
                    break;
                }

            }
        }
        return result;
    }
}

