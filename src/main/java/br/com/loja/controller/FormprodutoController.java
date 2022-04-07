package br.com.loja.controller;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.Valid;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.validator.Validator;
import br.com.loja.dao.CategoriaDao;
import br.com.loja.dao.ProductDao;
import br.com.loja.interceptors.OnlyLogged;
import br.com.loja.model.Product;

@Controller
@Path("formproduto")
public class FormprodutoController {
	
	@Inject Validator productValidator;
	@Inject ProductDao productDao;
	@Inject CategoriaDao categoryDao;
	@Inject Result productResult;
	@Inject EntityManager em;
	
	@Get("") @OnlyLogged
	public void formproduto() {
		System.out.println("\n\n\n\nInclui a categoria");
		productResult.include("categorys", categoryDao.selectAll());
		System.out.println("\n\n\n\nterm Inclui a categoria");
		
	}
	
	@Post("registerProduct") @IncludeParameters
	public void registerProduct(@Valid Product product) {
		
		// fazer depois o teste para verificar se produto já existe
		System.out.println("\n\n\n\nAntes de verificar se tem erro");
		productValidator.onErrorRedirectTo(this).formproduto();
		System.out.println("\n\n\n\nNão teve erro");
		productDao.insertOrUpdate(product);
		System.out.println("\n\n\n\ncadastrado");
		productResult.redirectTo(ProdutosController.class).produtos();
		System.out.println("\n\n\n\nCadastrou no banco");
	}
}
