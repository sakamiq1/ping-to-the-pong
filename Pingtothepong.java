package pingpong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Pingtothepong implements ActionListener, KeyListener {
	public static Pingtothepong pingtothepong;
	public int width = 700, height = 700;
	
	public Dohoa dohoa;
	
	public racket NguoiChoi1;
	public racket NguoiChoi2;
	
	public Bong bong;
	
	public boolean w, s, up, down;
	public int gameStatus = 0;
	public int playerWon;
		
	public Pingtothepong() {
		Timer timer = new Timer(20, this);
		JFrame jframe = new JFrame("Pingpong");
		
		dohoa = new Dohoa();
		
		jframe.setSize(width + 5, height + 25);
		jframe.setVisible(true);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.add(dohoa);
		jframe.addKeyListener(this);
		
		timer.start();
	}
	
	public void start() {
		gameStatus = 2;
		NguoiChoi1 = new racket(this, 1);
		NguoiChoi2 = new racket(this, 2);
		bong = new Bong(this); 
	}
	
	public void update() {
		
		
		
		if (w) {
			NguoiChoi1.move(true);
		}
		if (s) {
			NguoiChoi1.move(false);
		}
		if (up) {
			NguoiChoi2.move(true);
		}
		if (down) {
			NguoiChoi2.move(false);
		}
		
		bong.update(NguoiChoi1, NguoiChoi2);
	
	}
    public void actionPerformed(ActionEvent e) {
    	
    	if (gameStatus == 2) {
    		update();
    	}
    	
    	dohoa.repaint();
	
	}
	public static void main(String[] args) {
		pingtothepong = new Pingtothepong(); 
	}
	public void render(Graphics2D g) {
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		if(gameStatus == 0) {
			g.setColor(Color.WHITE);
			g.setFont(new Font ("Arial", 1, 50));
			g.drawString("Pingtothepong", width / 2 - 180, 50);
			
			g.setFont(new Font ("Arial", 1, 30));
			g.drawString("Press E to play", width / 2 - 100, height / 2);
		
		}
		
		
		if(gameStatus == 1) {
			g.setColor(Color.WHITE);
			g.setFont(new Font ("Arial", 1, 50));
			g.drawString("Pause", width / 2 - 80, height / 2 - 25);
		}
		if(gameStatus == 1 || gameStatus == 2) {
			g.setColor(Color.WHITE);
			g.setFont(new Font ("Arial", 1, 30));
			g.drawString(String.valueOf(NguoiChoi1.score), width / 2 - 40, 50);
			g.drawString(String.valueOf(NguoiChoi2.score), width / 2 + 40, 50);
			
			NguoiChoi1.render(g);
			NguoiChoi2.render(g);
			bong.render(g); 
		}
		if(gameStatus == 3) {
			g.setColor(Color.WHITE);
			g.setFont(new Font ("Arial", 1, 50));
			g.drawString("Pingtothepong", width / 2 - 180, 50);
			g.drawString("Nguoi Choi" + playerWon + "Thang", width / 2 - 75, 250);
			
			g.setFont(new Font ("Arial", 1, 30));
			g.drawString("Press E to play again", width / 2 - 100, height / 2);
			g.drawString("Press ESC back to menu", width / 2 - 100, height / 2);
		}
		
	}

	public void keyTyped(KeyEvent e) {
 		
	}

	public void keyPressed(KeyEvent e) {
		int id = e.getKeyCode();
		if (id == KeyEvent.VK_W) {
			w = true;
		}
		else if (id == KeyEvent.VK_S) {
			s = true;
		}
		else if (id == KeyEvent.VK_UP) {
			up = true;
		}
		else if (id == KeyEvent.VK_DOWN) {
			down = true;
		}
		else if (id == KeyEvent.VK_ESCAPE && gameStatus == 2) {
			gameStatus = 0;
		}
		else if (id == KeyEvent.VK_E) {
			if(gameStatus == 0) {
				start(); 
			}
			else if(gameStatus == 1) {
				gameStatus = 2;
			}
			else if(gameStatus == 2) {
				gameStatus = 1;
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		int id = e.getKeyCode();
		if (id == KeyEvent.VK_W) {
			w = false;
		}
		else if (id == KeyEvent.VK_S) {
			s = false;
		}
		else if (id == KeyEvent.VK_UP) {
			up = false;
		}
		else if (id == KeyEvent.VK_DOWN) {
			down = false;
		}
		
		
	}
	

}
