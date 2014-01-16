package Avanti;
import javax.swing.JDialog;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import Enumeration.TowerType;

public class TowerTypeDialog extends JDialog{

	private JButton addButton, cancelButton;
	private JRadioButton rAttia, rTanya, rIris, rXina, rKlair, rElni, rVivienne, rVelasariat;
	private ButtonGroup rowenKnights;
	private Avanti avt;
	
	public TowerTypeDialog(Avanti avt){
		this.avt = avt;
		setDefaultCloseOperation( DO_NOTHING_ON_CLOSE );
		
		rAttia = new JRadioButton("Attia");
		rTanya = new JRadioButton("Tanya");
		rIris = new JRadioButton("Iris");
		rXina = new JRadioButton("Xina");
		rKlair = new JRadioButton("Klair");
		rElni = new JRadioButton("Elni");
		rVivienne = new JRadioButton("Vivienne");
		rVelasariat = new JRadioButton("Velasariat");
		
		
		TowerTypeListener ttl = new TowerTypeListener();
		rAttia.addActionListener(ttl);
		rTanya.addActionListener(ttl);
		rIris.addActionListener(ttl);
		rXina.addActionListener(ttl);
		rKlair.addActionListener(ttl);
		rElni.addActionListener(ttl);
		rVivienne.addActionListener(ttl);
		rVelasariat.addActionListener(ttl);
		
		rowenKnights = new ButtonGroup();
		rowenKnights.add(rAttia);
		rowenKnights.add(rTanya);
		rowenKnights.add(rIris);
		rowenKnights.add(rXina);
		rowenKnights.add(rKlair);
		rowenKnights.add(rElni);
		rowenKnights.add(rVivienne);
		rowenKnights.add(rVelasariat);
		
		Box box = new Box(BoxLayout.Y_AXIS);
		box.add(rAttia);
		box.add(rTanya);
		box.add(rIris);
		box.add(rXina);
		box.add(rKlair);
		box.add(rElni);
		box.add(rVivienne);
		box.add(rVelasariat);
		add(box);
		
		Box box2 = new Box(BoxLayout.X_AXIS);
        
        
		cancelButton = new JButton("Close");
		cancelButton.addActionListener(new CloseWindowListener());
		addButton = new JButton("Add Tower");
		addButton.addActionListener(new AddTowerListener());
		box2.add(addButton);
        box2.add(cancelButton);
        Box box3 = new Box(BoxLayout.Y_AXIS);
        box3.add(new JLabel("After clicking 'Add Tower' click on the board to place it."));
        add(box2, BorderLayout.SOUTH);
        add(box3, BorderLayout.NORTH);
			
		
		Dimension sd = Toolkit.getDefaultToolkit().getScreenSize(); 
		Dimension fd = getSize(); 
		if (fd.height > sd.height) 
			fd.height = sd.height; 
		if (fd.width > sd.width) 
			fd.width = sd.width; 
		setLocation((sd.width - fd.width) / 2, (sd.height - fd.height) / 2); 
		
		setSize(400, 400);
		setVisible(true);
		
	}
	
	class AddTowerListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			setVisible(false);
			avt.placingTowers = true;
			dispose();
		}
	}
	
	class CloseWindowListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			setVisible(false);
			dispose();
		}
	}
	class TowerTypeListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if (rAttia.isSelected())
				avt.setTowerType(TowerType.ATTIA);
			else if (rTanya.isSelected())
				avt.setTowerType(TowerType.TANYA);
			else if (rIris.isSelected())
				avt.setTowerType(TowerType.IRIS);
			else if (rXina.isSelected())
				avt.setTowerType(TowerType.XINA);
			else if (rKlair.isSelected())
				avt.setTowerType(TowerType.KLAIR);
			else if (rElni.isSelected())
				avt.setTowerType(TowerType.ELNI);
			else if (rVivienne.isSelected())
				avt.setTowerType(TowerType.VIVIENNE);
			else if (rVelasariat.isSelected())
				avt.setTowerType(TowerType.VELASARIAT);
		}
		
		
	}
}

