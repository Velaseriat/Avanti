package Avanti;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JRadioButton;

import Enumeration.Mode;
import Tower.AttiaTower;
import Tower.ElniTower;
import Tower.IrisTower;
import Tower.KlairTower;
import Tower.TanyaTower;
import Tower.VelasariatTower;
import Tower.VivienneTower;
import Tower.XinaTower;
import Tower.Tower;


public class TowerOptionsPanel extends JDialog {
	private JButton closeButton, removeButton, abilityButton;
	private JRadioButton bFarthest, bStrongest, bWeakest;
	private Tower tower;
	private Avanti avt;
	
	public TowerOptionsPanel(Tower tower, Avanti avt){
		this.tower = tower;
		this.avt = avt;
		setDefaultCloseOperation( DO_NOTHING_ON_CLOSE );
		bFarthest = new JRadioButton("Furthest");
		bStrongest = new JRadioButton("Strongest");
		bWeakest = new JRadioButton("Weakest");
		MakeChangesListener mcl = new MakeChangesListener();
		bFarthest.addActionListener(mcl);
		bStrongest.addActionListener(mcl);
		bWeakest.addActionListener(mcl);
		ButtonGroup bg = new ButtonGroup();
		bg.add(bFarthest);
		bg.add(bWeakest);
		bg.add(bStrongest);
		Box box = new Box(BoxLayout.Y_AXIS);
		box.add(bFarthest);
		box.add(bWeakest);
		box.add(bStrongest);
		add(box);
		Box box2 = new Box(BoxLayout.X_AXIS);
        
        
		closeButton = new JButton("Close");
		closeButton.addActionListener(new CloseWindowListener());
		removeButton = new JButton("Remove");
		removeButton.addActionListener(new RemoveTowerListener());
		abilityButton = new JButton("Ability");
		abilityButton.addActionListener(new AbilityListener());
		box2.add(removeButton);
        box2.add(closeButton);
        box2.add(abilityButton);
        add(box2, BorderLayout.SOUTH);
		if (tower.getMode() == Mode.FARTHEST)
			bFarthest.setSelected(true);
		else if (tower.getMode() ==  Mode.WEAKEST)
			bWeakest.setSelected(true);
		else
			bStrongest.setSelected(true);
			
		
		Dimension sd = Toolkit.getDefaultToolkit().getScreenSize(); 
		Dimension fd = getSize(); 
		if (fd.height > sd.height) 
			fd.height = sd.height; 
		if (fd.width > sd.width) 
			fd.width = sd.width; 
		setLocation((sd.width - fd.width) / 2, (sd.height - fd.height) / 2); 
		
		setSize(400, 200);
		setVisible(true);
		
	}

	class RemoveTowerListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			avt.removeFromTowers(tower);
			avt.getBoard().repaint();
			setVisible(false);
			avt.towerOptionPanelOpen = false;
			dispose();
		}
	}
	class CloseWindowListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			setVisible(false);
			avt.towerOptionPanelOpen = false;
			dispose();
		}
	}
	
	class MakeChangesListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (bFarthest.isSelected())
				tower.setMode(Mode.FARTHEST);
			else if (bWeakest.isSelected())
				tower.setMode(Mode.WEAKEST);
			else
				tower.setMode(Mode.STRONGEST);
			avt.getBoard().repaint();
			System.out.println("Did I repaint?");
		}
		
	}
	class AbilityListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println(tower.canUseAbility());
			if (tower.canUseAbility()){
				tower.useAbility();
			}
		}
	}
}

