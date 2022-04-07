package br.com.loja.model;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.SimpleFormatter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.loja.rn.convertDateInBank;
import br.com.olimposistema.aipa.imagem.Imagem;
import br.com.olimposistema.aipa.model.Model;


@Entity

public class Product extends Model{
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.EAGER, orphanRemoval = true) 
	private Imagem imageProduct;
	@NotEmpty(message="Nome do produto não pode estar vazio") @Size(min = 2,max = 100, message="Nome pode ter no maximo 100 caracters") @Column(nullable=true)
	private String nameProduct;
	@NotNull(message="Valor do produto não pode estar vazio") @Column(nullable=true) @Min(value=0, message="Adicione um valor positivo")
	private double valueProduct;
	@NotEmpty(message="Descrição do produto não pode estar vazia") @Type(type="text")
	private String descriptionProduct;
	@Temporal(TemporalType.DATE) @NotNull(message="Insira a data que o produto foi cadastrado")
	private Calendar dateValidate;
	@ManyToOne @JoinColumn(nullable = false) @NotNull(message="Selecione uma categoria")
	private Categoria categoryProduct;
	
	// Getter and Setters
	
	public String getNameProduct() {
		return nameProduct;
	}
	public Categoria getCategoryProduct() {
		return categoryProduct;
	}
	public void setCategoryProduct(Categoria categoryProduct) {
		this.categoryProduct = categoryProduct;
	}
	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}
	public double getValueProduct() {
		return valueProduct;
	}
	
	public String getValueMoney() {
		
		String valueMoney = NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(valueProduct);
		
		
		return valueMoney;
	}
	
	public void setValueProduct(double valueProduct) {
		this.valueProduct = valueProduct;
	}
	public String getDescriptionProduct() {
		return descriptionProduct;
	}
	public void setDescriptionProduct(String descriptionProduct) {
		this.descriptionProduct = descriptionProduct;
	}	
	public Calendar getDateValidate() {
		return dateValidate;
	}
	
	public String getDateFormat() {
		
	String dateFormat = new SimpleDateFormat("dd/MM/yyyy").format(dateValidate.getTime());
		
	return dateFormat;
	}
	
	public void setDateValidate(Calendar dateValidate) {
		this.dateValidate = dateValidate;
	}
	
	// formatando DATE para o banco de dados
	public void setDateValidateEn(String date) {
		if(date == null) return;
		this.dateValidate = new convertDateInBank().execute(date);
	}
	public Imagem getImageProduct() {
		return imageProduct;
	}
	public void setImageProduct(Imagem imageProduct) {
		this.imageProduct = imageProduct;
	}
	
}
