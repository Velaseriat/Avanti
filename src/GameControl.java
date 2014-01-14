


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import javax.swing.JPanel;


public class GameControl extends JPanel{
	private Avanti avanti;
	private JButton moveEnemy, addEnemy;
	JPanel towerButton;
	boolean moveButtonClicked = false;

	public GameControl(Avanti avanti) {
		this.avanti = avanti;
		setLayout(new GridLayout(0,2));
		setPreferredSize(new Dimension(15, 30));
		moveEnemy = new JButton("Move Enemy");
		moveEnemy.addActionListener(new MoveEnemyListener());
		add(moveEnemy);
		addEnemy = new JButton("Add Enemy");
		addEnemy.addActionListener(new AddEnemyListener());
		add(addEnemy);
		
		

		    //Register a listener for the radio buttons.

	}


	class MoveEnemyListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if (!moveButtonClicked){
				moveButtonClicked = true;
				avanti.startEnemies();
			}
		}
	}
	
	class AddEnemyListener implements ActionListener{
		public void actionPerformed (ActionEvent event){
			avanti.addEnemy();
		}
	}
	

}

