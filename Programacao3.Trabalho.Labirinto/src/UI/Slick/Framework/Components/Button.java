package UI.Slick.Framework.Components;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Button implements ISlickComponent {

    private String id;
    private int x = 0;
    private int y = 0;
    private Image imageNormal;
    private Image imageHover;
    private Image image;
    private IButtonListener buttonListener;

    private Button() {

    }

    public Button(String id, int posX, int posY, String imageNormal, String imageHover, IButtonListener buttonListener)
            throws SlickException {
        this.x = posX;
        this.y = posY;
        this.imageNormal = new Image(imageNormal);
        this.imageHover = new Image(imageHover);
        this.buttonListener = buttonListener;
        this.id = id;

        this.image = this.imageNormal;
    }

    @Override
    public void draw() {
        image.draw(getX(), getY());
    }

    @Override
    public void updateMouseHover(int posX, int posY, boolean isPressed) {
        if (posX > getX()
                && posX < (getX() + image.getWidth())
                && posY > getY()
                && posY < (getY() + image.getHeight())) {

            this.image = this.imageHover;
            if (isPressed) {
                buttonListener.onClick(this);
            }

        } else {
            this.image = this.imageNormal;
        }
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

   

}
