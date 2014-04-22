package UI;

import Modelos.EnumCores;
import Modelos.IRatoListener;
import Modelos.Labirinto;
import RegraNegocio.Fachada;
import RegraNegocio.IFachada;
import javax.swing.JOptionPane;

public class LabirintoUI implements IRatoListener {

    private String diretorioLabirinto;

    @SuppressWarnings("unused")
	private LabirintoUI() {
    }

    LabirintoUI(String diretorioLabirinto) {
        this.diretorioLabirinto = diretorioLabirinto;
    }

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
    public void onChangeColor(EnumCores newColor) {
        System.out.println();
        System.out.println("Uiii, mudei de cor agora estou " + newColor);
    }

    @Override
    public void onDead() {
        System.out.println();
        System.out.println("Uiii, morri...");
    }

    @Override
    public void onEat() {
        System.out.println();
        System.out.println("Hmmm, totoso...");
    }

    @Override
    public void onFinish() {
        System.out.println();
        System.out.println("Ahhhh, cabo.");
    }

}
