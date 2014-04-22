package RegraNegocio;

import Modelos.Labirinto;
import Modelos.Obstaculo;
import RegraNegocio.Controladores.LabirintoRN;
import RegraNegocio.Controladores.RatoRN;

public class Fachada  implements IFachada{

    private LabirintoRN _regraNegocioLabirinto;
    private RatoRN _regraNegocioRato;
    
    public Fachada() {
        this._regraNegocioLabirinto = new LabirintoRN();
        this._regraNegocioRato = new RatoRN();
    }

    @Override
    public Labirinto criarLabirinto(String diretorio) throws Exception {
      return this._regraNegocioLabirinto.criar(diretorio);
    }

    @Override
    public Obstaculo getPosicaoInicialLabirinto(Labirinto labirinto) {
     return this._regraNegocioLabirinto.getPosicaoInicial(labirinto);
    }

    @Override
    public StringBuilder labirintoToPrint(Labirinto labirinto) {
       return this._regraNegocioLabirinto.labirintoToPrint(labirinto);
    }

    @Override
    public void procurarSaida(Labirinto labirinto) {
        _regraNegocioRato.procurarSaida(labirinto);
    }

   

    
}
