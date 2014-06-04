package UI.Slick.Telas;

import Modelos.Labirinto;
import RegraNegocio.Fachada;
import RegraNegocio.IFachada;
import UI.Slick.Framework.Components.BackGround;
import UI.Slick.Framework.Components.Button;
import UI.Slick.Framework.Components.IButtonListener;
import UI.Slick.Framework.Components.ISlickComponent;
import UI.Slick.Framework.Components.IStuartListener;
import UI.Slick.Framework.Components.Snake;
import UI.Slick.Framework.Components.Stuart;
import UI.Slick.LabirintoSlickMain;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author leonardo.alves
 */
public class LabirintoSlick extends BasicGameState implements IButtonListener, IStuartListener {

    private static final int TAMANHO_SPRITE = 30;
    private Animation sprite;
    private Stuart stuart;

    private int height;
    private int width;
    private int queijosCount;
    private int queijosLastCount;
    private int queijosX, queijosY;

    private LinkedList<ISlickComponent> screenComponents;

    private Labirinto labirinto;

    public LabirintoSlick() {
        screenComponents = new LinkedList<>();
        queijosCount = 0;
        queijosLastCount = 0;
    }

    private void gerarBackground() throws SlickException {

        if (labirinto == null) {
            return;
        }

        for (int i = 0; i < labirinto.getMatrizObstaculos().size() + 2; i++) {

            int y = i * TAMANHO_SPRITE;

            if (i >= labirinto.getMatrizObstaculos().size()) {
                for (int j = 0; j < labirinto.getMatrizObstaculos().get(labirinto.getMatrizObstaculos().size() - 1).size(); j++) {
                    int x = j * TAMANHO_SPRITE;
                    screenComponents.add(new BackGround(x, y, "data/sprites/spriteParede.png"));
                }
            } else {
                for (int j = 0; j < labirinto.getMatrizObstaculos().get(i).size(); j++) {

                    int x = j * TAMANHO_SPRITE;
                    switch (labirinto.getMatrizObstaculos().get(i).get(j)) {
                        case ARMADILHA:
                            screenComponents.add(new Snake(x, y));
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

    }

    private void setTamanhoTela() throws SlickException {

        height = (LabirintoSlickMain.getInstance().getLabirinto().getNoLinhas() + 2) * TAMANHO_SPRITE;
        width = LabirintoSlickMain.getInstance().getLabirinto().getNoColunas() * TAMANHO_SPRITE;
        LabirintoSlickMain.getInstance().setTamanhoJanela(width, height);

    }

    private void atualizaQueijos(Graphics grac) {

        grac.drawString("Queijos Comidos: " + queijosCount, (int) (width * 0.05), height - 30);

    }

    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return 1;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) {
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        try {

            IFachada regraNegocio = new Fachada();

            labirinto = (Labirinto) LabirintoSlickMain.getInstance().getLabirinto().clone();
            gerarBackground();

            stuart = new Stuart(labirinto.getRato(), this, TAMANHO_SPRITE);

            // Original orientation of the sprite. It will look right.
            sprite = stuart.getAnimationRight();

            regraNegocio.procurarSaida(labirinto);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {

        try {
            labirinto = null;
            stuart = null;
            screenComponents = null;
              LabirintoSlickMain.getInstance().zerarLabirinto();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) {
        try {
            if (labirinto == null || stuart == null) {
                return;
            }

            setTamanhoTela();

            grphcs.setBackground(Color.white);

            for (int i = 0; i < screenComponents.size(); i++) {
                if (queijosCount != queijosLastCount && 
                    screenComponents.get(i).getPosX() == queijosX && 
                    screenComponents.get(i).getPosY() == queijosY) {
                    screenComponents.set(i,new BackGround(queijosX, queijosY, "data/sprites/spriteFundo.png"));
                }

                screenComponents.get(i).draw();

            }
            sprite.draw((int) stuart.getPosAtualRatoX(), (int) stuart.getPosAtualRatoY());
            atualizaQueijos(grphcs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        try {
            if (labirinto == null || stuart == null) {
                return;
            }

            stuart.verificarProximaAcao(delta);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    @Override
    public void onClick(Button sender) {
        try {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public void onDead() {
        JOptionPane.showMessageDialog(null, "Filho da Puta!!!! Você será redirecionado ao menu pricipal.");
        LabirintoSlickMain.getInstance().enterState(0);
    }

    @Override
    public void onEat(int x, int y) {
        queijosX = x;
        queijosY = y;
        queijosCount++;
    }

    @Override
    public void onChangeColor() {

    }

    @Override
    public void onFinish() {

        JOptionPane.showMessageDialog(null, "Encontrei a saida :3! Você será redirecionado ao menu pricipal.");
        LabirintoSlickMain.getInstance().enterState(0);

    }

    @Override
    public void onMove(Animation animation) {

        sprite = animation;

    }

}
