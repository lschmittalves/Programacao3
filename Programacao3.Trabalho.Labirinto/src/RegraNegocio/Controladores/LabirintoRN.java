package RegraNegocio.Controladores;

import AcessoDados.IRepositorioLabirinto;
import AcessoDados.LabirintoDAO;
import Modelos.EnumTipoObstaculo;
import Modelos.Labirinto;
import Modelos.Obstaculo;
import RegraNegocio.Factory.LabirintoFactory;
import java.io.File;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Possue metodos que controlam as funcoes relativas ao objeto labirinto.
 */
public class LabirintoRN {

    public Modelos.Labirinto criar(String diretorio) throws Exception {
        /**
         * Se o arquivo labirinto nao existe no diretorio especificado, lanca
         * excecao.
         */
        if (!new File(diretorio).exists()) {
            throw new RuntimeException("Arquivo '" + diretorio
                    + "' n�o encontrado.");
        }
        /**
         * Se nao e possivel ler o arquivo labirinto, lanca excecao.
         */
        if (!new File(diretorio).canRead()) {
            throw new RuntimeException("Arquivo '" + diretorio
                    + "' nao pode ser aberto.");
        }

        IRepositorioLabirinto daoLabirinto = new LabirintoDAO();

        LinkedList<String> labirinto = (LinkedList<String>) daoLabirinto
                .lerLabirinto(diretorio);
        LabirintoFactory labirintoFactory = new LabirintoFactory();

        Modelos.Labirinto objLabirinto = labirintoFactory
                .gerarLabirinto(labirinto);

        return objLabirinto;
    }

    /**
     * Metodo que pega a posicao inicial do labirinto.
     *
     * @param Labirinto labirinto
     *
     * @throws RuntimeException
	 *
     */
    public Obstaculo getPosicaoInicial(Labirinto labirinto)
            throws RuntimeException {
        /**
         * Se labirinto e nulo, lanca excecao.
         */
        if (labirinto == null) {
            throw new RuntimeException("Parametro labirinto nao pode ser nulo.");
        }
        /**
         * Se o labirinto estiver nulo ou vazio de obstaculos, lanca excecao.
         */
        if (labirinto.getObstaculos() == null
                || labirinto.getObstaculos().size() == 0) {
            throw new RuntimeException("Labirinto nao possui obstaculos.");
        }

        for (Map.Entry<Integer, TreeMap<Integer, Obstaculo>> linha : labirinto
                .getObstaculos().entrySet()) {

            for (Map.Entry<Integer, Obstaculo> coluna : linha.getValue()
                    .entrySet()) {

                if (coluna.getValue().getTipoObstaculo() == EnumTipoObstaculo.ENTRADA) {
                    return coluna.getValue();
                }
            }
        }

        return null;

    }

    /**
     * Gera uma string para impressao do labirinto.
     *
     * @throws RuntimeException
     *
     *
     */
    public StringBuilder labirintoToPrint(Labirinto labirinto)
            throws RuntimeException {

        StringBuilder strLabirinto = new StringBuilder();
        // Se labirinto e nulo, lanca excecao.
        if (labirinto == null) {
            throw new RuntimeException("Parametro labirinto nao pode ser nulo.");
        }
        // Se o labirinto estiver nulo ou vazio de obstaculos, lanca excecao.
        if (labirinto.getObstaculos() == null
                || labirinto.getObstaculos().isEmpty()) {
            throw new RuntimeException("Labirinto n�o possui obst�culos.");
        }

        for (Map.Entry<Integer, TreeMap<Integer, Obstaculo>> linha : labirinto
                .getObstaculos().entrySet()) {

            int Y = linha.getKey();

            for (Map.Entry<Integer, Obstaculo> coluna : linha.getValue()
                    .entrySet()) {

                int X = coluna.getKey();

                if (labirinto.getRato().getPosicaoX() == X
                        && labirinto.getRato().getPosicaoY() == Y) {
                    // System.out.println("X:"+X+"Y:"+Y+"-M");
                    strLabirinto.append('M');
                    continue;
                }

                // System.out.println("X:"+X+"Y:"+Y+"-"+coluna.getValue().toString());
                strLabirinto.append(coluna.getValue().toString());

            }
            strLabirinto.append("\n");
        }

        return strLabirinto;
    }

    /**
     * Lan�a excecao se o labirinto nao for valido.
     *
     * @param Labirinto labirinto
     *
     * @param posX
     *
     * @param posY
     *
     * @throws RuntimeException
     */
    public Obstaculo getObstaculoLabirinto(Labirinto labirinto, int posX,
            int posY) throws RuntimeException {
        // Se labirinto e nulo, lanca excecao.
        if (labirinto == null) {
            throw new RuntimeException("Parametro labirinto nao pode ser nulo.");
        }
        // Se o labirinto estiver nulo ou vazio de obstaculos, lanca excecao.
        if (labirinto.getObstaculos() == null
                || labirinto.getObstaculos().isEmpty()) {
            throw new RuntimeException("Labirinto nao possui obstaculos.");
        }

        if (labirinto.getObstaculos().containsKey(posY)) {

            if (labirinto.getObstaculos().get(posY).containsKey(posX)) {
                return labirinto.getObstaculos().get(posY).get(posX);
            }
        }

        return null;
    }
}
