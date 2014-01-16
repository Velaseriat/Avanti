import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;


public class AttiaTower extends Tower{
	
	public AttiaTower(int x, int y) {
		super(x, y);
	}

	public void attack(ArrayList<Enemy> enemies){
		numEnemiesInRange = enemies.size();
		if (towerCount%2==0){//attack every other time
			if (enemies.size() > 0){
				counting = true;
				drawShot = true;
				enemy = enemies.get(0);
				for (int i = 0; i < enemies.size(); i++){
					if (towerMode == Mode.FARTHEST){
						if (enemy.getProgress() < enemies.get(i).getProgress())
							enemy = enemies.get(i);
						}
					else if (towerMode == Mode.STRONGEST){
						if (enemy.getHealth() < enemies.get(i).getHealth())
							enemy = enemies.get(i);
					}
					else{
						if (enemy.getHealth() > enemies.get(i).getHealth())
							enemy = enemies.get(i);
					}
				}
				enemy.setHealth(enemy.getHealth()-damage);
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
	
public void draw(Graphics g){
		
		g.setColor(Color.BLACK);
		g.fillRect(50 * location.x, 50 * location.y, 50, 50);
		g.setColor(Color.CYAN.brighter());
		g.drawRect(50 * location.x, 50 * location.y, 50, 50);
		g.drawLine(50 * location.x, 50 * location.y+50, 50*location.x+50,50 * location.y);
		g.drawOval( 50 * location.x, 50 * location.y, 50,50);
		g.setColor(Color.GREEN);
		g.drawString(new Integer(health).toString(), 50 * location.x , 50 * location.y );
		if (drawShot && enemy != null && numEnemiesInRange > 0){
			g.setColor(Color.CYAN.brighter());
			Point l = enemy.getExactLocation();
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

public  boolean equals(Tower other){
	if (location.equals(other.getLocation()) && other instanceof AttiaTower)
		return true;
	return false;
}

}
