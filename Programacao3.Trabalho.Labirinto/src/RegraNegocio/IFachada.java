package RegraNegocio;

import Modelos.Labirinto;
import Modelos.Obstaculo;

/** Encapsulamento da regra de negocio. */
public interface IFachada {
	/** Propriedade para criar o labirinto. */
	public Modelos.Labirinto criarLabirinto(String diretorio) throws Exception;

	/** Propriedade que pega a posicao de entrada do labirinto. */
	public Obstaculo getPosicaoInicialLabirinto(Labirinto labirinto);

	/**/
	public StringBuilder labirintoToPrint(Labirinto labirinto);

	/** Procura a saida do labirinto. */
	public void procurarSaida(Labirinto labirinto);
}
