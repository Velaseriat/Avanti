package Avanti;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import Tower.Tower;


public class EnemyGunner extends Enemy {
	int radius = 120;
	private boolean isAttacking = false;

	public EnemyGunner(Point p, Board board) {
		super(p, board);
		
	}
	
	public EnemyGunner(Point p, Board board, int health){
		super(p, board, health);
	}
	
	public Tower attack(ArrayList<Tower> t){
		isAttacking = false;
		if (t.size() > 0)
			return t.get(new Random().nextInt(t.size()));
		else
			return null;
	}
	
	public void move(){
		super.move();
		if (new Random().nextInt(12) %10 == 0){
			isAttacking  = true;
		}
	}
	
	public boolean isAttacking(){
		return isAttacking;
	}
	
	public boolean isInRange(Tower t) { 
		if (getExactLocation().distance(new Point(t.getExactLocation().x, t.getExactLocation().y)) <= radius)
			return true;
		return false;
	}

}
