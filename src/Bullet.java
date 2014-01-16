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
		if (isAtTarget){
			target.setHealth(target.getHealth()-damage);
			if (position.distance(target.getLocation()) <= speed*2){
				isAtTarget = true;
			}
		}
		else{
			/*int xx = 0;
			int yy = 0;
			if (position.x < target.getExactLocation().x)
				xx = 1;
			if (position.x > target.getExactLocation().x)
				xx = -1;
			if (position.y < target.getExactLocation().y)
				yy = 1;
			if (position.y > target.getExactLocation().y)
				yy = -1;
			
			position = new Point(position.x + xx*speed, position.y + yy*speed);*/
			
			int xx = target.getExactLocation().x - position.x;
			int yy = target.getExactLocation().y - position.y;
			int radius = (int)Math.sqrt(xx^2 + yy^2);
			//position is now (0, 0) and coordinate (xx, yy) is away from origin of distance radius
			double angle = Math.atan2(xx, yy);
			System.out.println(Math.toDegrees(angle));
			position = new Point((int)(position.x + speed*Math.cos(Math.toDegrees(angle))), (int)(position.y + speed*Math.sin(180 + Math.toDegrees(angle))));
			
			
			
		}
	}
	
	public void draw(Graphics g){
		g.setColor(color);
		g.drawOval(position.x - caliber/2, position.y - caliber/2, caliber, caliber);
	}
	
	public int getSpeed(){
		return speed;
	}
	
	public boolean isAtTarget(){
		return isAtTarget;
	}

	public Point getExactLocation() {
		return new Point(50 * position.x + caliber/2 , 50 * position.y + caliber/2);
	}


}
