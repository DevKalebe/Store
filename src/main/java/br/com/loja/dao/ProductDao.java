package br.com.loja.dao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.loja.model.Categoria;
import br.com.loja.model.Product;
import br.com.olimposistema.aipa.dao.DAO;

@RequestScoped
public class ProductDao extends DAO<Product>{
	
	@Deprecated public ProductDao() {super(null, null);
	
	}
	
	@Inject
	public ProductDao(EntityManager em) {
		super(em, Product.class);
	}
}
