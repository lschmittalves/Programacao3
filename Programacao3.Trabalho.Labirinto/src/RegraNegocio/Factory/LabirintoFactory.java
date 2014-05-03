package RegraNegocio.Factory;

import java.util.List;
import java.util.TreeMap;

import Modelos.EnumTipoObstaculo;
import Modelos.Labirinto;
import Modelos.Obstaculo;
import Modelos.Rato;

/**
 * Classe responsavel pela criacao do labirinto e objetos relacionados ao mesmo.
 */
public class LabirintoFactory {

    /**
     * Faz a leitura do texto do arquivo labirinto.
     *
     * @param List<String> labirintoTexto
     *
     * @throws RuntimeException
     */
    public Modelos.Labirinto gerarLabirinto(List<String> labirintoTexto)
            throws RuntimeException {

        TreeMap<Integer, TreeMap<Integer, Obstaculo>> obstaculos = new TreeMap<>();
        Obstaculo inicio = null;

        for (int i = 0; i < labirintoTexto.size(); i++) {

            TreeMap<Integer, Obstaculo> linha = new TreeMap<>();
            for (int j = 0; j < labirintoTexto.get(i).length(); j++) {

                char atual = labirintoTexto.get(i).charAt(j);
                Obstaculo obstaculo=  new ObstaculoFactory().gerarObstaculo(atual, j, i);

                
                
                
//                  Obstaculo obstaculo;
//        switch (atual) {
//            case '.':
//                obstaculo = new Obstaculo(j, i,
//                        EnumTipoObstaculo.ESPACO_BRANCO);
//                break;
//            case '#':
//                obstaculo = new Obstaculo(j, i, EnumTipoObstaculo.PAREDE);
//                break;
//            case 'S':
//                obstaculo = new Obstaculo(j, i, EnumTipoObstaculo.ENTRADA);
//
//                break;
//            case 'E':
//                obstaculo = new Obstaculo(j, i, EnumTipoObstaculo.SAIDA);
//                break;
//            case 'C':
//                obstaculo = new Obstaculo(j, i, EnumTipoObstaculo.QUEIJO);
//                break;
//            case 'T':
//                obstaculo = new Obstaculo(j, i,
//                        EnumTipoObstaculo.ARMADILHA);
//                break;
//            default:
//                throw new RuntimeException("Caracter inv�lido!");
//        }
                
                
             
                
                linha.put(j, obstaculo);

                if (obstaculo.getTipoObstaculo() == EnumTipoObstaculo.ENTRADA) {
                    inicio = obstaculo;
                }

                obstaculos.put(i, linha);

            }
        }

        if (inicio == null) {
            throw new RuntimeException("Labirinto n�o possui in�cio.");
        }

        Rato rato = new Rato(inicio.getPosicaoX(), inicio.getPosicaoY());
        Labirinto objLabirinto = new Labirinto(obstaculos, rato);

        return objLabirinto;
    }

    

}
