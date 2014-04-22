package Modelos;

import java.util.LinkedList;
import java.util.TreeMap;

import org.joda.time.DateTime;

public class Rato {

	private boolean vivo;
	private EnumCores cor;
	private DateTime dataInicio;

	private int posicaoX;
	private int posicaoY;
	private int queijosComidos;
	private IRatoListener iRatoListener;
	private TreeMap<Integer, TreeMap<Integer, EnumDirecoes>> direcoesPercorridos;
	private LinkedList<EnumDirecoes> listDirecoesPercorridas;

	public Rato(int posX, int posY) {
		this.posicaoX = posX;
		this.posicaoY = posY;
		this.direcoesPercorridos = new TreeMap<>();
		this.cor = EnumCores.BRANCO;
		this.dataInicio = DateTime.now();
		this.listDirecoesPercorridas = new LinkedList<>();
	}

	@SuppressWarnings("unused")
	private Rato() {
	}

	public void addListener(IRatoListener iRatoListener) {
		this.iRatoListener = iRatoListener;
	}

	public IRatoListener getRatoListener() {
		return this.iRatoListener;
	}

	public boolean getVivo() {
		return vivo;
	}

	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}

	public EnumCores getCor() {
		return cor;
	}

	public void setCor(EnumCores cor) {
		this.cor = cor;
	}

	public DateTime getDataInicio() {
		return dataInicio;
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

	public int getQueijosComidos() {
		return queijosComidos;
	}

	public void incrQueijosComidos() {
		this.queijosComidos++;
	}

	public TreeMap<Integer, TreeMap<Integer, EnumDirecoes>> getDirecoesPercorridas() {
		return this.direcoesPercorridos;
	}

	public void addDirecaoPercorrida(EnumDirecoes direcao, int posX, int posY) {
		if (!this.direcoesPercorridos.containsKey(posY)) {
			this.direcoesPercorridos.put(posY,
					new TreeMap<Integer, EnumDirecoes>());
		}

		this.direcoesPercorridos.get(posY).put(posX, direcao);

		this.listDirecoesPercorridas.add(direcao);
	}

	public EnumDirecoes getLastDirection() {

		if(listDirecoesPercorridas.size() ==0)
			return null;
		
		return listDirecoesPercorridas.getLast();
	}

	public void removerUltimaDirecao() {

		listDirecoesPercorridas.removeLast();
	}

}
