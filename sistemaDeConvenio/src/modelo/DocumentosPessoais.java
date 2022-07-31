package modelo;

	import javax.persistence.Column;
	import javax.persistence.Embeddable;

@Embeddable public class DocumentosPessoais {

	@Column(name = "CPF", unique = true, nullable = false) private String cpf;
	@Column(name = "RG", unique = true, nullable = false) private String rg;
	
	public DocumentosPessoais() {}

	public DocumentosPessoais(String cpf, String rg) 
	{
		super();
		this.cpf = cpf;
		this.rg = rg;
	}
	
	public String getCpf() 
	{
		return cpf;
	}
	public void setCpf(String cpf) 
	{
		this.cpf = cpf;
	}
	public String getRg() 
	{
		return rg;
	}
	public void setRg(String rg)
	{
		this.rg = rg;
	}
}
