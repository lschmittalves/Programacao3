package UI.Console;

import Modelos.EnumCores;
import Modelos.IRatoListener;
import Modelos.Labirinto;

import RegraNegocio.Fachada;
import RegraNegocio.IFachada;
import javax.swing.JOptionPane;

/** Camada de aplicacao labirinto. */
public class LabirintoUI implements IRatoListener {

	private String diretorioLabirinto;

	@SuppressWarnings("unused")
	private LabirintoUI() {
	}

	LabirintoUI(String diretorioLabirinto) {
		this.diretorioLabirinto = diretorioLabirinto;
	}

	/** Inicia o labirinto. */
	public void run() {

		try {
			IFachada regraNegocio = new Fachada();

			Labirinto labirinto = regraNegocio.criarLabirinto(diretorioLabirinto);
			labirinto.getRato().addListener(this);
			System.out.println(regraNegocio.labirintoToPrint(labirinto));
			regraNegocio.procurarSaida(labirinto);

		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

	}

	@Override
	/** Metodo para locomocao do rato. A execucao e interrompida caso ultrapassar o tempo de 150 milisegundos.*/
	public void onMove(StringBuilder labirinto) {
		System.out.println();
		System.out.println(labirinto);

		try {
			Thread.sleep(150);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	@Override
	/**
	 * Metodo que altera a cor do rato. Esse metodo e disparado apos o rato ter
	 * comido 3 queijos.
	 * 
	 * @param EnumCores newColor
	 */
	public void onChangeColor(EnumCores newColor) {
		System.out.println();
		System.out.println("Uiii, mudei de cor agora estou " + newColor);
	}

	@Override
	/**
	 * Evento disparado quando ele cai em uma armadilha ou quando o tempo e
	 * esgotado.
	 */
	public void onDead() {
		System.out.println();
		System.out.println("Uiii, morri...");
	}

	@Override
	/** Evento disparado quando o rato come um queijo. */
	public void onEat(int noQueijos) {
		System.out.println();
		System.out.println("Hmmm, totoso...");
                System.out.println("QUEIJOS COMIDOS: "+noQueijos);
	}

	@Override
	/** Evento disparado quando o rato acha a saida do labirinto. */
	public void onFinish() {
		System.out.println();
		System.out.println("Ahhhh, cabo.");
	}

   
}
