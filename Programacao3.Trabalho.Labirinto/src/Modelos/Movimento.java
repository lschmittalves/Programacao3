

package Modelos;


public class Movimento {
    
    private EnumDirecoes direcoes;
    private int posX;
    private int posY;

    public Movimento(EnumDirecoes direcoes, int posX, int posY) {
        this.direcoes = direcoes;
        this.posX = posX;
        this.posY = posY;
    }

    /**
     * @return the direcoes
     */
    public EnumDirecoes getDirecoes() {
        return direcoes;
    }

    /**
     * @return the posX
     */
    public int getPosX() {
        return posX;
    }

    /**
     * @return the posY
     */
    public int getPosY() {
        return posY;
    }
    
    
    
}
