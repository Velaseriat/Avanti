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
	private boolean counting = false;
	
	public Tower(int x, int y){
		location = new Point(x, y);
	}
	
	public void attack(ArrayList<Enemy> enemies){
		numEnemiesInRange = enemies.size();
		if (towerCount%2==0){//attack every other time
			if (enemies.size() > 0){
				counting = true;
				drawShot = true;
				e = enemies.get(0);
				for (int i = 0; i < enemies.size(); i++){
					if (e.getProgress() < enemies.get(i).getProgress())
						e = enemies.get(i);
				}
				e.setHealth(e.getHealth()-80);
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
		if (drawShot && e != null && numEnemiesInRange > 0){
			g.setColor(Color.CYAN.brighter());
			Point l = e.getExactLocation();
			g.drawLine(50 * location.x + 25, 50 * location.y + 25, l.x, l.y);
		}
		drawShot = !drawShot;
	}
	
	public Point getLocation(){
		return location;
	}
	
	

}
