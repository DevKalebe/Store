package br.com.loja.controller;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.IncludeParameters;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.loja.dao.UserDao;
import br.com.loja.interceptors.OnlyLogged;
import br.com.loja.model.Usuario;
import br.com.olimposistema.aipa.dao.DAO;

//Definindo o controlador do nosso App
@Controller	
@Path("login")
public class LoginController {
	
		@Inject Result loginResult;
		@Inject Validator loginValidator;
		@Inject UserDao userDao;
		@Inject HttpSession loginHS;
	
		@Get("")
		public void login() {
		
		System.out.println("|||||||||||||||||||||||||||||||");
    	System.out.println("Entrou na tela de login");
    	System.out.println("|||||||||||||||||||||||||||||||");
     }
		
		@IncludeParameters
		@Post("autenticarUser")
		public void autenticarUser(Usuario usuario, @NotEmpty(message = "{email.autenticar.empty}") @Size(min=4, max=150, message="{email.autenticar.size}") String email, @NotEmpty(message ="{senha.autenticar.empty}") @Size(min=6, max=16, message="{senha.autenticar.size}") String senha) {
			System.out.println("|||||||||||||||||||||||||||||||||||||||");
			System.out.println("Email: "+email+"\nSenha: "+ senha);
			System.out.println("||||||||||||||||||||||||||||||||||||||||");
			// redirecionado página caso houver erro
			loginValidator.onErrorRedirectTo(this).login();
		
			usuario = userDao.thereUserWith(email, senha);
			loginHS.setAttribute("usuariologado", usuario);
			loginValidator.addIf(usuario == null,new SimpleMessage("erro", "Email ou senha são inválidos"));
			
			loginValidator.onErrorRedirectTo(this).login();
			
			// redireciona para produto se der certo a autenticação
			loginResult.redirectTo(ProdutosController.class).produtos();
		}
}
