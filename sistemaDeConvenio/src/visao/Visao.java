package visao;

import controle.Controle;

public class Visao 
{
	private Controle controle;
	
	public Controle getControle() 
	{
		if(this.controle == null)
		{
			controle = new Controle();
		}
		return controle;
	}

	public void setControle(Controle controle) {
		this.controle = controle;
	}
	
}
