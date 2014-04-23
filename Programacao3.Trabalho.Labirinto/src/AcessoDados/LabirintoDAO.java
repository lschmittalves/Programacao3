package AcessoDados;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

/** Classe que contem o metodo de leitura de cada linha do labirinto. */
public class LabirintoDAO implements IRepositorioLabirinto {

	@Override
	/**Metodo que le as linhas do labirinto informadas no arquivo txt salvo no diret�rio, 
	 * onde para cada linha e adicionado um item novo no labirinto.
	 * 
	 * @param diretorio 
	 *                diretorio que contem o arquivo txt do labirinto
	 * 
	 * @throws Exception
	 * */
	public List<String> lerLabirinto(String diretorio) throws Exception {
		List<String> labirinto = new LinkedList<>();

		File arquivoLabirinto = new File(diretorio);

		try (FileReader fileReader = new FileReader(arquivoLabirinto)) {

			try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {

				String linhaLabirinto = bufferedReader.readLine();

				while (linhaLabirinto != null
						&& !linhaLabirinto.trim().equals("")) {

					labirinto.add(linhaLabirinto);
					linhaLabirinto = bufferedReader.readLine();
				}
			}

		} catch (Exception ex) {
			throw new Exception(ex);

		}

		return labirinto;

	}

}
