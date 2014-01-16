package Avanti;
import java.awt.Point;


public interface HuntAndKillable {
	
	public int getHealth();
	public void setHealth(int i);
	public void setLocation(Point p);
	public Point getLocation();
	public Point getExactLocation();
}
