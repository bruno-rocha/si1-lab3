package controllers;

import java.util.Collections;
import java.util.List;

import models.Serie;
import models.dao.GenericDAO;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {
	private static List<Serie> series;
	private static GenericDAO dao = new GenericDAO();
	
	@Transactional
	public static Result index() {
    	getMetasFromDB();
        return ok(index.render(series));
    }
    
    @Transactional
	private static void getMetasFromDB(){
		series = getDAO().findAllByClassName("Serie");
		getDAO().flush();
	}
    
    public static GenericDAO getDAO(){
		return dao;
	}

}
