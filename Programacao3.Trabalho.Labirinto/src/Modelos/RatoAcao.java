/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelos;

/**
 *
 * @author leonardo.alves
 */
public class RatoAcao {
  
    private EnumEventos evento;
    private EnumDirecoes direcoes;
    private int posX;
    private int posY;

    private RatoAcao(){}
   
    
    public RatoAcao(EnumEventos evento, int posX, int posY) {
        this.evento = evento;
        this.posX = posX;
        this.posY = posY;
    }
    
    public RatoAcao(EnumEventos evento,EnumDirecoes direcoes, int posX, int posY) {
        this.evento = evento;
        this.posX = posX;
        this.posY = posY;
        this.direcoes = direcoes;
    }

    /**
     * @return the direcoes
     */
    public EnumEventos getEvento() {
        return evento;
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

    /**
     * @return the ratoDirecoes
     */
    public EnumDirecoes getDirecoes() {
        return direcoes;
    }
}
