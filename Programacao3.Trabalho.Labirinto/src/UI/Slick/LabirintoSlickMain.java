/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Slick;


import Modelos.Labirinto;
import UI.Slick.Telas.LabirintoSlick;
import UI.Slick.Telas.MenuSlick;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author leonardo.alves
 */
public class LabirintoSlickMain extends StateBasedGame {

    private static LabirintoSlickMain labirintoSlickUI = null;
    private Labirinto labirinto = null;
    private AppGameContainer app ;
    
    public void run() throws SlickException{
            app = new AppGameContainer(labirintoSlickUI);
            app.setDisplayMode(1024, 768, false);
            app.setShowFPS(false);
            app.setTargetFrameRate(65);
            app.start();
           
           
    
    }
    
    public static LabirintoSlickMain getInstance() {
      if(labirintoSlickUI == null) {
         labirintoSlickUI = new LabirintoSlickMain();
      }
      return labirintoSlickUI;
   }
    
    private LabirintoSlickMain() {
        super("Labirinto");
        // TODO Auto-generated constructor stub
    }

    public boolean podeIniciar(){
        return labirinto != null;
    }
    
    
    @Override
    public void initStatesList(GameContainer arg0) throws SlickException {
            this.addState(new MenuSlick());
            this.addState(new LabirintoSlick());

    }
   
    public void salvarLabirinto(Labirinto labirinto) {
       this.labirinto = labirinto;
    }

    public void setTamanhoJanela(int width, int height) throws SlickException{
       app.setDisplayMode(width, height, false);
    }

    public Labirinto getLabirinto() {
        return labirinto;
    }
    
    public void zerarLabirinto(){
        labirinto =null;
    }
    
    
}
