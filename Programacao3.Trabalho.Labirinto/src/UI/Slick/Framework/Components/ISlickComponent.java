/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Slick.Framework.Components;

/**
 *
 * @author leonardo.alves
 */
public interface ISlickComponent {

    public void draw();
    public void updateMouseHover(int posX, int posY,boolean isPressed);

}
