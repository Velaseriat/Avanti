package Tower;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import Avanti.Enemy;
import Enumeration.Mode;


public class AttiaTower extends Tower{
	private Enemy enemy;
	private int abilityUseTime = 16;
	private int abilityCounter = 0;
	
	
	public AttiaTower(int x, int y) {
		super(x, y);
		coolDown = 100;
		upgradeCost = 50;
		purchaseCost = 250;
		baseHealth = 110;
		health = 1000;
		damage = 40;
		radius = 125;
	}

	public void attack(ArrayList<Enemy> targetList){
		this.enemies = targetList;
		if (!abilityIsOn){
			System.out.println("Time:" + timeKeeper + " Cooldown:" + coolDown);
			numEnemiesInRange = targetList.size();
			if (towerCount%2==0){//attack every other time
				if (targetList.size() > 0){
					counting = true;
					drawShot = true;
					enemy = targetList.get(0);
					for (int i = 0; i < targetList.size(); i++){
						if (towerMode == Mode.FARTHEST){
							if (enemy.getProgress() < targetList.get(i).getProgress())
								enemy = targetList.get(i);
						}
						else if (towerMode == Mode.STRONGEST){
							if (enemy.getHealth() < targetList.get(i).getHealth())
								enemy = targetList.get(i);
						}
						else{
							if (enemy.getHealth() > targetList.get(i).getHealth())
								enemy = targetList.get(i);
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
		else{
			System.out.println(abilityCounter);
			if (abilityUseTime > abilityCounter){
			for (Enemy e : targetList){
				e.setHealth(e.getHealth()-damage);
			}
			abilityCounter += 1;
			}
			else{
				abilityIsOn = false;
				abilityCounter = 0;
				timeKeeper = 0;
			}

		}
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
			if (!abilityIsOn){
				Point l = enemy.getExactLocation();
				g.drawLine(50 * location.x + 25, 50 * location.y + 25, l.x, l.y);
			}
			else{
				for (Enemy e : enemies){
					g.drawLine(50*location.x+25, 50*location.y+25, e.getExactLocation().x, e.getExactLocation().y);
				}
			}
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

	@Override
	public void useAbility() {
		if (canUseAbility())
			abilityIsOn = true;
	}
}
