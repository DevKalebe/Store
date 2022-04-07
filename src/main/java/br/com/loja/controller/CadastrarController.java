package br.com.loja.controller;

import javax.inject.Inject;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.loja.dao.UserDao;

import br.com.loja.model.Usuario;


@Controller
@Path("cadastrar")
public class CadastrarController {
    
	@Inject EntityManager em;
	@Inject Result result;
	@Inject UserDao usuarioDao;
	@Inject Validator userValidator;
	@Inject HttpSession userSession;
	
	@Get("") 
	public void cadastrar() {
		
		System.out.println("Entrei no tela de cadastro");
	}
	
	// Metodo para salvar o úsuario
	
	@IncludeParameters
	@Post("salvaUsuario")
	public void salvaUsuario(@Valid Usuario usuario, String confirmSenha) {
	
		
		// confirmando email existente
		boolean emailExist = true;
		if (usuarioDao.CheckEmailRegistered(usuario.getEmail()) != null) {
			emailExist = false;
		}
		
		userValidator.ensure(emailExist, new SimpleMessage("erro", "Email já cadastrado, verifique seu login ou cadastre outro e-mail"));
		
		//confirmando a senha
		boolean senhaEquals = usuario.getSenha().equals(confirmSenha);
		userValidator.ensure(senhaEquals, new SimpleMessage("erro", "A confirmação da senha é diferente"));
		
		
		
//		result.include("usuarioNome", usuario.getNome());
//		result.include("usuarioEmail", usuario.getEmail());
//		result.include("usuarioSenha", usuario.getSenha());
		userValidator.onErrorRedirectTo(this).cadastrar(); 
		usuarioDao.insert(usuario);
		userSession.setAttribute("usuariologado", usuario);
		result.redirectTo(ProdutosController.class).produtos();
		System.out.println("===============Informações do usuário=====================");
		System.out.println("Nome do usuario:" + usuario.getNome());
		System.out.println("Email do usuario:" + usuario.getEmail());
		System.out.println("Senha do usuario:" + usuario.getSenha());
		System.out.println("==========================================================");
		 
	}
}
