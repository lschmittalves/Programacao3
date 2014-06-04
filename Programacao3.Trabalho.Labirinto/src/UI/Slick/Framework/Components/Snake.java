/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Slick.Framework.Components;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author leonardo.alves
 */
public class Snake implements ISlickComponent {

    private Animation snakeAttack;
    private float screenPosX;
    private float screenPosY;

    public Snake(float screenPosX, float screenPosY) throws SlickException {

        this.screenPosX = screenPosX;
        this.screenPosY = screenPosY;

        int[] duration = new int[]{120, 120, 120, 120, 120, 120, 120};

        snakeAttack = new Animation(new Image[]{new Image("data/sprites/snake_1.png"),
            new Image("data/sprites/snake_2.png"),
            new Image("data/sprites/snake_3.png"),
            new Image("data/sprites/snake_4.png"),
            new Image("data/sprites/snake_5.png"),
            new Image("data/sprites/snake_6.png"),
            new Image("data/sprites/snake_7.png")}, duration, true);

    }

    @Override
    public void draw() {
        snakeAttack.draw(screenPosX, screenPosY);
    }

    @Override
    public void updateMouseHover(int posX, int posY, boolean isPressed) {
        //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getPosX() {
        return (int) screenPosX;
    }

    @Override
    public int getPosY() {
        return (int) screenPosY;
    }

}
