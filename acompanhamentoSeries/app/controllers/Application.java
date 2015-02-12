package controllers;

import java.util.Collections;
import java.util.List;

import models.*;
import models.dao.GenericDAO;
import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {
	private static List<Serie> series;
	private static GenericDAO dao = new GenericDAO();
	private final static int EP_SEGUINTE = 1;
	private final static int EP_MAIS_ANTIGO = 2;
	private final static int EP_APOS_TRES = 3;
	
	@Transactional
	public static Result index() {
    	getSeriesFromDB();
        return ok(index.render(series));
    }
    
	@Transactional
	public static Result acompanhaSerie(Long id){
		Serie serie = getDAO().findByEntityId(Serie.class, id);
		serie.setAcompanhada(true);
		getDAO().merge(serie);
		getDAO().flush();
		
		Logger.info("Acompanhando " + serie.getNome());
		
		return redirect("/");
	}
	
	@Transactional
	public static Result desacompanhaSerie(Long id){
		Serie serie = getDAO().findByEntityId(Serie.class, id);
		serie.setAcompanhada(false);
		getDAO().merge(serie);
		getDAO().flush();
		
		Logger.info("Desacompanhou " + serie.getNome());
		
		return redirect("/");
	}
	
	@Transactional
	public static Result cancelaEpisodio(Long id){
		Episodio ep = getDAO().findByEntityId(Episodio.class, id);
		ep.setAssistido(false);
		getDAO().merge(ep);
		getDAO().flush();
		
		Logger.info("Cancelou ep " + ep.getNome());
		
		return redirect("/");
	}
	
	@Transactional
	public static Result assisteEpisodio(Long id){
		Episodio ep = getDAO().findByEntityId(Episodio.class, id);
		ep.setAssistido(true);
		getDAO().merge(ep);
		getDAO().flush();
		
		Logger.info("Assistiu ep " + ep.getNome());
		
		return redirect("/");
	}

	@Transactional
	public static Result setOpcaoRecomendacao(Long id) {
		Serie serie = getDAO().findByEntityId(Serie.class, id);
		DynamicForm form = Form.form().bindFromRequest();
		int opcao = Integer.parseInt(form.get("opcao"));

		if (opcao == EP_SEGUINTE){
			serie.setOpcaoRecomendacao(new RecomendaEpSeguinte());
		}else if (opcao == EP_MAIS_ANTIGO){
			serie.setOpcaoRecomendacao(new RecomendaEpAntigo());
		}else if (opcao == EP_APOS_TRES){
			serie.setOpcaoRecomendacao(new RecomendaEpApos3());
		}
		getDAO().merge(serie);
		getDAO().flush();

		Logger.info("Próximo ep por opção " + opcao);

		return redirect("/");
	}

    @Transactional
	private static void getSeriesFromDB(){
		series = getDAO().findAllByClassName("Serie");
		getDAO().flush();
	}
    
    public static GenericDAO getDAO(){
		return dao;
	}

}
