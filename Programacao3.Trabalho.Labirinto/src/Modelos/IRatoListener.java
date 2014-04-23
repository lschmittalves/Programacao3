package Modelos;

/** Contem os eventos do rato. */
public interface IRatoListener {

	/** Metodo para locomocao do rato. */
	public void onMove(StringBuilder labirinto);

	/** Metodo para alteracao de cor do rato. */
	public void onChangeColor(EnumCores newColor);

	/** Metodo que e disparado quando o rato morre. */
	public void onDead();

	/** Metodo que e disparado quando o rato come um queijo. */
	public void onEat();

	/** Metodo que e disparado quando o rato chega a saida do labirinto. */
	public void onFinish();
}
