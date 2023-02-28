package br.com.edvaldo.cm.visao;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import br.com.edvaldo.cm.modelo.Campo;
import br.com.edvaldo.cm.modelo.CampoEvento;
import br.com.edvaldo.cm.modelo.CampoObservador;

@SuppressWarnings("serial")
public class BotaoCampo extends JButton implements CampoObservador, MouseListener {

	private final Color BG_PADRAO = new Color(153,255,102);
	private final Color BG_MARCADO = new Color(8, 179, 247);
	private final Color BG_EXPLODIR = new Color(189, 66, 68);
	private final Color TEXTO_VERDE = new Color(0, 100, 0);
	private final Color Borda = new Color(222, 184, 135);

	private Campo campo;

	public BotaoCampo(Campo campo) {
		this.campo = campo;
		setBackground(BG_PADRAO);
		setOpaque(true);
		setBorder(BorderFactory.createEtchedBorder());

		addMouseListener(this);
		campo.registrarObservador(this);

	}

	@Override
	public void eventoOcorrreu(Campo c, CampoEvento evento) {

		switch (evento) {
		case ABRIR: {

			aplicarEstiloAbrir();
			break;
		}
		case MARCAR: {

			AplicarEstiloMarcar();
			break;
		}
		case EXPLODIR: {

			aplicarEstiloExplodir();
			break;
		}
		default:
			aplicarEstiloPadrao();
		}

		SwingUtilities.invokeLater(() -> {
			repaint();
			validate();

		});

	}

	private void aplicarEstiloPadrao() {
		setBackground(BG_EXPLODIR);
		setText("");

	}

	private void aplicarEstiloExplodir() {
		setBackground(BG_EXPLODIR);
		setForeground(Color.WHITE);
		setText("X");
	}

	private void AplicarEstiloMarcar() {
		setBackground(BG_MARCADO);
		setForeground(Color.BLACK);
		setText("M");
	}

	private void aplicarEstiloAbrir() {

		setBorder(BorderFactory.createLineBorder(Color.gray));
		if (campo.isMinado()) {
			setBackground(BG_EXPLODIR);
			return;
		}

		setBackground(Borda);

		switch (campo.minasNaVizinhaca()) {
		case 1: {
			setForeground(TEXTO_VERDE);
			break;

		}
		case 2: {
			setForeground(Color.BLUE);
			break;

		}
		case 3: {
			setForeground(Color.YELLOW);
			break;

		}
		case 4:
		case 5:
		case 6:
		case 7:
			setForeground(Color.RED);
			break;

		default:
			setForeground(Color.PINK);
			break;
		}
		String valor = !campo.vizinhancaSegura() ? campo.minasNaVizinhaca() + "" : "";
		setText(valor);
	}

	// Interface dos eventos do Mouse

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {

		if (e.getButton() == 1) {

			campo.abrir();
		} else {
			campo.alternarMarcacao();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
