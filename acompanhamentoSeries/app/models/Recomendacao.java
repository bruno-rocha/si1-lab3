package models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Bruno on 10/02/2015.
 */
@Entity
@DiscriminatorColumn(name="REF_TYPE")
public abstract class Recomendacao {
    @Id
    @GeneratedValue
    private Long id;

    public abstract String getProximoEpisodio(Temporada temporada);
}
