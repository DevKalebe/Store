package br.com.loja.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.loja.dao.ProductDao;
import br.com.loja.interceptors.OnlyLogged;

@Controller
@Path("produtos")
public class ProdutosController {
	
	@Inject Result result;
	@Inject ProductDao daoProduct;
	
      @Get("") @OnlyLogged
      public void produtos() {
    	  
    	  result.include("products", daoProduct.selectAll());
    	  
      }
      
      
}
