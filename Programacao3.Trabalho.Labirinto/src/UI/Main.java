/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UI;

/** Classe que roda a leitura do arquivo. */
public class Main {

	/**
	 * Metodo onde e lido o diretorio em que o labirinto foi salvo.
	 * 
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		new LabirintoUI(
				"C:\\Users\\fernanda.vitorio\\workspace\\Programacao3.Trabalho.Labirinto\\labirinto")
				.run();
	}

}
