package modelo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

public class DAO <E>
{
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Class<E> classe; //Recebe literalmente uma classe de par�metro
	
	/* Serve para iniciar emf apenas uma �nica vez, j� que este servir� para v�rias conex�es
	em. Tamb�m serve para criar um bloco, pois objetos e m�todos s�o exeutados apenas em
	blocos de c�digo.*/ 
	
	private static void criarEntityManagerFactory(String unidadeDePersistencia) 
	{
		try
		{
			DAO.emf = Persistence.createEntityManagerFactory(unidadeDePersistencia);
		}
		catch(Exception e)
		{
			System.out.println("EntityManagerFactory n�o foi gerado, emf nulo.");
			e.getMessage();
		}
	}
	
	public DAO() {this(null);}
	
	/*Passando uma classe E como par�metro que sera usada em obterPorId() que usa Class.getName() para achar o registro equivalente no banco de dados. Acontece que o m�todo
	find() que retorna o registro pesquisado, precisa do Class.name, cujo valor � guardado dentro do atributo classe*/
	public DAO(Class<E> classe)
	{
		this.classe = classe;
		if(emf == null)
		{
			criarEntityManagerFactory("sistemaDeConvenio");
		}
		em = emf.createEntityManager();
	}
	
	public DAO<E> comecarTransacao()
	{
		em.getTransaction().begin();
		return this;
	}
	
	public DAO<E> commitarTransacao()
	{
		em.getTransaction().commit();
		this.avisoVisual("Transa��o commitada com sucesso no banco de dados.");
		return this;
	}
	//Precisa estar dentro do contexto de transa��o para funcionar
	public DAO<E> persistirEntidade(E entidade) 
	{
		if(entidade != null)
		{
			em.persist(entidade);
		}
		return this;
	}
	
	//Precisa estar dentro do contexto de transa��o para funcionar
		public DAO<E> removerEntidade(E entidade)
		{
			if(entidade != null)
			{
				em.remove(entidade);
			}
			return this;
		}
		//Precisa estar dentro do contexto de transa��o para funcionar
		public DAO<E> alterarEntidade(E entidade)
		{
			/*� bom chamar o detach() para o caso da vari�vel estiver nula e o JPA tentar comit�-la ao chamar commitarTransacao() por conta do estado gerenciado, evitando um
			 * IllegalArgumentException por tentar atualizar no banco de dados a partir de uma inst�ncia nula.*/
			em.detach(entidade);
			if(entidade != null)
			{
				em.merge(entidade);
			}
			return this;
		}
	
	public DAO<E> persistirDeFormaAtomica(E entidade)
	{
		this.comecarTransacao().persistirEntidade(entidade).commitarTransacao();
		return this;
	}
		
	/*Utiliza uma String que representa uma consulta JPQL, e uma classe de par�metro. O Objeto TypedQuery<E> query recebe de valor a consulta propriamente dita. Para a consulta
	 * ser passada como o valor do objeto TypedQuery<E> query, chamamos o m�todo createQuery(String argumento1, Class<E> argumrno2), o Class<E> passado precisa ser o mesmo
	 * passado no generics do TypedQuery, e a consulta tamb�m precisa coerente, n�o adianta voc� passar E como classe e tentar consultar F na consulta. Um exemplo de cnsulta
	 * seria: select e from E e; No lugar de 'e' voc� pode por o nome que mais achar coerente como alias, o 'E' representa a classe mapeada que voc� deseja buscar no banco
	 * de dados, esta classe precisa ser a mesma do TypedQuery<E>. O m�todo setMaxResults(int argumento1) serve para limitar a quantidade de elementos retornados, o m�todo
	 * getResultList serve para de fato retornar a lista de elementos obtidos com a consulta, este m�todo est� dentro da classe TypedQuery<E> e � chamado a partir de sua
	 * inst�ncia.  O m�todo setFirstResult(int argumento1) serve para pular os 'argumento01' primeiros elementos da tabela no banco de dados.*/
	public List<E> obterTodos()
	{
		if(classe == null)
		{
			throw new UnsupportedOperationException("Classe nula."); 
		}
		String jpql = "select e from " + classe.getName() + " e";
		TypedQuery<E> query = em.createQuery(jpql, classe);
		return query.getResultList();
	}
	
	public List<E> getEmployeeByNameWithNamedQuery(String consulta, Object... params)
	{
		TypedQuery<E> query = em.createNamedQuery(consulta, classe);
		for(int i = 0; i < params.length; i = i + 2)
		{
		query.setParameter(params[i].toString(), params[i + 1].toString() + "%");
		}
		return query.getResultList();
	}
	
	public E consultarUm(String consulta, Object... parametros)
	{
		List<E> lista = this.consultar(consulta, parametros);
		return lista.isEmpty() ? null : lista.get(0);
	}
	
	/*Repare que neste m�todo, temos entre par�ntes Object... parametros, isso significa que quando este m�todo for chamado, al�m de uma String representando a consulta,
	 * tamb�m ir� receber uma quantidade indefinida de par�metros Object, se � indefinido ent�o pode ser de qualquer quantidade, inclusive nenhum par�metro ap�s a String,
	 * fiz este m�todo desta maneira pois dependendo da consulta, posso precisar de par�metros de tipos diferentess, mas n�o necessariamente ser�o objetos do tipo Object.
	 * Tamb�m � imprtante falar do m�todo TypedQuery setParameter(String arg0, Object arg1), o primeiro argumento em String se refere ao par�metro declarado na consulta jpql
	 * enquanto o segundo argumento ser� o valor que o par�metro declarado na consulta jpql ir� assumir. Isso acontece porque uma consulta jpql pode ter v�rios par�metros
	 * declarados, ent�o � preciso especificar o valor de todos um por um.*/
	public List<E> consultar(String consulta, Object... parametros)
	{
		TypedQuery<E> query = em.createNamedQuery(consulta, classe);
		for(int i = 0; i< parametros.length; i = i + 2)
		{
			query.setParameter(parametros[i].toString(), parametros[i + 2]);
		}
		return query.getResultList();
	}
	
	public List<E> getEmployeeByName(String nome)
	{
		TypedQuery<E> query = em.createQuery("select f from Funcionario f where f.nome like :nomeParametro", classe);
		query.setParameter("nomeParametro", "%" + nome + "%");
		return query.getResultList();
	}
		
	public void fecharEntityManager()
	{
		em.close();
	}
	
	/*Aqui est� um motivo de usar Generics, com este m�todo que usa um id, me permite usar este m�todo para obter qualquer objeto de qualquer tipo, e por isso o motivo de 
	* fornecer uma classe no construtor, para poder referenciar o tipo gen�rico E como par�metro e assim pesquisar o objeto desejado a partir do m�todo Class.getName() 
	* que retorna a classe passada no construtor
	* Obtem um objeto do banco de dados pelo id, o m�todo public E find(Class<E> argumento1, Object argumento2) faz uma consulta do tipo where, por isso � recomend�vel usar
	* um id como par�metro, mas o m�todo aceita qualquer Object como segundo par�metro. O Class<E> deve estar mapeado no persistence.xml e � usado para se saber qual o 
	* tipo de objeto que ser� recuperado no banco de dados*/
	public E obterPorId(Long id)
	{
		return em.find(classe, id);
	}
	
	public void avisoVisual(String mensagem)
	{
		JOptionPane.showMessageDialog(null, mensagem );
	}
}
