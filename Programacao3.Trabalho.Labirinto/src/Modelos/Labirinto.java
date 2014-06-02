package Modelos;

import java.util.LinkedList;
import java.util.TreeMap;

/**
 * Modelo do labirinto.
 */
public class Labirinto implements Cloneable{
 

    private Rato rato;

    /**
     * Inst�ncia uma TreeMap que pega as direcoes percorridas pelo rato.
     */
    private TreeMap<Integer, TreeMap<Integer, Obstaculo>> obstaculos;
    private LinkedList<LinkedList<EnumTipoObstaculo>> matrizObstaculos;
    
    public Labirinto(TreeMap<Integer, TreeMap<Integer, Obstaculo>> obstaculos,
            Rato rato,LinkedList<LinkedList<EnumTipoObstaculo>> matrizObstaculos) {
        this.obstaculos = obstaculos;
        this.rato = rato;
        this.matrizObstaculos = matrizObstaculos;
    }
    
    
    public Object clone() {
        try {
            return super.clone();
        }
        catch (CloneNotSupportedException e) {
            // This should never happen
            throw new InternalError(e.toString());
        }
    }

    @SuppressWarnings("unused")
    private Labirinto() {

    }

    public Rato getRato() {
        return rato;
    }

    public TreeMap<Integer, TreeMap<Integer, Obstaculo>> getObstaculos() {
        return obstaculos;
    }

    public int getNoColunas() {
        return obstaculos.get(obstaculos.lastKey()).size();
    }

    public int getNoLinhas() {
        return obstaculos.size();
    }

    /**
     * @return the matrizObstaculos
     */
    public LinkedList<LinkedList<EnumTipoObstaculo>> getMatrizObstaculos() {
        return matrizObstaculos;
    }

}
