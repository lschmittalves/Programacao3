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

public class LabirintoRN {

    public Modelos.Labirinto criar(String diretorio) throws Exception {

        if (!new File(diretorio).exists()) {
            throw new RuntimeException("Arquivo '" + diretorio + "' não encontrado.");
        }

        if (!new File(diretorio).canRead()) {
            throw new RuntimeException("Arquivo '" + diretorio + "' não pode ser aberto.");
        }

        IRepositorioLabirinto daoLabirinto = new LabirintoDAO();

        LinkedList<String> labirinto = (LinkedList<String>) daoLabirinto.lerLabirinto(diretorio);
        LabirintoFactory labirintoFactory = new LabirintoFactory();

        Modelos.Labirinto objLabirinto = labirintoFactory.gerarLabirinto(labirinto);

        return objLabirinto;
    }

    public Obstaculo getPosicaoInicial(Labirinto labirinto) throws RuntimeException {

        if (labirinto == null) {
            throw new RuntimeException("Parâmetro labirinto não pode ser nulo.");
        }

        if (labirinto.getObstaculos() == null || labirinto.getObstaculos().size() == 0) {
            throw new RuntimeException("Labirinto não possui obstaculos.");
        }

        for (Map.Entry<Integer, TreeMap<Integer, Obstaculo>> linha : labirinto.getObstaculos().entrySet()) {

            for (Map.Entry<Integer, Obstaculo> coluna : linha.getValue().entrySet()) {

                if (coluna.getValue().getTipoObstaculo() == EnumTipoObstaculo.ENTRADA) {
                    return coluna.getValue();
                }
            }
        }

        return null;

    }

    public StringBuilder labirintoToPrint(Labirinto labirinto) throws RuntimeException {

        StringBuilder strLabirinto = new StringBuilder();

        if (labirinto == null) {
            throw new RuntimeException("Parâmetro labirinto não pode ser nulo.");
        }

        if (labirinto.getObstaculos() == null || labirinto.getObstaculos().isEmpty()) {
            throw new RuntimeException("Labirinto não possui obstaculos.");
        }

        for (Map.Entry<Integer, TreeMap<Integer, Obstaculo>> linha : labirinto.getObstaculos().entrySet()) {

            int Y = linha.getKey();

            for (Map.Entry<Integer, Obstaculo> coluna : linha.getValue().entrySet()) {

                int X = coluna.getKey();

                if (labirinto.getRato().getPosicaoX() == X && labirinto.getRato().getPosicaoY() == Y) {
//                    System.out.println("X:"+X+"Y:"+Y+"-M");
                    strLabirinto.append('M');
                    continue;
                }

      //        System.out.println("X:"+X+"Y:"+Y+"-"+coluna.getValue().toString());
              strLabirinto.append(coluna.getValue().toString());

            }
            strLabirinto.append("\n");
        }

        return strLabirinto;
    }

    public Obstaculo getObstaculoLabirinto(Labirinto labirinto, int posX, int posY) throws RuntimeException {

        if (labirinto == null) {
            throw new RuntimeException("Parâmetro labirinto não pode ser nulo.");
        }

        if (labirinto.getObstaculos() == null || labirinto.getObstaculos().isEmpty()) {
            throw new RuntimeException("Labirinto não possui obstaculos.");
        }

        if (labirinto.getObstaculos().containsKey(posY)) {

            if (labirinto.getObstaculos().get(posY).containsKey(posX)) {
                return labirinto.getObstaculos().get(posY).get(posX);
            }
        }

        return null;
    }
}
