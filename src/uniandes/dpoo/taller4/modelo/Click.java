package uniandes.dpoo.taller4.modelo;

public class Click 
{
	private int numero;

	private long tiempo;

	public Click(int numero)
	{
		this.numero = numero;
		this.tiempo = System.currentTimeMillis();
	}

	@Override
	public String toString()
	{
		return "" + numero + " [" + tiempo + "]";
	}

	public int getNumero()
	{
		return numero;
	}
}
