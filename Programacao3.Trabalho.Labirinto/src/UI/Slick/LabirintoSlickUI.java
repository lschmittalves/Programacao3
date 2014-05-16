/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Slick;


import UI.Slick.Telas.ITelaListener;
import UI.Slick.Telas.Menu;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author leonardo.alves
 */
public class LabirintoSlickUI extends StateBasedGame implements ITelaListener{


    public void run() throws SlickException{
            AppGameContainer app = new AppGameContainer(new LabirintoSlickUI());
            app.setDisplayMode(1024, 768, false);
            app.setShowFPS(false);
            
            app.setTargetFrameRate(65);
            app.start();
    
    }
    
    
    public LabirintoSlickUI() {
        super("Labirinto");
        // TODO Auto-generated constructor stub
    }

    @Override
    public void initStatesList(GameContainer arg0) throws SlickException {
        addState(new Menu(this));
   //     addState(new Play());

    }

    @Override
    public void onOpenScreen() {
   
    }

    @Override
    public void onTradeScreen() {
  
    }
}
