import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JRadioButton;


public class TowerOptionsPanel extends JDialog {
	private JButton closeButton;
	private JRadioButton bFurthest, bStrongest;
	private Tower tower;
	private Board b;
	
	public TowerOptionsPanel(Tower tower, Board b){
		this.tower = tower;
		this.b = b;
		bFurthest = new JRadioButton("Furthest");
		bStrongest = new JRadioButton("Strongest");
		MakeChangesListener mcl = new MakeChangesListener();
		bFurthest.addActionListener(mcl);
		bStrongest.addActionListener(mcl);
		ButtonGroup bg = new ButtonGroup();
		bg.add(bFurthest);
		bg.add(bStrongest);
		Box box = new Box(BoxLayout.X_AXIS);
		box.add(bFurthest);
		box.add(bStrongest);
		add(box);
		closeButton = new JButton("Close");
		closeButton.addActionListener(new CloseWindowListener());
		add(closeButton, BorderLayout.SOUTH);
		
		if (!tower.getMode())
			bFurthest.setSelected(true);
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
	
	class CloseWindowListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			setVisible(false);
		}
	}
	class MakeChangesListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if (bFurthest.isSelected())
				tower.setMode(false);
			else
				tower.setMode(true);
			b.repaint();
			System.out.println(tower.getLocation() + " - " + tower.getMode());
			
		}
		
	}
}

