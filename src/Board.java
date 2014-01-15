import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JPanel;


public class Board extends JPanel {
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	String filename = "";
	BoardCell[][] map;
	private static Point startingPoint, endingPoint;
	ArrayList<BoardCell> boardCells = new ArrayList<BoardCell>();
	private ArrayList<Tower> towers = new ArrayList<Tower>();;
	
	
	public Board(String fName){
		ArrayList<String[]> tempMap = new ArrayList<String[]>();
		try {
			Scanner k = new Scanner(new File(fName));
			String[] row;
			while (k.hasNextLine()){
				row = k.nextLine().split(",");
				tempMap.add(row);
			}
			if (tempMap.size() > 0 && tempMap.get(0).length > 0){
				map = new BoardCell[tempMap.size()][tempMap.get(0).length];
				for (int i = 0; i < tempMap.size(); i++){
					for (int j = 0; j < tempMap.get(0).length; j++){
						boolean tempIsRoom = true;
						if (!tempMap.get(i)[j].equals("W"))
							tempIsRoom = false;
						map[i][j] = new BoardCell(i, j, tempIsRoom, tempMap.get(i)[j]);
						if (map[i][j].getDirection().contains("B")){
							startingPoint = new Point(map[i][j].getY(), map[i][j].getX());
							map[i][j] = new BoardCell(i, j, tempIsRoom, tempMap.get(i)[j].substring(1));
						}
					}
				}
			}
			setPreferredSize(new Dimension(map[0].length*50 ,map.length*50));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	

	public void paintComponent(Graphics g) {
		g.setColor(Color.black);
		for(int i = 0; i < map.length; i++){
			for (int j = 0; j < map[0].length; j++){
				map[i][j].draw(g);
			}
		}
		for (Enemy e : enemies){
			e.draw(g);
		}
		for (Tower t : towers){
			t.draw(g);
		}
	}
	
	public void getEnemiesFromGame(ArrayList<Enemy> e){
		enemies = e;
	}
	
	public void getTowersFromGame(ArrayList<Tower> t){
		towers = t;
	}

	public Point getStartingPoint() {
		return startingPoint;
	}

	public String getCellDirection(Point location) {
		return map[location.y][location.x].getDirection();
	}
	
	
}
