package br.com.edvaldo.cm.visao;

import javax.swing.JFrame;

import br.com.edvaldo.cm.modelo.Tabuleiro;

@SuppressWarnings("serial")
public class TelaPrincipal extends JFrame {

	public TelaPrincipal() {
		Tabuleiro tabuleiro = new Tabuleiro(15, 35, 50);
		add(new PainelTabuleiro(tabuleiro));

		setTitle("Campo Minado");
		setSize(690, 438);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);

	}

	public static void main(String[] args) {
		new TelaPrincipal();
	}
}
