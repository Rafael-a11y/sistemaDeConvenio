package controle;

import java.util.List;

import modelo.DAO;

public class Controle 
{

	private DAO<Object> dao;

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
	
	public void remove(int id) {
		this.setDao(new DAO<>(Object.class));
		Object objeto = this.dao.obterPorId(id);
		dao.comecarTransacao().removerEntidade(objeto).commitarTransacao().fecharEntityManager();
	}
	
	public List<Object> getObjetos(){
		
		this.setDao(new DAO<>(Object.class));
		return dao.obterTodos();
	}
	
	public Object getObjetoByYd(int id) {
		this.setDao(new DAO<>(Object.class));
		return dao.obterPorId(id);
	}
}
