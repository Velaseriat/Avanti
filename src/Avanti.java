import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.Timer;


public class Avanti extends JFrame{
	Board b;
	ArrayList<Tower> towers;
	ArrayList<Enemy> enemies;

	private static Point startingPoint;
	private static int mode = 2; //2: if enemies arent dead, they come back around again with their current health
	public Avanti(){
		b = new Board("map.csv");
		add(b);
		add(new GameControl(this),BorderLayout.SOUTH);
		setVisible(true);
		setSize(300,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		b.addMouseListener(new boardClickListener());
		pack();
		//testing purposes
		enemies = new ArrayList<Enemy>();
		startingPoint = b.getStartingPoint();
		towers = new ArrayList<Tower>();
		//correct this later on, just testing things out
		towers.add(new Tower(0,0));
		towers.add(new Tower(4,4));
		b.getTowersFromGame(towers);
		b.repaint();
	}
	
	public static void main(String[] args){
		Avanti avt = new Avanti();
	}
	
	class boardClickListener implements MouseListener{//not being used yet. will be for placing towers
		public void mouseClicked(MouseEvent e) {
			boardClick(e.getX(), e.getY());
		}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	}
	
	private class MoveTimerListener implements ActionListener {//this all happens at each tick
		public void actionPerformed(ActionEvent event){
			ArrayList<Enemy> t = new ArrayList<Enemy>();
			for (Enemy e : enemies){ //move each enemy
				e.move();
			}
			//towers attacking
			if (towers.size() > 0)
			for (Tower tower : towers){//each tower scans around itself for enemies to attack, and attacks the one that progressed the most
				ArrayList<Enemy> listOfEnemies = new ArrayList<Enemy>();
				for (Enemy e : enemies){
					if (tower.isInRange(e)){
						listOfEnemies.add(e); //I'm sure the error is around here.
					}
				}
				tower.attack(listOfEnemies);
			}
			
			if (mode == 1){ //they disappear at 'E'
			while(!enemies.isEmpty()){
				Enemy e = enemies.remove(0);
				if (!e.atEndingPoint() && e.getHealth()>0)
					t.add(e);
			}
			enemies = t;
			}
			else if (mode == 2){//they respawn to the beginning at 'E' with their current health
				while(!enemies.isEmpty()){
					Enemy e = enemies.remove(0);
					if (e.getHealth()>0)
					if (!e.atEndingPoint())
						t.add(e);
					//be able to obtain health from current enemy
					else
						t.add(new Enemy(startingPoint, b, e.getHealth()));
				}
				enemies = t;
			}
			
			b.getEnemiesFromGame(enemies); //for painting stuff
			b.getTowersFromGame(towers);
			b.repaint();
		}
	}
	
	private class SpawnTimerListener implements ActionListener {//will be used later
		public void actionPerformed(ActionEvent event){
			enemies.add(new Enemy(startingPoint, b)); 
			System.out.println("Starting Point:" + startingPoint);
		}
	}
		
	public void startEnemies() {//should really be named "ticker" but this gives the computer 50 ms to do everything
		Timer moveTimer = new Timer(50, new MoveTimerListener());
		moveTimer.start();
		//Timer spawnTimer = new Timer(500, new SpawnTimerListener());
		//spawnTimer.start();
	}

	public void boardClick(int x, int y) { // for placing towers later on
		// TODO Auto-generated method stub
		
	}

	public void addEnemy() {
		enemies.add(new Enemy(startingPoint, b));
		b.getEnemiesFromGame(enemies);
		b.repaint();
	}

	public void moveEnemies() {
		
		for (Enemy e : enemies){
			e.move();	
		}
		
		b.repaint();
	}
	
}
