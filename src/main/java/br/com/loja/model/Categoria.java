package br.com.loja.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.olimposistema.aipa.model.Model;

@Entity

public class Categoria extends Model{
	
	@NotEmpty(message = "Nome de categoria não pode ser vazio") @Size(min=2, max=100, message="Máximo de caracteres é até 100") @Column(unique = true) 
	private String tipo;
	
	@OneToMany(mappedBy = "categoryProduct")
	private List<Product> products;
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	
	
}
