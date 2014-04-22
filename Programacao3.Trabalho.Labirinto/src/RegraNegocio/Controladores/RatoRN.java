package RegraNegocio.Controladores;

import Modelos.EnumCores;
import Modelos.EnumDirecoes;
import Modelos.EnumTipoObstaculo;
import Modelos.Labirinto;
import Modelos.Obstaculo;
import Modelos.Rato;
import org.joda.time.DateTime;
import org.joda.time.Minutes;

public class RatoRN {

	public void procurarSaida(Labirinto labirinto) throws RuntimeException {

		procurarSaida(labirinto, null, null);
	}

	@SuppressWarnings("incomplete-switch")
	private void procurarSaida(Labirinto labirinto, Obstaculo ultimoObstaculo,
			EnumDirecoes ultimoMovimento) throws RuntimeException {

		if (tempoEsgotado(labirinto.getRato())) {
			return;
		}
		
		if (ultimoObstaculo != null) {
			switch (ultimoObstaculo.getTipoObstaculo()) {
			case ARMADILHA:
				matarRato(labirinto.getRato());
				return;
			case QUEIJO:
				comerQueijo(labirinto.getRato());
				new LabirintoRN().getObstaculoLabirinto(labirinto,
						labirinto.getRato().getPosicaoX(),
						labirinto.getRato().getPosicaoY()).setTipoObstaculo(
						EnumTipoObstaculo.ESPACO_BRANCO);
				break;
			case SAIDA:
				if (labirinto.getRato().getRatoListener() != null) {
					labirinto.getRato().getRatoListener().onFinish();
				}
				return;
			}
		}

		if (!porCaminhoLivre(labirinto)) {
			if (labirinto.getRato().getLastDirection()==null && labirinto.getRato().getDirecoesPercorridas().size() !=0) {
				matarRato(labirinto.getRato());
				return;
			}
			voltarMesmoCaminho(labirinto);
		}

		procurarSaida(labirinto, new LabirintoRN().getObstaculoLabirinto(
				labirinto, labirinto.getRato().getPosicaoX(), labirinto
						.getRato().getPosicaoY()), ultimoMovimento);

	}

	private boolean porCaminhoLivre(Labirinto labirinto) {
		if (ratoPodeAndar(labirinto, EnumDirecoes.DIREITA)
				&& !ratoJaPassou(labirinto.getRato(), EnumDirecoes.DIREITA)) {
			moverRato(labirinto, EnumDirecoes.DIREITA,true);
			return true;
		} else if (ratoPodeAndar(labirinto, EnumDirecoes.ESQUERDA)
				&& !ratoJaPassou(labirinto.getRato(), EnumDirecoes.ESQUERDA)) {
			moverRato(labirinto, EnumDirecoes.ESQUERDA,true);
			return true;
		} else if (ratoPodeAndar(labirinto, EnumDirecoes.FRENTE)
				&& !ratoJaPassou(labirinto.getRato(), EnumDirecoes.FRENTE)) {
			moverRato(labirinto, EnumDirecoes.FRENTE,true);
			return true;
		} else if (ratoPodeAndar(labirinto, EnumDirecoes.ATRAS)
				&& !ratoJaPassou(labirinto.getRato(), EnumDirecoes.ATRAS)) {
			moverRato(labirinto, EnumDirecoes.ATRAS,true);
			return true;
		}
		return false;
	}

	private void voltarMesmoCaminho(Labirinto labirinto) {

		if (labirinto.getRato().getLastDirection() == EnumDirecoes.ESQUERDA) {
			labirinto.getRato().removerUltimaDirecao();
			moverRato(labirinto, EnumDirecoes.DIREITA,false);

		} else if (labirinto.getRato().getLastDirection() == EnumDirecoes.DIREITA) {
			labirinto.getRato().removerUltimaDirecao();
			moverRato(labirinto, EnumDirecoes.ESQUERDA,false);

		} else if (labirinto.getRato().getLastDirection() == EnumDirecoes.ATRAS) {
			labirinto.getRato().removerUltimaDirecao();
			moverRato(labirinto, EnumDirecoes.FRENTE,false);

		} else if (labirinto.getRato().getLastDirection() == EnumDirecoes.FRENTE) {
			labirinto.getRato().removerUltimaDirecao();
			moverRato(labirinto, EnumDirecoes.ATRAS,false);

		}
	}

