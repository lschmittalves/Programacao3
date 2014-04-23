package Modelos;

/** Contem metodos com as funcoes relativas aos obstaculos. */
public class Obstaculo {

	/** Propriedade de posicao da linha. */
	private int posicaoX;
	/** Propriedade de posicao da coluna. */
	private int posicaoY;
	/** Propriedade com tipo de obstaculos presentes no labirinto. */
	private EnumTipoObstaculo tipoObstaculo;

	/**
	 * Configura as propriedades do Obstaculo.
	 * 
	 * @param posicaoX
	 *            linha
	 * 
	 * @param posicaoY
	 *            coluna
	 * 
	 * @param EnumTipoObstaculo Obstaculo
	 *            enum que contem todos os obstaculos presentes no labirinto
	 */
	public Obstaculo(int posicaoX, int posicaoY, EnumTipoObstaculo Obstaculo) {
		this.posicaoX = posicaoX;
		this.posicaoY = posicaoY;
		this.tipoObstaculo = Obstaculo;
	}

	/**
	 * Pega a posicao da linha.
	 * 
	 * @return posicaoX
	 */
	public int getPosicaoX() {
		return posicaoX;
	}

	/**
	 * Configura a propriedade setPosicaoX.
	 * 
	 * @param posicaoX
	 * 
	 */
	public void setPosicaoX(int posicaoX) {
		this.posicaoX = posicaoX;
	}

	/**
	 * Pega a posicao da coluna.
	 * 
	 * @return posicaoY
	 */
	public int getPosicaoY() {
		return posicaoY;
	}

	/**
	 * Seta a posicao da coluna.
	 * 
	 * @param posicaoY
	 * 
	 */
	public void setPosicaoY(int posicaoY) {
		this.posicaoY = posicaoY;
	}

	/**
	 * Pega o tipo de obstaculo definidos no EnumTipoObstaculos.
	 * 
	 * return tipoObstaculo
	 */
	public EnumTipoObstaculo getTipoObstaculo() {
		return tipoObstaculo;
	}

	/**
	 * Seta o tipo de obstaculo.
	 */
	public void setTipoObstaculo(EnumTipoObstaculo Obstaculo) {
		this.tipoObstaculo = Obstaculo;
	}

	@Override
	/**
	 * Converte o tipos de obst�culos definidos no EnumTipoObstaculo para
	 * String.
	 */
	public String toString() {

		switch (tipoObstaculo) {
		case ENTRADA:
			return "S";
		case ARMADILHA:
			return "T";
		case ESPACO_BRANCO:
			return ".";
		case PAREDE:
			return "#";
		case QUEIJO:
			return "C";
		case SAIDA:
			return "E";
		}

		return "";

	}
}
