package controle;

import java.util.List;

import modelo.Conveniado;
import modelo.DAO;
import modelo.Dependente;
import modelo.Funcionario;

public class Controle 
{

	private DAO<Object> dao;
	private DAO<Funcionario> daoFuncionario;
	private DAO<Conveniado> daoConveniado;
	private DAO<Dependente> daoDependente;

	public DAO<Object> getDao() {
		return dao;
	}

	public void setDao(DAO<Object> dao) {
		this.dao = dao;
	}
	
	public void create(Object objeto) {
		new DAO<Object>(Object.class).persistirDeFormaAtomica(objeto).fecharEntityManager();
	}
	
	public void update(Object objeto) {
		this.setDao(new DAO<>());
		this.dao.comecarTransacao().alterarEntidade(objeto).commitarTransacao().fecharEntityManager();
	}
	
	public void removeFuncionario(Long id) {
		this.setDaoFuncionario(new DAO<Funcionario>(Funcionario.class));
		Funcionario objeto = this.daoFuncionario.obterPorId(id);
		dao.comecarTransacao().removerEntidade(objeto).commitarTransacao().fecharEntityManager();
	}
		
		public void removeConveniado(Long id) {
			this.setDaoConveniado(new DAO<Conveniado>(Conveniado.class));
			Conveniado objeto = daoConveniado.obterPorId(id);
			dao.comecarTransacao().removerEntidade(objeto).commitarTransacao().fecharEntityManager();
			
	}
		
		public void removeDependente(Long id) {
			this.setDaoDependente(new DAO<Dependente>(Dependente.class));
			Dependente objeto = this.daoDependente.obterPorId(id);
			dao.comecarTransacao().removerEntidade(objeto).commitarTransacao().fecharEntityManager();
		}
	
	public List<Funcionario> getFuncionarios(){
		
		this.setDaoFuncionario(new DAO<Funcionario>(Funcionario.class));
		return daoFuncionario.obterTodos();
	}
	
	public List<Conveniado> getConveniados(){
		
		this.setDaoConveniado(new DAO<Conveniado>(Conveniado.class));
		return daoConveniado.obterTodos();
	}
	
	public List<Dependente> getDependentes(){
		
		this.setDaoDependente(new DAO<Dependente>(Dependente.class));
		return daoDependente.obterTodos();
	}
	
	public Funcionario getFuncionarioByYd(Long id) {
		this.setDaoFuncionario(new DAO<Funcionario>(Funcionario.class));
		return daoFuncionario.obterPorId(id);
	}
		
		public Conveniado getConveniadoByYd(Long id) {
			this.setDaoConveniado(new DAO<Conveniado>(Conveniado.class));
			return daoConveniado.obterPorId(id);
	}
		
		public Dependente getDependenteByYd(Long id) {
			this.setDaoDependente(new DAO<Dependente>(Dependente.class));
			return daoDependente.obterPorId(id);
	}

		public DAO<Funcionario> getDaoFuncionario() {
			return daoFuncionario;
		}

		public void setDaoFuncionario(DAO<Funcionario> daoFuncionario) {
			this.daoFuncionario = daoFuncionario;
		}

		public DAO<Conveniado> getDaoConveniado() {
			return daoConveniado;
		}

		public void setDaoConveniado(DAO<Conveniado> daoConveniado) {
			this.daoConveniado = daoConveniado;
		}

		public DAO<Dependente> getDaoDependente() {
			return daoDependente;
		}

		public void setDaoDependente(DAO<Dependente> daoDependente) {
			this.daoDependente = daoDependente;
		}
		
	
}
