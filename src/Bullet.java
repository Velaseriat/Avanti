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
		if (position.distance(target.getExactLocation())>=speed && !isAtTarget){
			int xx = target.getExactLocation().x - position.x;
			int yy = target.getExactLocation().y - position.y;
			double angle = Math.atan2(yy, xx);
			System.out.println(position.distance(target.getExactLocation()));
			position = new Point((int)(position.x + speed*Math.cos(angle)), (int)(position.y + speed*Math.sin(angle)));
		}
		else{
			isAtTarget = true;
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
