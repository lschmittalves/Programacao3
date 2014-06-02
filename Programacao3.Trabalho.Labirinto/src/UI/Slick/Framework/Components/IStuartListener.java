/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Slick.Framework.Components;

import org.newdawn.slick.Animation;

/**
 *
 * @author leonardo.alves
 */
public interface IStuartListener {
    
    public void onMove(Animation animation);
    public void onDead();
    public void onEat();
    public void onChangeColor();
    public void onFinish();
}
