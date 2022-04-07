package br.com.loja.dao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.loja.model.Usuario;
import br.com.olimposistema.aipa.dao.DAO;

@RequestScoped
public class UserDao extends DAO<Usuario>{

	
	@Deprecated public UserDao() {super(null, null);
		
	}
	
	@Inject
	public UserDao(EntityManager em) {
		super(em, Usuario.class);

	}

	public Usuario thereUserWith(String email, String senha) {
		String jpql ="select u from Usuario u where u.email= :pEmail and u.senha = :pSenha";
		
		Query query = em.createQuery(jpql) ; 
		
		query.setParameter("pEmail", email);
		query.setParameter("pSenha", senha);
		
		try {
			Usuario usuario = (Usuario) query.getSingleResult();
			
			return usuario;
		} catch (NoResultException e) {
			
			return null;
		}
		
		
	}
	
	// metodo criado por mim
	public Usuario CheckEmailRegistered(String email) {
		
		String jpql = "select u from Usuario u where u.email = :pEmail";
		
		Query query = em.createQuery(jpql);
		query.setParameter("pEmail", email);
		
		try {
			Usuario usuario = (Usuario) query.getSingleResult();
			
			return usuario;
		} catch (NoResultException e) {

			return null;
		}
		
		
	}
	
}
