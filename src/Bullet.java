import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;


public class Bullet {
	private int speed;
	private int damage;
	private int caliber;
	private HuntAndKillable target;
	private Point position;
	private Color color;
	private boolean isAtTarget = false;
	
	public Bullet(int speed, int damage, HuntAndKillable target, Point position, Color color, int caliber) {
		this.speed = speed;
		this.damage = damage;
		this.target = target;
		this.position = position;
		this.color = color;
		this.caliber = caliber;
	}
	
	public void move(){
		if (position.equals(target.getLocation())){
			target.setHealth(target.getHealth()-damage);
			if (position.distance(target.getLocation()) <= speed){
				isAtTarget = true;
			}
		}
		else{
			int xx = 0;
			int yy = 0;
			if (position.x < target.getExactLocation().x)
				xx = 1;
			else if (position.x > target.getExactLocation().x)
				xx = -1;
			else if (position.y < target.getExactLocation().y)
				yy = 1;
			else if (position.y < target.getExactLocation().y)
				yy = -1;
			
			position = new Point(position.x + xx*speed, position.y + yy*speed);
			
		}
	}
	
	public void draw(Graphics g){
		g.setColor(color);
		g.drawOval(position.x, position.y, caliber, caliber);
	}
	
	public int getSpeed(){
		return speed;
	}
	
	public boolean isAtTarget(){
		return isAtTarget;
	}


}
