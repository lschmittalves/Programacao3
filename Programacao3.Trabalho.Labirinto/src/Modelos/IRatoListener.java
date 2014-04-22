package Modelos;

public interface IRatoListener {
    public void onMove(StringBuilder labirinto);
    public void onChangeColor(EnumCores newColor);
    public void onDead();
    public void onEat();
    public void onFinish();
}
