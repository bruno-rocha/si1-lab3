package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity(name="Serie")
public class Serie {
	@Transient
	public int EP_SEGUINTE = 1;
	@Transient
	public int EP_MAIS_ANTIGO = 2;

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String nome;
	@Column
	private boolean acompanhada;
	@Column
	private int qtdTemporadas;


	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="TEMPS")
	private List<Temporada> temporadas;

	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="opcao_recomendacao")
	Recomendacao opcaoRecomendacao;

	public Serie() {
		temporadas = new ArrayList<>();
		acompanhada = false;
		qtdTemporadas = temporadas.size();
		this.setOpcaoRecomendacao(new RecomendaEpSeguinte());
	}
	
	public Serie(String nome){
		this();	
		this.nome = nome;
	}
	
	public void addTemporada(Temporada temp){
		temporadas.add(temp);
		qtdTemporadas++;
	}
	
	public void addEpisodio(Episodio ep){
		temporadas.get(ep.getTemporada().getNumero()-1).addEpisodio(ep);
	}
	
	public Temporada getTemporada(int num){
		return temporadas.get(num-1);
	}
	
	public Long getId() {
		return id;
	}

	private void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Temporada> getTemporadas() {
		return temporadas;
	}

	private void setTemporadas(List<Temporada> temporadas) {
		this.temporadas = temporadas;
	}
	
	public boolean isAcompanhada() {
		return acompanhada;
	}
	
	public void setAcompanhada(boolean acompanhada) {
		this.acompanhada = acompanhada;
	}

	public int getQtdTemporadas() {
		return qtdTemporadas;
	}

	public void setQtdTemporadas(int qtdTemporadas) {
		this.qtdTemporadas = qtdTemporadas;
	}

	public Recomendacao getOpcaoRecomendacao() {
		return opcaoRecomendacao;
	}

	public void setOpcaoRecomendacao(Recomendacao opcaoRecomendacao) {
		this.opcaoRecomendacao = opcaoRecomendacao;
	}

}

