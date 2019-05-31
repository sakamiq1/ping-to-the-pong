package pingpong;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Bong {
	
	public int x, y, width = 25, height = 25; 
	public int motionX, motionY;
	public Random random;
	private Pingtothepong pingtothepong;
	public int amountOfHits;
	public Bong(Pingtothepong pingtothepong) {
		this.random = new Random();
		this.pingtothepong = pingtothepong;
		
		spawn();
		
	}
	public void update(racket racket1, racket racket2) {
		
		int speed = 5;
		this.x += motionX * speed;
		this.y += motionY * speed;
	
		if(this.y + height - motionY > pingtothepong.height || this.y + motionY < 0) {
			if(this.motionY < 0) {
				
				this.y = 0;
				this.motionY = random.nextInt(4);
				
				if (motionY == 0) {
					motionY = 1;
				}
			}
			else {				
				this.motionY = -random.nextInt(4);
				this.y = pingtothepong.height - height;
				
				if (motionY == 0) {
					motionY = -1;
				}
			}
		
		}
		
		if (checkCollision(racket1) == 1) {
			this.motionX = 1 + (amountOfHits / 5);
			this.motionY = -2 + random.nextInt(4);
			
			if (motionY == 0) {
				motionY = 1;
			}
			amountOfHits ++;
		}
		
		else if (checkCollision(racket2) == 1) {
			
			this.motionX = -1 - (amountOfHits / 5);
			this.motionY = -2 + random.nextInt(4);
			
			if (motionY == 0) {
				motionY = 1;
			}
			amountOfHits ++;
		}
		
		if (checkCollision(racket1) == 2) {
			racket2.score++;
			spawn();
		}
		else if (checkCollision(racket2) == 2) {
			racket1.score++;
			spawn();
		}
	}
		
	public void spawn() {
		
		this.amountOfHits = 0;
		this.x = pingtothepong.width / 2 - this.width / 2;
		this.y = pingtothepong.height / 2 - this.height / 2;
		this.motionY = -2 + random.nextInt(4);
		if(motionY == 0) {
			motionY = 1;
		}
		if(random.nextBoolean()) {
			motionX = 1;
		}
		else {
			motionX = -1;
		}
		
	}
	public int checkCollision(racket racket) {
		if(this.x < racket.x + racket.width && this.x + width > racket.x && this.y < racket.y + racket.height && this.y + height > racket.y) {
				return 1;
			}
		else if ((racket.x > x && racket.racketNumber == 1) || (racket.x < x - width && racket.racketNumber == 2)){			
			return 2;
		}			
		return 0;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(x, y, width, height);
		
	}

}
