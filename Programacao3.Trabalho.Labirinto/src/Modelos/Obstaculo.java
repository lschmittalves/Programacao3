package Modelos;

public class Obstaculo {

    private int posicaoX;
    private int posicaoY;
    private EnumTipoObstaculo tipoObstaculo;

    public Obstaculo(int posicaoX, int posicaoY, EnumTipoObstaculo Obstaculo) {
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.tipoObstaculo = Obstaculo;
    }

    public int getPosicaoX() {
        return posicaoX;
    }

    public void setPosicaoX(int posicaoX) {
        this.posicaoX = posicaoX;
    }

    public int getPosicaoY() {
        return posicaoY;
    }

    public void setPosicaoY(int posicaoY) {
        this.posicaoY = posicaoY;
    }

    public EnumTipoObstaculo getTipoObstaculo() {
        return tipoObstaculo;
    }

    public void setTipoObstaculo(EnumTipoObstaculo Obstaculo) {
        this.tipoObstaculo = Obstaculo;
    }

    @Override
    public String toString() {

        switch (tipoObstaculo) {
            case ENTRADA:
                return "S";
            case ARMADILHA:
                return "T";
            case ESPACO_BRANCO:
                return ".";
            case PAREDE:
                return "#";
            case QUEIJO:
                return "C";
            case SAIDA:
                return "E";
        }

        return "";

    }

}
