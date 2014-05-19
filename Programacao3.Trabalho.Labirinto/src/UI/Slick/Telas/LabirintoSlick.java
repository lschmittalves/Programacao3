/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UI.Slick.Telas;

import UI.Slick.Framework.Components.Button;
import UI.Slick.Framework.Components.IButtonListener;
import UI.Slick.ICallBackToContext;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author leonardo.alves
 */
public class LabirintoSlick extends BasicGameState implements IButtonListener{

        private ICallBackToContext callBackToContext;

    private LabirintoSlick() {
    }

    public LabirintoSlick(ICallBackToContext callBackToContext) {
        this.callBackToContext = callBackToContext;
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
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
       
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
      
    }

    @Override
    public void onClick(Button sender) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
