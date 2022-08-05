import java.util.List;

import modelo.Conveniado;
import modelo.DAO;
import modelo.Dependente;
import modelo.DocumentosPessoais;
import modelo.Endereco;
import modelo.Funcionario;
import visao.Visao;

public class SistemaDeConvenio 
{
	public static void main(String[] args)
	{
		DAO<Funcionario> daoFuncionario = new DAO<>(Funcionario.class);
		
		List<Funcionario> listaFuncionarios = daoFuncionario.getEmployeeByName("Ra");
		
		for(Funcionario f : listaFuncionarios)
		{
			System.out.println(f.getNome());
		}
		
	}
}
