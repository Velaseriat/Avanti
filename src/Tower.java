import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;


public abstract class Tower implements HuntAndKillable {
	
	
	//Variables
	private int radius = 100;
	protected int health = 10000;
	protected int damage = 40;
	
	//Counting
	protected int numEnemiesInRange = 0;
	protected int towerCount = 0;
	protected boolean drawShot = false;
	protected boolean counting = false;
	
	//Objects
	protected Point location;
	protected Enemy enemy;
	protected Mode towerMode = Mode.FARTHEST; //furthest is false
	
	public Tower(int x, int y){
		location = new Point(x, y);
		towerMode = Mode.FARTHEST;
	}
	
	public abstract void attack(ArrayList<Enemy> enemies);
	public abstract void draw(Graphics g);
	public abstract boolean equals(Tower other);
	
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
	
	

}
