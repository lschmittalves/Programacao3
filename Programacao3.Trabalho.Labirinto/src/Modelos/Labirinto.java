package Modelos;

import java.util.TreeMap;

/** Modelo do labirinto. */
public class Labirinto {

	private Rato rato;

	/** Inst�ncia uma TreeMap que pega as direcoes percorridas pelo rato. */
	private TreeMap<Integer, TreeMap<Integer, Obstaculo>> obstaculos;

	public Labirinto(TreeMap<Integer, TreeMap<Integer, Obstaculo>> obstaculos,
			Rato rato) {
		this.obstaculos = obstaculos;
		this.rato = rato;
	}

	@SuppressWarnings("unused")
	private Labirinto() {

	}

	public Rato getRato() {
		return rato;
	}

	public TreeMap<Integer, TreeMap<Integer, Obstaculo>> getObstaculos() {
		return obstaculos;
	}

}
