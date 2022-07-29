package modelo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable public class Endereco 
{
	@Column(name = "Cidade's", unique= true, nullable = false)	private String cidade;
	@Column(name = "CEP's", unique= true, nullable = false)	private String cep;
	
	public Endereco() {}
	
	public Endereco(String cidade, String cep) 
	{
		super();
		this.cidade = cidade;
		this.cep = cep;
	}
	
	public String getCidade() 
	{
		return cidade;
	}
	public void setCidade(String cidade) 
	{
		this.cidade = cidade;
	}
	public String getCep() 
	{
		return cep;
	}
	public void setCep(String cep) 
	{
		this.cep = cep;
	}
	
}
