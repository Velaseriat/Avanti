package Avanti;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;



public class Enemy implements HuntAndKillable {
	char choice;
	private Point location;
	private int xMove = 0;
	private int yMove = 0;
	Board b;
	private boolean chosenDirection = false;
	private boolean isAtEnd = false;
	private int health;
	private int progress = 0;
	
	public Enemy(Point p, Board board){
		location = p;
		b = board;
		health = 300;
	}
	public Enemy(Point p, Board board,int health){
		location = p;
		b = board;
		this.health = health;
	}
	public void move(){
		progress += 1;
		if (chosenDirection  == false){
			choice = b.getCellDirection(location).toCharArray()[new Random().nextInt(b.getCellDirection(location).length())];
			chosenDirection = true;
		}
		if (choice == 'R')
			xMove+=1;
		else if (choice == 'L')
			xMove-=1;
		else if (choice == 'U')
			yMove-=1;
		else if (choice == 'D')
			yMove+=1;
		else if (choice == 'E'){
			isAtEnd = true;
		}
		if (xMove == 10){
			xMove = 0;
			location = new Point(location.x+1, location.y);
			chosenDirection = false;
		}
		else if (xMove == -10){
			xMove = 0;
			location = new Point(location.x-1, location.y);
			chosenDirection = false;
		}
		else if (yMove == -10){
			yMove = 0;
			location = new Point(location.x, location.y-1);
			chosenDirection = false;
		}
		else if (yMove == 10){
			yMove = 0;
			location = new Point(location.x, location.y+1);
			chosenDirection = false;
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.RED.brighter());
		g.fillOval(50 * location.x + xMove*5+25/2, 50 * location.y + yMove*5 + 25/2, 25, 25);
		g.setColor(Color.GREEN);
		g.drawString(new Integer(health).toString(), 50 * location.x + xMove*5+25/2, 50 * location.y + yMove*5 + 25/2);
	}

	public Point getLocation() {
		return location;
	}
	public Point getExactLocation(){
		return new Point(50 * location.x + xMove*5 + 25, 50 * location.y + yMove*5 + 25);
	}
	public boolean atEndingPoint() {
		return isAtEnd;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int i) {
		health = i;
	}
	public int getProgress() {
		return progress;
	}
	public void setLocation(Point p) {
		location = p;
	}
}
