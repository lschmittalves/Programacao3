package UI.Slick.Telas;

import RegraNegocio.Fachada;
import UI.Slick.ICallBackToContext;
import UI.Slick.Framework.Components.Button;
import UI.Slick.Framework.Components.IButtonListener;
import UI.Slick.Framework.Components.ISlickComponent;
import UI.Slick.LabirintoSlickMain;
import UI.Swing.Util.FrmImportacao;
import UI.Swing.Util.IFrmActionListener;
import java.io.File;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JOptionPane;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuSlick extends BasicGameState implements IButtonListener, IFrmActionListener {

    private static final boolean log = false;
    private Image background;
    private TreeMap<String, ISlickComponent> screenComponents;
    private ICallBackToContext callBackToContext;

    private MenuSlick() {
    }

    public MenuSlick(ICallBackToContext callBackToContext) {
        this.callBackToContext = callBackToContext;
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
        try {
            switch (sender.getId()) {
                case "btnPlay":
                    LabirintoSlickMain.getInstance().enterState(1);
                case "btnImportar":
                    new FrmImportacao(this).setVisible(true);
                    break;
                case "btnSair":
                    System.exit(0);
                    break;

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    @Override
    public void onSelectFile(File file) {
        try {
            callBackToContext.salvarLabirinto(new Fachada().criarLabirinto(file.getAbsolutePath()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

}
