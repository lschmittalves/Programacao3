/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UI.Slick.Framework.Components;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author leonardo.alves
 */
public class BackGround implements ISlickComponent{


    private int x = 0;
    private int y = 0;
    private Image image;

    
     public BackGround(int posX, int posY, String imageNormal)
            throws SlickException {
        this.x = posX;
        this.y = posY;
        this.image = new Image(imageNormal);
    }
     
    @Override
    public void draw() {
        image.draw(x, y);
    }

    @Override
    public void updateMouseHover(int posX, int posY, boolean isPressed) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getPosX() {
       return  x;
    }

    @Override
    public int getPosY() {
         return  y;
    }

    
}
