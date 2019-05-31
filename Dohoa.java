package pingpong;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Dohoa extends JPanel {

	private static final long serialVersionUID = 1L;
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Pingtothepong.pingtothepong.render((Graphics2D) g);
	}
	

}
