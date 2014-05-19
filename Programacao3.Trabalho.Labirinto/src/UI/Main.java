/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import UI.Slick.LabirintoSlickMain;


/**
 * Classe que roda a leitura do arquivo.
 */
public class Main {

    /**
     * Metodo onde e lido o diretorio em que o labirinto foi salvo.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
             LabirintoSlickMain.getInstance().run();               
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*	new LabirintoUI(
         "C:\\Users\\fernanda.vitorio\\Documents\\NetBeansProjects\\Programacao3\\Programacao3.Trabalho.Labirinto\\labirinto")
         .run();*/
    }

}
