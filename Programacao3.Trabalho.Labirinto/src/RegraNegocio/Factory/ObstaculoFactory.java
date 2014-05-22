/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RegraNegocio.Factory;

import Modelos.EnumTipoObstaculo;
import Modelos.Obstaculo;

// Classe ObstaculoFactory, extraida da parte do código da Classe LabirintoFactory.
public class ObstaculoFactory {

    public Obstaculo gerarObstaculo(char atual, int j, int i) throws RuntimeException {

        Obstaculo obstaculo;
        switch (atual) {
            case '.':
                obstaculo = new Obstaculo(j, i,
                        EnumTipoObstaculo.ESPACO_BRANCO);
                break;
            case '#':
                obstaculo = new Obstaculo(j, i, EnumTipoObstaculo.PAREDE);
                break;
            case 'S':
                obstaculo = new Obstaculo(j, i, EnumTipoObstaculo.ENTRADA);

                break;
            case 'E':
                obstaculo = new Obstaculo(j, i, EnumTipoObstaculo.SAIDA);
                break;
            case 'C':
                obstaculo = new Obstaculo(j, i, EnumTipoObstaculo.QUEIJO);
                break;
            case 'T':
                obstaculo = new Obstaculo(j, i,
                        EnumTipoObstaculo.ARMADILHA);
                break;
            default:
                throw new RuntimeException("Caracter inv�lido!");
        }
        return obstaculo;
    }
    

}
