package AcessoDados;

import java.util.List;

/** Encapsula camada de persistencia do labirinto. */
public interface IRepositorioLabirinto {
	public List<String> lerLabirinto(String diretorio) throws Exception;

}
