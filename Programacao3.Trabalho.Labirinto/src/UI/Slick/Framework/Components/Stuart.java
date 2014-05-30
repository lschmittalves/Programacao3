/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Slick.Framework.Components;

import Modelos.EnumDirecoes;
import Modelos.EnumEventos;
import Modelos.Rato;
import Modelos.RatoAcao;
import java.util.LinkedList;
import java.util.Queue;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author leonardo.alves
 */
public class Stuart {

    private static final float MARGEM_ERRO_PARAMAIS = 1.007f;
    private static final float MARGEM_ERRO_PARAMENOS = 0.993f;

    private float tamanhoSprite;
    private Rato rato;

    private Animation up;
    private Animation down;
    private Animation left;
    private Animation right;

    private float posAtualRatoX;
    private float posAtualRatoY;

    private Queue<RatoAcao> filaDeAcoe;

    public Stuart(Rato rato, float tamanhoSprite) throws SlickException {

        this.rato = rato;

        int[] duration = new int[]{100, 100, 100, 100, 100, 100, 100};

        up = new Animation(new Image[]{new Image("data/sprites/stuart_bk_1.png"), new Image("data/sprites/stuart_bk_2.png"), new Image("data/sprites/stuart_bk_3.png"), new Image("data/sprites/stuart_bk_4.png"), new Image("data/sprites/stuart_bk_5.png"), new Image("data/sprites/stuart_bk_6.png"), new Image("data/sprites/stuart_bk_7.png")}, duration, false);
        down = new Animation(new Image[]{new Image("data/sprites/stuart_fr_1.png"), new Image("data/sprites/stuart_fr_2.png"), new Image("data/sprites/stuart_fr_3.png"), new Image("data/sprites/stuart_fr_4.png"), new Image("data/sprites/stuart_fr_5.png"), new Image("data/sprites/stuart_fr_6.png"), new Image("data/sprites/stuart_fr_7.png")}, duration, false);
        left = new Animation(new Image[]{new Image("data/sprites/stuart_lft_1.png"), new Image("data/sprites/stuart_lft_2.png"), new Image("data/sprites/stuart_lft_3.png"), new Image("data/sprites/stuart_lft_4.png"), new Image("data/sprites/stuart_lft_5.png"), new Image("data/sprites/stuart_lft_6.png"), new Image("data/sprites/stuart_lft_7.png")}, duration, false);
        right = new Animation(new Image[]{new Image("data/sprites/stuart_rgt_1.png"), new Image("data/sprites/stuart_rgt_2.png"), new Image("data/sprites/stuart_rgt_3.png"), new Image("data/sprites/stuart_rgt_4.png"), new Image("data/sprites/stuart_rgt_5.png"), new Image("data/sprites/stuart_rgt_6.png"), new Image("data/sprites/stuart_rgt_7.png")}, duration, false);

        this.tamanhoSprite = tamanhoSprite;

        this.posAtualRatoX = rato.getPosXInicial() * tamanhoSprite;
        this.posAtualRatoY = rato.getPosYInicial() * tamanhoSprite;

        this.filaDeAcoe = rato.getFilaDeAcoe();

    }

    public Animation getProxMovimento(int delta) throws InterruptedException {

        if (filaDeAcoe != null && filaDeAcoe.size() != 0) {

            Modelos.RatoAcao proxAcao = filaDeAcoe.peek();

            switch (proxAcao.getEvento()) {
                case MOVE:
                    return andar(proxAcao, delta);
                case CHANGECOLOR:
                    break;
                case DEAD:
                    break;
                case EAT:
                    break;
                case FINISH:
                    break;

            }
        }
        return null;

    }

    private Animation andar(RatoAcao proxAcao, int delta) {
        switch (proxAcao.getDirecoes()) {
            case FRENTE:
                andarFrente(delta);
                return up;
            case ATRAS:
                andarTraz(delta);
                return down;
            case ESQUERDA:
                andarEsquerda(delta);
                return left;
            case DIREITA:
                andarDireita(delta);
                return right;
        }
        return null;
    }