	private void moverRato(Labirinto labirinto, EnumDirecoes direcao,
			boolean gravarDirecao) {
		int posXNovaRato = 0;
		int posYNovaRato = 0;

		switch (direcao) {
		case FRENTE:
			posXNovaRato = labirinto.getRato().getPosicaoX();
			posYNovaRato = labirinto.getRato().getPosicaoY() - 1;
			break;
		case ATRAS:
			posXNovaRato = labirinto.getRato().getPosicaoX();
			posYNovaRato = labirinto.getRato().getPosicaoY() + 1;
			break;
		case DIREITA:
			posXNovaRato = labirinto.getRato().getPosicaoX() + 1;
			posYNovaRato = labirinto.getRato().getPosicaoY();
			break;
		case ESQUERDA:
			posXNovaRato = labirinto.getRato().getPosicaoX() - 1;
			posYNovaRato = labirinto.getRato().getPosicaoY();
			break;

		}

		labirinto.getRato().setPosicaoX(posXNovaRato);
		labirinto.getRato().setPosicaoY(posYNovaRato);

		if (gravarDirecao)
			labirinto.getRato().addDirecaoPercorrida(direcao, posXNovaRato,
					posYNovaRato);

		if (labirinto.getRato().getRatoListener() != null) {
			labirinto.getRato().getRatoListener()
					.onMove(new LabirintoRN().labirintoToPrint(labirinto));
		}
	}

	private boolean ratoPodeAndar(Labirinto labirinto, EnumDirecoes direcao) {

		int posXDesejadaRato = 0;
		int posYDesejadaRato = 0;

		switch (direcao) {
		case FRENTE:
			posXDesejadaRato = labirinto.getRato().getPosicaoX();
			posYDesejadaRato = labirinto.getRato().getPosicaoY() - 1;
			break;
		case ATRAS:
			posXDesejadaRato = labirinto.getRato().getPosicaoX();
			posYDesejadaRato = labirinto.getRato().getPosicaoY() + 1;
			break;
		case DIREITA:
			posXDesejadaRato = labirinto.getRato().getPosicaoX() + 1;
			posYDesejadaRato = labirinto.getRato().getPosicaoY();
			break;
		case ESQUERDA:
			posXDesejadaRato = labirinto.getRato().getPosicaoX() - 1;
			posYDesejadaRato = labirinto.getRato().getPosicaoY();
			break;

		}

		Obstaculo obstaculoCaminho = new LabirintoRN().getObstaculoLabirinto(
				labirinto, posXDesejadaRato, posYDesejadaRato);

		if (obstaculoCaminho == null) {
			return false;
		}

		switch (obstaculoCaminho.getTipoObstaculo()) {
		case ARMADILHA:
		case PAREDE:
		case ENTRADA:
			return false;
		case ESPACO_BRANCO:
		case QUEIJO:
		case SAIDA:
			return true;

		}

		return false;
	}

	private boolean ratoJaPassou(Rato rato, EnumDirecoes direcao) {
		int posXDesejadaRato = 0;
		int posYDesejadaRato = 0;

		switch (direcao) {
		case FRENTE:
			posXDesejadaRato = rato.getPosicaoX();
			posYDesejadaRato = rato.getPosicaoY() - 1;
			break;
		case ATRAS:
			posXDesejadaRato = rato.getPosicaoX();
			posYDesejadaRato = rato.getPosicaoY() + 1;
			break;
		case DIREITA:
			posXDesejadaRato = rato.getPosicaoX() + 1;
			posYDesejadaRato = rato.getPosicaoY();
			break;
		case ESQUERDA:
			posXDesejadaRato = rato.getPosicaoX() - 1;
			posYDesejadaRato = rato.getPosicaoY();
			break;

		}

		return getDirecaoJaExecutada(rato, posXDesejadaRato, posYDesejadaRato) != null;
	}

	private EnumDirecoes getDirecaoJaExecutada(Rato rato, int posX, int posY)
			throws RuntimeException {

		if (rato == null) {
			throw new RuntimeException("Parâmetro rato não pode ser nulo.");
		}

		if (rato.getDirecoesPercorridas() == null) {
			throw new RuntimeException("Rato não possui obstaculos.");
		}

		if (rato.getDirecoesPercorridas().containsKey(posY)) {

			if (rato.getDirecoesPercorridas().get(posY).containsKey(posX)) {
				return rato.getDirecoesPercorridas().get(posY).get(posX);
			}
		}

		return null;
	}

	private boolean tempoEsgotado(Rato rato) {

		if (Minutes.minutesBetween(rato.getDataInicio(), DateTime.now())
				.getMinutes() >= 5) {
			matarRato(rato);
			return true;
		}

		return false;
	}

	private void matarRato(Rato rato) {
		if (rato.getRatoListener() != null) {
			rato.getRatoListener().onDead();
		}
		rato.setVivo(false);
	}

	private void comerQueijo(Rato rato) {

		if (rato.getRatoListener() != null) {
			rato.getRatoListener().onEat();
		}
		rato.incrQueijosComidos();

		if (rato.getQueijosComidos() >= 3) {
			if (rato.getRatoListener() != null) {
				rato.getRatoListener().onChangeColor(EnumCores.VERMELHO);
			}
		}
	}
}
