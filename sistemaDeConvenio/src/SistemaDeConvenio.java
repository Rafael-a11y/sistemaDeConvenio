import java.util.List;

import modelo.Conveniado;
import modelo.DAO;
import modelo.Dependente;
import modelo.DocumentosPessoais;
import modelo.Endereco;
import modelo.Funcionario;
import modelo.QuantidadeFuncionarios;
import visao.Visao;

public class SistemaDeConvenio 
{
	public static void main(String[] args)
	{
		DAO<QuantidadeFuncionarios> daoQtd = new DAO<>(QuantidadeFuncionarios.class);
		QuantidadeFuncionarios qtd = daoQtd.consultarUm("obterQuantidadeFuncionarios");
		System.out.println(qtd.getQuantidadeFuncionarios());
	}
}
