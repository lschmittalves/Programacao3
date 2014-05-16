package UI.Slick.Telas;

import UI.Slick.Framework.Components.Button;
import UI.Slick.Framework.Components.IButtonListener;
import UI.Slick.Framework.Components.ISlickComponent;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JFileChooser;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends BasicGameState implements IButtonListener {

    private static final boolean log = false;
    private Image background;
    private TreeMap<String, ISlickComponent> screenComponents;
    private ITelaListener iTelaListener;

    private Menu() {
    }

    public Menu(ITelaListener iTelaListener) {
        this.iTelaListener = iTelaListener;
    }

    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

        screenComponents = new TreeMap();

        background = new Image("data/backgrounds/MenuBackground.png");
        screenComponents.put("btnPlay", new Button("btnPlay", 630, 260, "data/botoes/btnIniciar.png", "data/botoes/btnIniciar_hover.png", this));
        screenComponents.put("btnImportar", new Button("btnImportar", 630, 325, "data/botoes/btnImportar.png", "data/botoes/btnImportar_hover.png", this));
        screenComponents.put("btnSair", new Button("btnSair", 630, 390, "data/botoes/btnSair.png", "data/botoes/btnSair_hover.png", this));

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException {
        background.draw(0, 0);
        //desenha os componentes
        for (Map.Entry<String, ISlickComponent> entry : screenComponents.entrySet()) {
            String key = entry.getKey();
            entry.getValue().draw();
        }

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int arg2)
            throws SlickException {

        Input input = gc.getInput();
        int x = input.getMouseX();
        int y = input.getMouseY();
        // atualiza todos os componentes      
        for (Map.Entry<String, ISlickComponent> entry : screenComponents.entrySet()) {
            String key = entry.getKey();
            entry.getValue().updateMouseHover(x, y, input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON));
        }
    }

    @Override
    public void onClick(Button sender) {
        switch (sender.getId()) {
            case "btnPlay":
                System.exit(0);
            case "btnImportar":
                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showOpenDialog() == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                }
            case "btnSair":
                System.exit(0);

        }
    }

}
