import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;


public class Tower {
	int radius = 100;
	Point location;
	private int numEnemiesInRange = 0;
	Enemy e;
	boolean drawShot = false;
	private int towerCount = 0;
	
	public Tower(int x, int y){
		location = new Point(x, y);
	}
	
	public void attack(ArrayList<Enemy> enemies){
		numEnemiesInRange = enemies.size();
		if (towerCount%2==0){//attack every other time
			drawShot = true;
			if (enemies.size() > 0){
				e = enemies.get(0);
				for (int i = 0; i < enemies.size(); i++){
					if (e.getProgress() < enemies.get(i).getProgress())
						e = enemies.get(i);
				}
				e.setHealth(e.getHealth()-100);
			}
		}
		else{
			drawShot = false;
		}
		towerCount += 1;
	}

	public boolean isInRange(Enemy e) { //this might not be measuring quite right
		if (e.getExactLocation().distance(new Point(location.x*50 + 25, location.y*50 + 25)) <= radius)
			return true;
		return false;
	}
	
	public void draw(Graphics g){
		drawShot = !drawShot;
		g.setColor(Color.WHITE);
		g.fillRect(50 * location.x + 25/2, 50 * location.y + 25/2, 25, 25);
		if (drawShot && e != null && numEnemiesInRange > 0){
			g.setColor(Color.CYAN.brighter());
			Point l = e.getExactLocation();
			g.drawLine(50 * location.x + 25, 50 * location.y + 25, l.x + 25, l.y + 25);
		}
		
	}
	
	public Point getLocation(){
		return location;
	}
	
	

}
