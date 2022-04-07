package br.com.loja.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.loja.dao.CategoriaDao;
import br.com.loja.interceptors.OnlyLogged;
import br.com.loja.model.Categoria;

@Controller
@Path("deleteCategory")
public class DeleteCategoryController {
	
	@Inject CategoriaDao categoryDao;
	@Inject Result result;
	
	@Get("/{categoria.id}") @OnlyLogged
	public void deleteCategory(Categoria categoria) {
		
		categoryDao.delete(categoria);
		result.redirectTo(CategoriaController.class).categoria();
	}
	
}
