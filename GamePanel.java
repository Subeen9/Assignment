import java.awt.*;

import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7997686283139622364L;
	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int Unit_Size = 25;
	static final int GAME_UNIT =(SCREEN_WIDTH * SCREEN_HEIGHT)/Unit_Size;
	static final int Delay = 75;
	final int x[]= new int[GAME_UNIT];
	final int y[]= new int[GAME_UNIT];
	int bodyParts =6;
	int fruitEaten;
	int fruitX;
	int fruitY;
	char direction = 'L';
	boolean run = false;
	Timer time;
	Random random;
	GamePanel(){
		random = new Random();
		 this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		 this.setBackground(Color.black);
		 this.setFocusable(true);
		 this.addKeyListener(new MyAdapter());
		 start();
		
	}
	public void start() { 
		newApple();
		run = true;
		time = new Timer(Delay, this);
		time.start();
		
		
	};
	
	public void paint(Graphics g) {
		super.paint(g);
		draw(g);
		
	}
	public void draw(Graphics g) {
		if(run) {
		for(int i=0;i<SCREEN_HEIGHT/Unit_Size; i++) {
			g.drawLine(i*Unit_Size, 0, i*Unit_Size, SCREEN_HEIGHT);
			g.drawLine(0, i*Unit_Size, SCREEN_WIDTH, i*Unit_Size);	
			for(int k=0; k<bodyParts; k++) {
				if(k==0) {
					g.setColor(Color.white);
					g.fillRect(x[k], x[k], Unit_Size, Unit_Size);
				}
				else {
					g.setColor(Color.green);
					g.fillRect(x[k], x[k], Unit_Size, Unit_Size);
				}
			}
		}
		g.setColor(Color.red);	
		g.fillOval(fruitX, fruitY, Unit_Size, Unit_Size);
	}
			
	public void newApple() {
		fruitX = random.nextInt((int)(SCREEN_WIDTH/Unit_Size))/Unit_Size;
		fruitY = random.nextInt((int)(SCREEN_HEIGHT/Unit_Size))/Unit_Size;
		
	};
	public void move() {
		for(int j=bodyParts; j>0; j--) {
			x[j]= x[j-1];
			y[j]= y[j-1];
		}
		switch(direction) {
		case 'L':
			x[0]=x[0]-Unit_Size;
			break;
		case 'R':
			x[0]=x[0]+Unit_Size;
			break;
		case 'U':
			y[0]= y[0]- Unit_Size;
			break;
		case 'D':
			y[0]= y[0]+Unit_Size;
			break;
		}
		
		
	};
	
	public void fruits() {
		if(x[0]==fruitX && y[0]== fruitY) {
			bodyParts++;
			fruitEaten++;
			newApple();
			
		}
		
	};
	public void collision() {
		for(int i=bodyParts; i>0; i--) {//to check head collides with bodyparts 
			if((x[0]==x[i] && y[0]==y[i])) {
				run = false;
			}
		}
		if(x[0]<0) {// check left border touch by snake
			run = false;
		}
		if(x[0]>SCREEN_WIDTH) {//check right border touch by snake
			run = false;
		}
		if(y[0]<0) {//check top border touch by snake
			run =false;
		}
		if(y[0]>SCREEN_HEIGHT) {//check down border touch by snake
			run = false;
		}
		if(!run) {
			time.stop();
		}
		
	};
	public void gameOver(Graphics g) {
		g.setColor(Color.red);
		
	};
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		repaint();
		
		
	}
	public class MyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction!= 'R') {
					direction ='L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction!= 'L') {
					direction ='R';
				}
				break;
			case KeyEvent.VK_UP:
				if(direction!= 'D') {
					direction ='U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direction!= 'U') {
					direction ='D';
				}
				break;
			}
			
		};
		
	}

}
