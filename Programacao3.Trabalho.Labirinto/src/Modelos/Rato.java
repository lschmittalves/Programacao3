package Modelos;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeMap;

import org.joda.time.DateTime;
import java.util.Queue;

/**
 * Cont�m m�todos com as fun��es relativas ao rato.
 */
public class Rato {

    /**
     * Propriedade posicao da linha.
     */
    
    private int posXInicial;
    private int posYInicial;
    
    private boolean vivo;
    /**
     * Propriedade cor do rato, definidas no EnumCores.
     */
    private EnumCores cor;
    /**
     * Propriedade data/hora de inicio do labirinto.
     */
    private DateTime dataInicio;
    /**
     * Propriedade posicao da linha.
     */
    private int posicaoX;
    /**
     * Propriedade posicao da coluna.
     */
    private int posicaoY;
    /**
     * Propriedade de quantidade de queijos comidos pelo rato.
     */
    private int queijosComidos;
    /**
     * Propriedade que contem os eventos do rato.
     */
    private IRatoListener iRatoListener;
    /**
     * Instancia uma TreeMap que pega as direcoes percorridas pelo rato.
     */
    private TreeMap<Integer, TreeMap<Integer, EnumDirecoes>> direcoesPercorridas;
    /**
     * Propriedade que armazena as direcoes percorridas em uma LinkedList.
     */
    private LinkedList<EnumDirecoes> listDirecoesPercorridas;

    private Queue<int[]> filaDeMovimentos;

    public Rato(int posX, int posY) {
        this.posicaoX = posX;
        this.posicaoY = posY;
        this.direcoesPercorridas = new TreeMap<>();
        this.cor = EnumCores.BRANCO;
        this.dataInicio = DateTime.now();
        this.listDirecoesPercorridas = new LinkedList<>();
        this.filaDeMovimentos = new LinkedList();
        this.posXInicial=posX;
        this.posYInicial=posY;
    }

    @SuppressWarnings("unused")
    private Rato() {
    }

    public void addListener(IRatoListener iRatoListener) {
        this.iRatoListener = iRatoListener;
    }

    /**
     * Metodo da interface IRatoListener, que contem os eventos do rato.
     *
     * @return iRatoListener
     */
    public IRatoListener getRatoListener() {
        return this.iRatoListener;
    }

    /**
     * Caso o rato estiver vivo.
     *
     * @return vivo
     */
    public boolean getVivo() {
        return vivo;
    }

    /**
     * Configura a propriedade vivo.
     *
     * @param vivo
     */
    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    /**
     * Pega a cor atual.
     *
     * @return cor
     */
    public EnumCores getCor() {
        return cor;
    }

    /**
     * Configura a propriedade EnumCores.
     */
    public void setCor(EnumCores cor) {
        this.cor = cor;
    }

    /**
     * Pega a data/hora em que o rato iniciou o labirinto.
     *
     * @return dataInicio
     */
    public DateTime getDataInicio() {
        return dataInicio;
    }

    /**
     * Pega a posicao da linha.
     *
     * @return posicaoX
     */
    public int getPosicaoX() {
        return posicaoX;
    }

    /**
     * Configura a propriedade setPosicaoX.
     *
     * @param posicaoX linha labirinto
     *
     * @return posicaoX
     */
    public void setPosicaoX(int posicaoX) {
        this.posicaoX = posicaoX;
    }

    /**
     * Pega a posicao da coluna.
     *
     * @return posicaoY
     */
    public int getPosicaoY() {
        return posicaoY;
    }

    /**
     * Seta a posicao da coluna.
     *
     * @param posicaoY coluna labirinto
     */
    public void setPosicaoY(int posicaoY) {
        this.posicaoY = posicaoY;
    }

    /**
     * Pega a quantidade de queijos comidos pelo rato.
     *
     * @return queijosComidos
     */
    public int getQueijosComidos() {
        return queijosComidos;
    }

    /**
     * Incrementa a quantidade de queijos comidos.
     */
    public void incrQueijosComidos() {
        this.queijosComidos++;
    }

    /**
     * Metodo que instancia uma TreeMap que pega as direcoes percorridas pelo
     * rato.
     *
     * @param Integer
     *
     * @param TreeMap <Integer, EnumDirecoes>
     *
     * @return direcoesPercorridas
     */
    public TreeMap<Integer, TreeMap<Integer, EnumDirecoes>> getDirecoesPercorridas() {
        return this.direcoesPercorridas;
    }

    /**
     * Adiciona posicao percorrida na TreeMap.
     *
     * @param EnumDirecoes direcao
     *
     * @param posX linha labirinto
     *
     * @param posY coluna labirinto
     */
    public void addDirecaoPercorrida(EnumDirecoes direcao, int posX, int posY) {
        /**
         * Se as direcoes percorridas pelo rato contem posicao na coluna do
         * labirinto, adiciona posicao da coluna na TreeMap.
         */
        if (!this.direcoesPercorridas.containsKey(posY)) {
            this.direcoesPercorridas.put(posY,
                    new TreeMap<Integer, EnumDirecoes>());
        }
        /**
         * Pega a posicao coluna e guarda a posicao da linha em
         * direcoesPercorridas, juntamente com a direcao em que o rato andou.
         */
        this.direcoesPercorridas.get(posY).put(posX, direcao);

        /**
         * Adiciona a direcao em que o rato andou na lista de direcoes
         * percorridas.
         */
        this.listDirecoesPercorridas.add(direcao);

        int[] arrayPos = {posX, posY};
        this.filaDeMovimentos.add(arrayPos);
    }

    /**
     * Metodo verifica se a lista de direcoes percorridas pelo rato tem tamanho
     * igual a zero, se sim retorna nulo. Caso contrario, retorna a direcao
     * percorrida anteriormente.
     *
     */
    public EnumDirecoes getLastDirection() {

        if (listDirecoesPercorridas.size() == 0) {
            return null;
        }

        return listDirecoesPercorridas.getLast();
    }

    /**
     * Remove a ultima direcao percorrida pelo rato.
     */
    public void removerUltimaDirecao() {

        listDirecoesPercorridas.removeLast();
    }

    public void removerPrimeiroMovimento() {
        filaDeMovimentos.remove();
    }

    public int getProximaPosicaoX() {
        return this.filaDeMovimentos.peek()[0];

    }
    
    public int getProximaPosicaoY() {
        return this.filaDeMovimentos.peek()[1];

    }

    public EnumDirecoes getMovimentoByPosition(int posX, int posY) {

        if (this.direcoesPercorridas.containsKey(posY)) {
            if (this.direcoesPercorridas.get(posY).containsKey(posX)) {
                return this.direcoesPercorridas.get(posY).get(posX);
            }
        }
        return null;

    }

    /**
     * @return the posXInicial
     */
    public int getPosXInicial() {
        return posXInicial;
    }

    /**
     * @return the posYInicial
     */
    public int getPosYInicial() {
        return posYInicial;
    }
}
