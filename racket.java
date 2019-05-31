package pingpong;

import java.awt.Color;
import java.awt.Graphics;

public class racket {
	
	public int racketNumber;
	public int x, y, width = 50, height = 250;
	public int score;
	public racket(Pingtothepong pingtothepong, int racketNumber) {
		this.racketNumber = racketNumber;
		if (racketNumber == 1) {
			this.x = 0;
		}
		if (racketNumber == 2) {
			this.x = pingtothepong.width - width;
			this.y = pingtothepong.height / 2 - this.height / 2;
		}
	}
	public void render(Graphics g) {
		
		g.setColor(Color.RED);
		
		g.fillRect(x, y, width, height);
	}
		
	public void move(boolean up) {
		
		int speed = 25;
		if(up) {
			if(y - speed > 0) {
				y -= speed;
			}
			else {
				y = 0;
			}
		}
		else {
			if(y + height + speed < Pingtothepong.pingtothepong.height) {
				y += speed;
			}
			else {
				y = Pingtothepong.pingtothepong.height - height;
			}
		}
		
		
	}

}
