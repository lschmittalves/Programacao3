package AcessoDados;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class LabirintoDAO implements IRepositorioLabirinto {

    @Override
    public List<String> lerLabirinto(String diretorio) throws Exception{     
        List<String> labirinto = new LinkedList<>();            

        File arquivoLabirinto = new File(diretorio);

        try (FileReader fileReader = new FileReader(arquivoLabirinto)) {

            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            
                String linhaLabirinto = bufferedReader.readLine();
                
                while (linhaLabirinto != null && !linhaLabirinto.trim().equals("")) {
               
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
