import modelo.DocumentosPessoais;
import modelo.Endereco;
import modelo.Funcionario;
import visao.Visao;

public class SistemaDeConvenio 
{
	public static void main(String[] args)
	{
		Visao visao = new Visao();
		visao.getControle().create(new Funcionario("Rafael", new Endereco("Recanto das Emas", "72621310"), new DocumentosPessoais("06763614128", "3567449")));
	}
}
