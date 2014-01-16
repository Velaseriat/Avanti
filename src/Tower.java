import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;


public class Tower implements HuntAndKillable {
	public enum Mode {WEAKEST, FARTHEST, STRONGEST};
	
	//Variables
	private int radius = 100;
	private int health = 10000000;
	private int damage = 40;
	
	//Counting
	private int numEnemiesInRange = 0;
	private int towerCount = 0;
	private boolean drawShot = false;
	private boolean counting = false;
	
	//Objects
	private Point location;
	private Enemy e;
	private Mode towerMode = Mode.FARTHEST; //furthest is false
	
	public Tower(int x, int y){
		location = new Point(x, y);
		towerMode = Mode.FARTHEST;
		//false means strongest
		//true means furthest
	}
	
	public void attack(ArrayList<Enemy> enemies){
		numEnemiesInRange = enemies.size();
		if (towerCount%2==0){//attack every other time
			if (enemies.size() > 0){
				counting = true;
				drawShot = true;
				e = enemies.get(0);
				for (int i = 0; i < enemies.size(); i++){
					if (towerMode == Mode.FARTHEST){
						if (e.getProgress() < enemies.get(i).getProgress())
							e = enemies.get(i);
						}
					else if (towerMode == Mode.STRONGEST){
						if (e.getHealth() < enemies.get(i).getHealth())
							e = enemies.get(i);
					}
					else{
						if (e.getHealth() > enemies.get(i).getHealth())
							e = enemies.get(i);
					}
				}
				e.setHealth(e.getHealth()-damage);
			}
			else{
				towerCount = 0;
				counting = false;
			}
		}
		else{
			drawShot = false;
		}
		if (counting)
			towerCount += 1;
	}

	public void setMode(Mode mode){
		towerMode = mode;
	}
	public boolean isInRange(Enemy e) { //this might not be measuring quite right
		if (e.getExactLocation().distance(new Point(location.x*50 + 25, location.y*50 + 25)) <= radius)
			return true;
		return false;
	}
	
	public void draw(Graphics g){
		
		g.setColor(Color.BLACK);
		g.fillRect(50 * location.x, 50 * location.y, 50, 50);
		g.setColor(Color.CYAN.brighter());
		g.drawRect(50 * location.x, 50 * location.y, 50, 50);
		g.drawLine(50 * location.x, 50 * location.y+50, 50*location.x+50,50 * location.y);
		g.drawOval( 50 * location.x, 50 * location.y, 50,50);
		g.setColor(Color.GREEN);
		g.drawString(new Integer(health).toString(), 50 * location.x , 50 * location.y );
		if (drawShot && e != null && numEnemiesInRange > 0){
			g.setColor(Color.CYAN.brighter());
			Point l = e.getExactLocation();
			g.drawLine(50 * location.x + 25, 50 * location.y + 25, l.x, l.y);
		}
		if (towerMode == Mode.FARTHEST)
			g.setColor(Color.YELLOW);
		else if (towerMode == Mode.WEAKEST)
			g.setColor(Color.GREEN);
		else
			g.setColor(Color.RED);
		g.fillRect(50 * location.x + 1, 50 * location.y + 1, 7, 7);
		drawShot = !drawShot;
	}
	
	public Point getLocation(){
		return location;
	}
	
	public boolean equals(Tower other){
		if (location.equals(other.getLocation()))
			return true;
		return false;
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

	@Override
	public void setLocation(Point p) {
		// TODO Auto-generated method stub
		
	}
	
	

}
