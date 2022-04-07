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
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.loja.dao.CategoriaDao;
import br.com.loja.interceptors.OnlyLogged;
import br.com.loja.model.Categoria;
import br.com.olimposistema.aipa.service.Util;

@Controller
@Path("formcategoria")
public class FormcategoriaController {
	
	@Inject Validator categoriaValidator;
	@Inject CategoriaDao categoriaDao;
	@Inject Result categoriaResult;
	@Inject EntityManager em;
	
	@Get("")	@OnlyLogged
	public void formcategoria(Categoria categoria) {
		
		// fazendo o alterar
		if (Util.isNotNull(categoria) && Util.isPositivo(categoria.getId())) {
			Categoria categoryBank = categoriaDao.selectPorId(categoria);
			categoriaResult.include("categoria", categoryBank);
		}
		
		System.out.println("\n\n\n\nEntrou na tela de cadastro\n\n\n\n");
	}
	
	@IncludeParameters
	@Post("cadastrarCategoria")
	public void cadastrarCategoria(@Valid Categoria categoria) {
		
		// Fazendo metodo para validar se existe uma categoria e tratar do jeito que o úsuario escreve mantendo um padrão
		categoria.setTipo(categoria.getTipo().toLowerCase());
		String palavra = categoria.getTipo();
		palavra = palavra.substring(0,1).toUpperCase().concat(palavra.substring(1));
		categoria.setTipo(palavra);
				
		
		boolean categoriaExist = true;
		if (categoriaDao.CheckCategoriaRegistered(categoria.getTipo()) != null) {
			categoriaExist = false;
		}
		
		categoriaValidator.ensure(categoriaExist, new SimpleMessage("erro", "Tipo de categoria já existe, verifque a lista de categorias e adicione uma diferente"));
		
		categoriaValidator.onErrorRedirectTo(this).formcategoria(categoria);
		categoriaDao.insertOrUpdate(categoria);
		categoriaResult.redirectTo(CategoriaController.class).categoria();
		
		
		
		System.out.println("\n\n\n\nCadastrou "+categoria.getTipo()+"\n\n\n\n");
	}
	
	
}
