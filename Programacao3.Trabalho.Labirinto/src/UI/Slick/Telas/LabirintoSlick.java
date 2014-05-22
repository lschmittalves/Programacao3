/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Slick.Telas;

import Modelos.EnumTipoObstaculo;
import Modelos.Labirinto;
import UI.Slick.Framework.Components.BackGround;
import UI.Slick.Framework.Components.Button;
import UI.Slick.Framework.Components.IButtonListener;
import UI.Slick.Framework.Components.ISlickComponent;
import UI.Slick.LabirintoSlickMain;
import java.util.LinkedList;
import java.util.TreeMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author leonardo.alves
 */
public class LabirintoSlick extends BasicGameState implements IButtonListener {

    private LinkedList<ISlickComponent> screenComponents;

    public LabirintoSlick() {
        screenComponents = new LinkedList<>();
    }

    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return 1;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        gerarBackground();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        setTamanhoTela();
        for (int i = 0; i < screenComponents.size(); i++) {
            screenComponents.get(i).draw();
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {

    }

    @Override
    public void onClick(Button sender) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void gerarBackground() throws SlickException {

        Labirinto labirinto = LabirintoSlickMain.getInstance().getLabirinto();

        if (labirinto == null) {
            return;
        }

        for (int i = 0; i < labirinto.getMatrizObstaculos().size(); i++) {

            int y = i * 30;

            for (int j = 0; j < labirinto.getMatrizObstaculos().get(i).size(); j++) {

                int x = j * 30;
                switch (labirinto.getMatrizObstaculos().get(i).get(j)) {
                    case ARMADILHA:
                        screenComponents.add(new BackGround(x, y, "data/sprites/spriteArmadilha.png"));
                        break;
                    case ENTRADA:
                        screenComponents.add(new BackGround(x, y, "data/sprites/spriteEntrada.png"));
                        break;
                    case ESPACO_BRANCO:
                        screenComponents.add(new BackGround(x, y, "data/sprites/spriteFundo.png"));
                        break;
                    case PAREDE:
                        screenComponents.add(new BackGround(x, y, "data/sprites/spriteParede.png"));
                        break;
                    case QUEIJO:
                        screenComponents.add(new BackGround(x, y, "data/sprites/spriteQueijo.png"));
                        break;
                    case SAIDA:
                        screenComponents.add(new BackGround(x, y, "data/sprites/spriteSaida.png"));
                        break;

                }

            }
        }

    }

    private void setTamanhoTela() throws SlickException {

        int height = LabirintoSlickMain.getInstance().getLabirinto().getNoLinhas() * 30;
        int width = LabirintoSlickMain.getInstance().getLabirinto().getNoColunas() * 30;
        LabirintoSlickMain.getInstance().setTamanhoJanela(width, height);

    }

}
