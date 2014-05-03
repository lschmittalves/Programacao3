package RegraNegocio.Controladores;

import Modelos.EnumCores;
import Modelos.EnumDirecoes;
import Modelos.EnumTipoObstaculo;
import Modelos.Labirinto;
import Modelos.Obstaculo;
import Modelos.Rato;
import org.joda.time.DateTime;
import org.joda.time.Minutes;

/**
 * Possue metodos que controlam as funcoes relativas ao objeto rato.
 */
public class RatoRN {

    public void procurarSaida(Labirinto labirinto) throws RuntimeException {

        procurarSaida(labirinto, null, null);
    }

    @SuppressWarnings("incomplete-switch")
    /**
     * Metodo utilizado para o rato procurar a saida do labirinto.
     *
     * @param Labirinto labirinto
     *
     * @param Obstaculo ultimo obstaculo
     *
     * @param EnumDirecoes ultimoMovimento
     *
     * @throws RuntimeException
     */
    private void procurarSaida(Labirinto labirinto, Obstaculo ultimoObstaculo,
            EnumDirecoes ultimoMovimento) throws RuntimeException {

        if (tempoEsgotado(labirinto.getRato())) {
            return;
        }

//            if (ultimoObstaculo != null) {
//                switch (ultimoObstaculo.getTipoObstaculo()) {
//                    // Caso seja uma armadilha, o rato e morto.
//                    case ARMADILHA:
//                        matarRato(labirinto.getRato());
//                        return true;
//            // Se for um queijo, o rato come o mesmo e a posicao em que
//                    // estava o queijo fica em branco.
//                    case QUEIJO:
//                        comerQueijo(labirinto.getRato());
//                        new LabirintoRN().getObstaculoLabirinto(labirinto,
//                                labirinto.getRato().getPosicaoX(),
//                                labirinto.getRato().getPosicaoY()).setTipoObstaculo(
//                                        EnumTipoObstaculo.ESPACO_BRANCO);
//                        break;
//            // Se for a saida o evento onFinish e disparado, o que indica que o
//                    // rato chegou ao fim do labirinto.
//                    case SAIDA:
//                        if (labirinto.getRato().getRatoListener() != null) {
//                            labirinto.getRato().getRatoListener().onFinish();
//                        }
//                        return true;
//                }
//            }
        if (validaUltimoObstaculo(ultimoObstaculo, labirinto)) {
            return;
        }

        // Se o caminhado nao estiver livre, o rato volta pelo mesmo caminho.
        if (!porCaminhoLivre(labirinto)) {
			// Se a ultima direcao percorrida for nula e o quantidade de
            // direcoes percorridas for diferente de zero, matar o rato.
            if (labirinto.getRato().getLastDirection() == null
                    && labirinto.getRato().getDirecoesPercorridas().size() != 0) {
                matarRato(labirinto.getRato());
                return;
            }
            voltarMesmoCaminho(labirinto);
        }

        procurarSaida(labirinto, new LabirintoRN().getObstaculoLabirinto(
                labirinto, labirinto.getRato().getPosicaoX(), labirinto
                .getRato().getPosicaoY()), ultimoMovimento);

    }

    private boolean validaUltimoObstaculo(Obstaculo ultimoObstaculo, Labirinto labirinto) throws RuntimeException {
        // Se o ultimo obstaculo e dirente de nulo, e feito um switch no qual e
        // verificado qual o tipo de obstaculo.
        if (ultimoObstaculo != null) {
            switch (ultimoObstaculo.getTipoObstaculo()) {
                // Caso seja uma armadilha, o rato e morto.
                case ARMADILHA:
                    matarRato(labirinto.getRato());
                    return true;
            // Se for um queijo, o rato come o mesmo e a posicao em que
                // estava o queijo fica em branco.
                case QUEIJO:
                    comerQueijo(labirinto.getRato());
                    new LabirintoRN().getObstaculoLabirinto(labirinto,
                            labirinto.getRato().getPosicaoX(),
                            labirinto.getRato().getPosicaoY()).setTipoObstaculo(
                                    EnumTipoObstaculo.ESPACO_BRANCO);
                    break;
            // Se for a saida o evento onFinish e disparado, o que indica que o
                // rato chegou ao fim do labirinto.
                case SAIDA:
                    if (labirinto.getRato().getRatoListener() != null) {
                        labirinto.getRato().getRatoListener().onFinish();
                    }
                    return true;
            }
        }
        return false;
    }

