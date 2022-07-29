package modelo;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity @Table(name = "funcionarios") @Inheritance(strategy = InheritanceType.JOINED) @DiscriminatorColumn(name = "tipoFuncionario", length = 2, 
		discriminatorType = DiscriminatorType.STRING) @DiscriminatorValue("FU") public class Funcionario 
{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)	private Long id;
	@Column(name = "nomes", nullable = false) private String nome;
	Endereco endereco;
	DocumentosPessoais documentos;
	
	public Funcionario() {}

	public Funcionario(String nome, Endereco endereco, DocumentosPessoais documentos) 
	{
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.documentos = documentos;
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}
