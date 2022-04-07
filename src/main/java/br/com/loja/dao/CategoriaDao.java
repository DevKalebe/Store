package br.com.loja.dao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.loja.model.Categoria;
import br.com.olimposistema.aipa.dao.DAO;

@RequestScoped
public class CategoriaDao extends DAO<Categoria>{
	
	
	@Deprecated public CategoriaDao() {super(null, null);
		
	}
	
	@Inject
	public CategoriaDao(EntityManager em) {
		super(em, Categoria.class);
	}
	
	public Categoria CheckCategoriaRegistered(String tipo) {
		
		String jpql ="select c from Categoria c where c.tipo = :pTipo";
		
		Query query = em.createQuery(jpql);
		query.setParameter("pTipo", tipo);
		
		try {
			Categoria categoria = (Categoria) query.getSingleResult();
			
			return categoria;
		} catch (Exception e) {
			
			return null;  
		}
	}
}
