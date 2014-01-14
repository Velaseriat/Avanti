import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;


public class TowerOptionsPanel extends JDialog {
	JButton confirmButton;
	
	public TowerOptionsPanel(){
		confirmButton = new JButton("Confrim");
		add(confirmButton);
		System.out.println("EHE");
		setSize(400, 200);
		setLayout(new GridLayout(4,2));

		setVisible(true);
	}
}
