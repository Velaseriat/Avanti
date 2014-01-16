package Avanti;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.Timer;

import Enumeration.TowerType;
import Tower.AttiaTower;
import Tower.Tower;


public class Avanti extends JFrame{
	//In-game objects
	private Board b;
	//Containers
	private ArrayList<Tower> towers;
	private ArrayList<Enemy> enemies;
	private ArrayList<Bullet> bullets;
	
	//Variables
	private int enemiesSpawned = 0;
	private static Point startingPoint;
	private static int mode = 2; //2: if enemies arent dead, they come back around again with their current health
	public boolean placingTowers = false;
	public boolean towerOptionPanelOpen = false;
	public boolean madeTowerSelection = false;
	private int enemyStartingHealth = 300;
	private TowerType tt = TowerType.ATTIA;
	
	//Timers
	private Timer spawnTimer;
	private Timer waitTimer;
	
	

	
	
	
	public Avanti(){
		b = new Board("map.csv");
		add(b);
		add(new GameControl(this),BorderLayout.SOUTH);
		
		b.addMouseListener(new BoardClickListener());
		
		//testing purposes
		enemies = new ArrayList<Enemy>();
		startingPoint = b.getStartingPoint();
		towers = new ArrayList<Tower>();
		bullets = new ArrayList<Bullet>();
		//correct this later on, just testing things out
		b.getTowersFromGame(towers);
		b.getBulletsFromGame(bullets);
		b.repaint();
		setVisible(true);
		setSize(300,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}
	
	public static void main(String[] args){
		Avanti avt = new Avanti();
		Dimension sd = Toolkit.getDefaultToolkit().getScreenSize(); 
		Dimension fd = avt.getSize(); 
		if (fd.height > sd.height) 
			fd.height = sd.height; 
		if (fd.width > sd.width) 
			fd.width = sd.width; 
		avt.setLocation((sd.width - fd.width) / 2, (sd.height - fd.height) / 2); 
	}


	class BoardClickListener implements MouseListener{//not being used yet. will be for placing towers
		public void mouseClicked(MouseEvent e) {
			boardClick(e.getX(), e.getY());
		}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	}
	
	public void boardClick(int x, int y) { // for placing towers later on
		Point p = new Point(x/50, y/50);
		if (placingTowers && madeTowerSelection){
			boolean canPlaceTower = true;
			if (b.getCellDirection(p).equals("W")){
				for (Tower t : towers){
					if (t.getLocation().equals(p)){
						canPlaceTower = false;
					}
				}
				if (canPlaceTower){
					switch (tt){
					case ATTIA: {towers.add(new AttiaTower(p.x, p.y)); break;}
					case TANYA: //{towers.add(new TanyaTower(p.x, p.y)); break;}
					case IRIS: //{towers.add(new IrisTower(p.x, p.y)); break;}
					case XINA: //{towers.add(new XinaTower(p.x, p.y)); break;}
					case KLAIR: //{towers.add(new KlairTower(p.x, p.y)); break;}
					case ELNI: //{towers.add(new ElniTower(p.x, p.y)); break;}
					case VIVIENNE: //{towers.add(new VivienneTower(p.x, p.y)); break;}
					case VELASARIAT: //{towers.add(new VelasariatTower(p.x, p.y)); break;}
						default: //towers.add(new AttiaTower(p.x, p.y));
					}
					
					b.repaint();
				}
			}
			placingTowers = false;
		}
		else{
			for (Tower t : towers){
				if (t.getLocation().equals(p)){
					if (!towerOptionPanelOpen){
						towerOptionPanelOpen = true;
						new TowerOptionsPanel(t, this);
						System.out.println("Opened Tower Options Panel...");
					}
				}
			}
		}
	}

	private class MoveTimerListener implements ActionListener {//this all happens at each tick

		public void actionPerformed(ActionEvent event){
			ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
			ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
			ArrayList<Tower> towerList = new ArrayList<Tower>();
			
			for (Enemy e : enemies){ //move each enemy
				e.move();
			}
			for (Bullet b : bullets){
				b.move();
			}
			while(!bullets.isEmpty()){
				Bullet b = bullets.remove(0);
				if (!b.isAtTarget())
					bulletList.add(b);
			}
			bullets = bulletList;
			
			while(!towers.isEmpty()){
				Tower t = towers.remove(0);
				if (t.getHealth()>0){
					towerList.add(t);
				}
				else{
					System.out.println("Tower " + t.toString() + " has been destroyed!");
				}
			}
			towers = towerList;
			
			
			
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
			for (Enemy e : enemies){
				ArrayList<Tower> towerTargetList = new ArrayList<Tower>();
				if (e instanceof EnemyGunner){
					if (((EnemyGunner) e).isAttacking()){
						for (Tower t : towers){
							if(((EnemyGunner) e).isInRange(t))
								towerTargetList.add(t);
						}
						Tower target = ((EnemyGunner) e).attack(towerTargetList);
						if (target != null){
							Bullet bullet = new Bullet(13,10,target,e.getExactLocation(),Color.RED.darker(),10);
							bullets.add(bullet);
						}
					}
				}
				
			}
			
			if (mode == 1){ //they disappear at 'E'
			while(!enemies.isEmpty()){
				Enemy e = enemies.remove(0);
				if (!e.atEndingPoint() && e.getHealth()>0)
					enemyList.add(e);
			}
			enemies = enemyList;
			}
			else if (mode == 2){//they respawn to the beginning at 'E' with their current health
				while(!enemies.isEmpty()){
					Enemy e = enemies.remove(0);
					if (e.getHealth()>0)
					if (!e.atEndingPoint())
						enemyList.add(e);
					//be able to obtain health from current enemy
					else if (e instanceof EnemyGunner)
						enemyList.add(new EnemyGunner(startingPoint, b, e.getHealth()));
					else
						enemyList.add(new Enemy(startingPoint, b, e.getHealth()));
				}
				enemies = enemyList;
			}
			
			b.getEnemiesFromGame(enemies); //for painting stuff
			b.getTowersFromGame(towers);
			b.getBulletsFromGame(bullets);
			b.repaint();
		}
	}
	
	private class SpawnTimerListener implements ActionListener {//will be used later
		public void actionPerformed(ActionEvent event){
			//enemies.add(new Enemy(startingPoint, b, enemyStartingHealth)); 
			enemies.add(new EnemyGunner(startingPoint, b, enemyStartingHealth)); 
			enemiesSpawned+=1;
			if (enemiesSpawned  == 20){
				enemiesSpawned = 0;
				spawnTimer.stop();
				waitTimer = new Timer(5000, new WaitTimerListener());
				waitTimer.start();
			}
			
		}
	}
	
	private class WaitTimerListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
				waitTimer.stop();
				increaseEnemyHealth(50);
				spawnTimer.start();	
		}
	}
		
	public void startEnemies() {//should really be named "ticker" but this gives the computer 50 ms to do everything
		Timer moveTimer = new Timer(50, new MoveTimerListener()); // CHANGE SPEED HERE
		moveTimer.start();
		spawnTimer = new Timer(500, new SpawnTimerListener());
		spawnTimer.start();
		
	}

	public void increaseEnemyHealth(int i) {
		enemyStartingHealth +=i;
	}

	public void addEnemy() {
		//enemies.add(new Enemy(startingPoint, b));
		enemies.add(new EnemyGunner(startingPoint, b, enemyStartingHealth)); 
		b.getEnemiesFromGame(enemies);
		b.repaint();
	}

	public void moveEnemies() {
		
		for (Enemy e : enemies){
			e.move();	
		}
		b.repaint();
	}

	public void removeFromTowers(Tower tower) {
		//a bunch of tower recycling related code here so that the player can get some money back
		towers.remove(tower);
	}

	public Component getBoard() {
		return b;
	}

	public void setTowerType(TowerType tt) {
		this.tt = tt;
	}

	public TowerType getTowerType() {
		return tt;
	}
	
}
