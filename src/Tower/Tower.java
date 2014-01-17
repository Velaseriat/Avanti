package Tower;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import Avanti.Enemy;
import Avanti.HuntAndKillable;
import Enumeration.Mode;


public abstract class Tower implements HuntAndKillable {


	//Variables
	protected int radius;
	protected int health;
	protected int damage;
	protected int baseHealth;

	//Counting
	protected int numEnemiesInRange = 0;
	protected int coolDown;
	protected int towerCount = 0;
	protected int timeKeeper = 0;
	protected boolean drawShot = false;
	protected boolean counting = false;

	//Objects
	protected Point location;
	protected Mode towerMode = Mode.FARTHEST; //furthest is false
	protected boolean abilityIsOn = false;
	protected ArrayList<Enemy> enemies;
	protected int upgradeCost;
	protected int purchaseCost;
	private int level;

	public Tower(int x, int y){
		location = new Point(x, y);
		towerMode = Mode.FARTHEST;
		level = 1;
	}

	public abstract void attack(ArrayList<Enemy> enemies);
	public abstract void draw(Graphics g);
	public abstract boolean equals(Tower other);
	public abstract void useAbility();

	public boolean isInRange(Enemy e) { //this might not be measuring quite right
		if (e.getExactLocation().distance(new Point(location.x*50 + 25, location.y*50 + 25)) <= radius)
			return true;
		return false;
	}

	public Point getLocation(){
		return location;
	}

	public Mode getMode() {
		return towerMode;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int i){
		health = i;
	}

	public Point getExactLocation(){
		return new Point(50 * location.x + 25, 50 * location.y + 25);
	}

	public void setLocation(Point p) {
	}

	public void setMode(Mode towerMode) {
		this.towerMode = towerMode;
	}

	public boolean canUseAbility() {
		if (timeKeeper > coolDown && level == 10)
			return true;
		return false;
	}


	public void addTime(){
		timeKeeper+=1;
	}

	public boolean abilityIsOn() {
		return abilityIsOn;
	}

	public int getUpgradeCost() {
		return upgradeCost;
	}

	public int getLevel(){
		return level;
	}
	
	public void increaseLevel() {
		if (level < 10)
			level+=1;
		else
			System.out.println("Max level achieved!");
		health = health + baseHealth*level;
		damage = damage + 2*level;
	}


}
