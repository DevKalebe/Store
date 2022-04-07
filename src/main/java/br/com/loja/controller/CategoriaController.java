package br.com.loja.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.loja.dao.CategoriaDao;
import br.com.loja.interceptors.OnlyLogged;
import br.com.loja.model.Categoria;
import br.com.loja.model.Usuario;
import br.com.olimposistema.aipa.dao.DAO;
import br.com.olimposistema.aipa.service.Util;

@Controller
@Path("categoria")
public class CategoriaController {
	
	@Inject HttpSession session;
	@Inject Result result;
	@Inject CategoriaDao categoriaDao;
	
	@Get("") @OnlyLogged
    public void categoria() {
		
		// buscar no banco
		
		
		List<Categoria> categorias = categoriaDao.selectAll();
		
		
		 
		// disponibilizar para o jsp
		result.include("categorias", categorias);
		
		
      }
}