    /**
     * Metodo que verifica se o caminho esta livre e move o rato. E verificado
     * se o rato pode andar para a direcao especificada e se ele ja nao passou
     * pela mesma. Caso as verificacoes retornem true, o rato e movido, caso
     * retornem false o rato permanece na mesma posicao.
     *
     * @param Labirinto labirinto
     */
    private boolean porCaminhoLivre(Labirinto labirinto) {
        if (ratoPodeAndar(labirinto, EnumDirecoes.DIREITA)
                && !ratoJaPassou(labirinto.getRato(), EnumDirecoes.DIREITA)) {
            moverRato(labirinto, EnumDirecoes.DIREITA, true);
            return true;
        } else if (ratoPodeAndar(labirinto, EnumDirecoes.ESQUERDA)
                && !ratoJaPassou(labirinto.getRato(), EnumDirecoes.ESQUERDA)) {
            moverRato(labirinto, EnumDirecoes.ESQUERDA, true);
            return true;
        } else if (ratoPodeAndar(labirinto, EnumDirecoes.FRENTE)
                && !ratoJaPassou(labirinto.getRato(), EnumDirecoes.FRENTE)) {
            moverRato(labirinto, EnumDirecoes.FRENTE, true);
            return true;
        } else if (ratoPodeAndar(labirinto, EnumDirecoes.ATRAS)
                && !ratoJaPassou(labirinto.getRato(), EnumDirecoes.ATRAS)) {
            moverRato(labirinto, EnumDirecoes.ATRAS, true);
            return true;
        }
        return false;
    }

    /**
     * Metodo utilizado quando nao ha mais opcoes para o rato andar nas quais
     * ele ja nao tenha passado. E verificada a ultima direcao percorrida pelo
     * rato, a mesma e removida e o rato anda para a direcao contraria.
     *
     * @param Labirinto labirinto
     */
    private void voltarMesmoCaminho(Labirinto labirinto) {

        if (labirinto.getRato().getLastDirection() == EnumDirecoes.ESQUERDA) {
            labirinto.getRato().removerUltimaDirecao();
            moverRato(labirinto, EnumDirecoes.DIREITA, false);

        } else if (labirinto.getRato().getLastDirection() == EnumDirecoes.DIREITA) {
            labirinto.getRato().removerUltimaDirecao();
            moverRato(labirinto, EnumDirecoes.ESQUERDA, false);

        } else if (labirinto.getRato().getLastDirection() == EnumDirecoes.ATRAS) {
            labirinto.getRato().removerUltimaDirecao();
            moverRato(labirinto, EnumDirecoes.FRENTE, false);

        } else if (labirinto.getRato().getLastDirection() == EnumDirecoes.FRENTE) {
            labirinto.getRato().removerUltimaDirecao();
            moverRato(labirinto, EnumDirecoes.ATRAS, false);

        }
    }

    /**
     * Metodo para mover o rato. Para cada direcao em que o rato anda e pegada a
     * posicao x (linha) e posicao y (coluna), setadas como nova posicao e
     * gravadas.
     *
     * @param Labirinto labirinto
     *
     * @param EnumDirecoes direcao
     *
     * @param gravarDirecao
     */
    private void moverRato(Labirinto labirinto, EnumDirecoes direcao,
            boolean gravarDirecao) {
        int posXNovaRato = proximaPosicaoEmX(direcao, labirinto.getRato());
        int posYNovaRato = proximaPosicaoEmY(direcao, labirinto.getRato());

        //		switch (direcao) {
//		case FRENTE:
//			posXNovaRato = labirinto.getRato().getPosicaoX();
//			posYNovaRato = labirinto.getRato().getPosicaoY() - 1;
//			break;
//		case ATRAS:
//			posXNovaRato = labirinto.getRato().getPosicaoX();
//			posYNovaRato = labirinto.getRato().getPosicaoY() + 1;
//			break;
//		case DIREITA:
//			posXNovaRato = labirinto.getRato().getPosicaoX() + 1;
//			posYNovaRato = labirinto.getRato().getPosicaoY();
//			break;
//		case ESQUERDA:
//			posXNovaRato = labirinto.getRato().getPosicaoX() - 1;
//			posYNovaRato = labirinto.getRato().getPosicaoY();
//			break;
//
//		}
        labirinto.getRato().setPosicaoX(posXNovaRato);
        labirinto.getRato().setPosicaoY(posYNovaRato);

        if (gravarDirecao) {
            labirinto.getRato().addDirecaoPercorrida(direcao, posXNovaRato,
                    posYNovaRato);
        }

        if (labirinto.getRato().getRatoListener() != null) {
            labirinto.getRato().getRatoListener()
                    .onMove(new LabirintoRN().labirintoToPrint(labirinto));
        }
    }

