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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Animation;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author leonardo.alves
 */
public class LabirintoSlick extends BasicGameState implements IButtonListener {

    private Animation sprite, up, down, left, right;
    private LinkedList<ISlickComponent> screenComponents;
    private float x = 34f, y = 34f;

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

        Image[] movementUp = {new Image("data/sprites/stuart_bk_1.png"), new Image("data/sprites/stuart_bk_2.png"), new Image("data/sprites/stuart_bk_3.png"), new Image("data/sprites/stuart_bk_4.png"), new Image("data/sprites/stuart_bk_5.png"), new Image("data/sprites/stuart_bk_6.png"), new Image("data/sprites/stuart_bk_7.png")};
        Image[] movementDown = {new Image("data/sprites/stuart_fr_1.png"), new Image("data/sprites/stuart_fr_2.png"), new Image("data/sprites/stuart_fr_3.png"), new Image("data/sprites/stuart_fr_4.png"), new Image("data/sprites/stuart_fr_5.png"), new Image("data/sprites/stuart_fr_6.png"), new Image("data/sprites/stuart_fr_7.png")};
        Image[] movementLeft = {new Image("data/sprites/stuart_lft_1.png"), new Image("data/sprites/stuart_lft_2.png"), new Image("data/sprites/stuart_lft_3.png"), new Image("data/sprites/stuart_lft_4.png"), new Image("data/sprites/stuart_lft_5.png"), new Image("data/sprites/stuart_lft_6.png"), new Image("data/sprites/stuart_lft_7.png")};
        Image[] movementRight = {new Image("data/sprites/stuart_rgt_1.png"), new Image("data/sprites/stuart_rgt_2.png"), new Image("data/sprites/stuart_rgt_3.png"), new Image("data/sprites/stuart_rgt_4.png"), new Image("data/sprites/stuart_rgt_5.png"), new Image("data/sprites/stuart_rgt_6.png"), new Image("data/sprites/stuart_rgt_7.png")};
        int[] duration = {100, 100, 100, 100, 100, 100, 100};

        up = new Animation(movementUp, duration, false);
        down = new Animation(movementDown, duration, false);
        left = new Animation(movementLeft, duration, false);
        right = new Animation(movementRight, duration, false);

        // Original orientation of the sprite. It will look right.
        sprite = right;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        setTamanhoTela();
        for (int i = 0; i < screenComponents.size(); i++) {
            screenComponents.get(i).draw();
        }
        sprite.draw((int) x, (int) y);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();
        if (input.isKeyDown(Input.KEY_UP)) {
            sprite = up;

            sprite.update(delta);
            // The lower the delta the slowest the sprite will animate.
            y -= delta * 0.1f;

        } else if (input.isKeyDown(Input.KEY_DOWN)) {
            sprite = down;

            sprite.update(delta);
            y += delta * 0.1f;

        } else if (input.isKeyDown(Input.KEY_LEFT)) {
            sprite = left;

            sprite.update(delta);
            x -= delta * 0.1f;

        } else if (input.isKeyDown(Input.KEY_RIGHT)) {
            sprite = right;

            sprite.update(delta);
            x += delta * 0.1f;

        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(LabirintoSlick.class.getName()).log(Level.SEVERE, null, ex);
        }
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
