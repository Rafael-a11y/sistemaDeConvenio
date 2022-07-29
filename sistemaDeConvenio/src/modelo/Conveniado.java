package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity @Table(name = "conveniados") @DiscriminatorValue("FC") public class Conveniado extends Funcionario
{
	@Column(name = "Convênio's", nullable = false) private String nomeConvenio;
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "titular") private List<Dependente> dependentes;
	
	public Conveniado() {}
	public Conveniado(String nomeConvenio) 
	{
		super();
		this.nomeConvenio = nomeConvenio;
	}
	public Conveniado(String nome, Endereco endereco, DocumentosPessoais documentos, String nomeConvenio)
	{
		super(nome, endereco, documentos);
		this.nomeConvenio = nomeConvenio;
	}
	
	public String getNomeConvenio() 
	{
		return nomeConvenio;
	}
	public void setNomeConvenio(String nomeConvenio) 
	{
		this.nomeConvenio = nomeConvenio;
	}
	public List<Dependente> getDependentes()
	{
		if(dependentes != null)
		{
			dependentes = new ArrayList<>();
		}
		return dependentes;
	}
	public void setDependentes(List<Dependente> dependentes) 
	{
		this.dependentes = dependentes;
	}
	public void adicionarDependente(Dependente dependente)
	{
		if(dependente != null)
		{
			getDependentes().add(dependente);
		}
	}
}
