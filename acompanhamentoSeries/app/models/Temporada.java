package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Temporada {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private int numero;
	@Column
	private int assistida;
	@Column
	private int qtdEpisodios;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Serie serie;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="TEMP_EPS")
	private List<Episodio> episodios;

	public Temporada() {
		assistida = -1;
		episodios = new ArrayList<>();
		qtdEpisodios = episodios.size();
	}
	
	public Temporada(int num, Serie serie){
		this();
		this.numero = num;
		this.serie = serie;
	}
	
	public void addEpisodio(Episodio ep){
		episodios.add(ep);
		qtdEpisodios++;
	}

	public Long getId() {
		return id;
	}

	private void setId(Long id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public List<Episodio> getEpisodios() {
		return episodios;
	}

	public void setEpisodios(List<Episodio> episodios) {
		this.episodios = episodios;
	}

	public int isAssistida() {
		return assistida;
	}

	public void setAssistida(int assistida) {
		this.assistida = assistida;
	}

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	public int getQtdEpisodios() {
		return qtdEpisodios;
	}

	public void setQtdEpisodios(int qtdEpisodios) {
		this.qtdEpisodios = qtdEpisodios;
	}

}