    private int proximaPosicaoEmY(EnumDirecoes direcao, Rato rato) {

        int posYNovaRato = 0;
        switch (direcao) {
            case FRENTE:
                posYNovaRato = rato.getPosicaoY() - 1;
                break;
            case ATRAS:
                posYNovaRato = rato.getPosicaoY() + 1;
                break;
            case DIREITA:
                posYNovaRato = rato.getPosicaoY();
                break;
            case ESQUERDA:
                posYNovaRato = rato.getPosicaoY();
                break;

        }
        return posYNovaRato;
    }

    private int proximaPosicaoEmX(EnumDirecoes direcao, Rato rato) {
        int posXNovaRato = 0;
        switch (direcao) {
            case FRENTE:
                posXNovaRato = rato.getPosicaoX();
                break;
            case ATRAS:
                posXNovaRato = rato.getPosicaoX();
                break;
            case DIREITA:
                posXNovaRato = rato.getPosicaoX() + 1;
                break;
            case ESQUERDA:
                posXNovaRato = rato.getPosicaoX() - 1;
                break;
        }
        return posXNovaRato;
    }

    /**
     * Move o rato para uma das direcoes especificadas.
     */
    private boolean ratoPodeAndar(Labirinto labirinto, EnumDirecoes direcao) {

        int posXDesejadaRato = proximaPosicaoEmX(direcao, labirinto.getRato());
        int posYDesejadaRato = proximaPosicaoEmY(direcao, labirinto.getRato());

//        switch (direcao) {
//            case FRENTE:
//                posXDesejadaRato = labirinto.getRato().getPosicaoX();
//                posYDesejadaRato = labirinto.getRato().getPosicaoY() - 1;
//                break;
//            case ATRAS:
//                posXDesejadaRato = labirinto.getRato().getPosicaoX();
//                posYDesejadaRato = labirinto.getRato().getPosicaoY() + 1;
//                break;
//            case DIREITA:
//                posXDesejadaRato = labirinto.getRato().getPosicaoX() + 1;
//                posYDesejadaRato = labirinto.getRato().getPosicaoY();
//                break;
//            case ESQUERDA:
//                posXDesejadaRato = labirinto.getRato().getPosicaoX() - 1;
//                posYDesejadaRato = labirinto.getRato().getPosicaoY();
//                break;
//
//        }

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
        int posXDesejadaRato = proximaPosicaoEmX(direcao, rato);
        int posYDesejadaRato = proximaPosicaoEmY(direcao, rato);
        
//        switch (direcao) {
//            case FRENTE:
//                posXDesejadaRato = rato.getPosicaoX();
//                posYDesejadaRato = rato.getPosicaoY() - 1;
//                break;
//            case ATRAS:
//                posXDesejadaRato = rato.getPosicaoX();
//                posYDesejadaRato = rato.getPosicaoY() + 1;
//                break;
//            case DIREITA:
//                posXDesejadaRato = rato.getPosicaoX() + 1;
//                posYDesejadaRato = rato.getPosicaoY();
//                break;
//            case ESQUERDA:
//                posXDesejadaRato = rato.getPosicaoX() - 1;
//                posYDesejadaRato = rato.getPosicaoY();
//                break;
//
//        }

        return getDirecaoJaExecutada(rato, posXDesejadaRato, posYDesejadaRato) != null;
    }

    /**
     * 
     */
    private EnumDirecoes getDirecaoJaExecutada(Rato rato, int posX, int posY)
            throws RuntimeException {
        // Se nao ha um rato no labirinto, lanca excecao.
        if (rato == null) {
            throw new RuntimeException("Par�metro rato n�o pode ser nulo.");
        }
        // Se direcoes percorridas forem nulas, lanca excecao.
        if (rato.getDirecoesPercorridas() == null) {
            throw new RuntimeException("Rato n�o possui obstaculos.");
        }
        // Se direcoes percorridas contem a posicao da coluna
        if (rato.getDirecoesPercorridas().containsKey(posY)) {
			// Se direcoes percorridas tem posicao da coluna e contem posicao da
            // linha
            if (rato.getDirecoesPercorridas().get(posY).containsKey(posX)) {
                return rato.getDirecoesPercorridas().get(posY).get(posX);
            }
        }

        return null;
    }

    /**
     * Metodo que controla o tempo de execucao do labirinto. E pega a data e
     * hora em que iniciou o labirinto e caso apos 5 minutos o rato nao tiver
     * chegado ao final do labirinto, o rato e morto.
     */
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
            rato.getRatoListener().onEat(rato.getQueijosComidos());
        }
        rato.incrQueijosComidos();

        if (rato.getQueijosComidos() >= 3) {
            if (rato.getRatoListener() != null) {
                rato.getRatoListener().onChangeColor(EnumCores.VERMELHO);
            }
        }
    }
}
