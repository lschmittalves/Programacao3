package RegraNegocio;

import Modelos.Labirinto;
import Modelos.Obstaculo;

public interface IFachada {
     public Modelos.Labirinto criarLabirinto(String diretorio) throws Exception ;
     public Obstaculo getPosicaoInicialLabirinto(Labirinto labirinto);
     public StringBuilder labirintoToPrint(Labirinto labirinto);
     public void procurarSaida(Labirinto labirinto);
}
