package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity @Table(name = "dependentes") public class Dependente
{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)	private Long id;
	@Column(name = "nome's", nullable = false)	private String nome;
	@Column(name = "idade's", nullable = false) private int idade;
	DocumentosPessoais documentos;
	@ManyToOne private Conveniado titular;
	
	public Dependente() {}
	public Dependente(String nome, int idade, DocumentosPessoais documentos, Conveniado titular) 
	{
		super();
		this.nome = nome;
		this.idade = idade;
	  	this.titular = titular;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public Conveniado getTitular() {
		return titular;
	}
	public void setTitular(Conveniado conveniado) {
		this.titular = conveniado;
	}
}
