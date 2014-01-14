import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.Box;


public class BoardCell{
	private int x;
	private int y;
	private int cellSize = 50;
	private boolean isPath;
	private String direction;
	private Color color;
	
	public BoardCell(int x, int y, boolean isPath, String direction) {
		this.x = x;
		this.y = y;
		this.isPath = isPath;
		this.direction = direction;
		if (!direction.contains("W")){
			color = Color.CYAN;
			color = color.darker().darker();
		}
		else
			color = Color.black;
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(cellSize * y,cellSize * x, cellSize, cellSize);
		g.setColor(Color.CYAN);
		g.drawRect(cellSize * y,cellSize * x, cellSize, cellSize);
		g.setColor(Color.white);
		g.drawString(""+direction, cellSize * y + 20, cellSize * x + 30);
	}

	public String getDirection() {
		return direction;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isPath() {
		return isPath;
	}

	public void setPath(boolean isPath) {
		this.isPath = isPath;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	
}