    public EnumEventos checkAction() {

        if (filaDeAcoe != null && filaDeAcoe.size() != 0) {

            RatoAcao proxAcao = filaDeAcoe.peek();

            if (getPosXNaTelaByLabirinto() == proxAcao.getPosX() && getPosAtualRatoY() == proxAcao.getPosY()) {
                return proxAcao.getEvento();
            } else {
                return null;
            }
        } else {
            return null;
        }

    }

    private void andarDireita(int delta) {
        right.update(delta);
        posAtualRatoX += delta * 0.05f;
        updateProximoMovimentoX();
    }

    private void andarEsquerda(int delta) {
        left.update(delta);
        posAtualRatoX -= delta * 0.05f;
        updateProximoMovimentoX();
    }

    private void andarTraz(int delta) {
        down.update(delta);
        posAtualRatoY += delta * 0.05f;
        updateProximoMovimentoY();
    }

    private void andarFrente(int delta) {
        up.update(delta);
        posAtualRatoY -= delta * 0.05f;
        updateProximoMovimentoY();
    }

    private void updateProximoMovimentoX() {

        if ((getPosXNaTelaByLabirinto() * MARGEM_ERRO_PARAMENOS <= posAtualRatoX) && (getPosXNaTelaByLabirinto() * MARGEM_ERRO_PARAMAIS >= posAtualRatoX)) {
            //debug
            System.err.println("X | ERRO_MENOS: " + (getPosXNaTelaByLabirinto() * MARGEM_ERRO_PARAMENOS) + " | ERRO_MAIS: " + (getPosXNaTelaByLabirinto() * MARGEM_ERRO_PARAMAIS) + " | ATUAL: " + posAtualRatoX);
            System.err.println("X|" + filaDeAcoe.peek().getDirecoes() + " " + filaDeAcoe.peek().getPosX());

            posAtualRatoX = getPosXNaTelaByLabirinto();
            filaDeAcoe.remove();

        }

    }

    private void updateProximoMovimentoY() {

        if ((getPosYNaTelaByLabirinto() * MARGEM_ERRO_PARAMENOS <= posAtualRatoY) && (getPosYNaTelaByLabirinto() * MARGEM_ERRO_PARAMAIS >= posAtualRatoY)) {
            //debug
            System.err.println("Y | ERRO_MENOS: " + (getPosYNaTelaByLabirinto() * MARGEM_ERRO_PARAMENOS) + " | ERRO_MAIS: " + (getPosYNaTelaByLabirinto() * MARGEM_ERRO_PARAMAIS) + " | ATUAL: " + posAtualRatoY);
            System.err.println("Y |" + filaDeAcoe.peek().getDirecoes() + " " + filaDeAcoe.peek().getPosY());

            posAtualRatoY = getPosYNaTelaByLabirinto();
            filaDeAcoe.remove();

        }
    }

    private float getPosXNaTelaByLabirinto() {

        if (filaDeAcoe.peek() == null) {
            return posAtualRatoX;
        }

        return (filaDeAcoe.peek().getPosX() * tamanhoSprite);
    }

    private float getPosYNaTelaByLabirinto() {

        if (filaDeAcoe.peek() == null) {
            return posAtualRatoY;
        }

        return (filaDeAcoe.peek().getPosY() * tamanhoSprite);
    }

    private String getProxPos() {
        return "{" + this.filaDeAcoe.peek().getPosX() + "," + this.filaDeAcoe.peek().getPosY() + "| " + this.filaDeAcoe.peek().getDirecoes() + "} size: " + filaDeAcoe.size();
    }

    public Animation getAnimationUp() {

        return up;

    }

    public Animation getAnimationDown() {

        return down;

    }

    public Animation getAnimationLeft() {

        return left;

    }

    public Animation getAnimationRight() {

        return right;

    }

    public float getPosAtualRatoX() {
        return posAtualRatoX;
    }

    public float getPosAtualRatoY() {
        return posAtualRatoY;
    }

}
