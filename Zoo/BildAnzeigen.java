package Zoo;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class BildAnzeigen {
	public BildAnzeigen(){
			frame = new JFrame("BildAnzeigen");
			label = new JLabel();
	}

	private static JFrame frame;
	private static JLabel label;
	public void anzeigenBild(String bildPfad,int breite, int höhe, boolean b) {
		SwingUtilities.invokeLater(()->{
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(breite, höhe);
		
		
		ImageIcon icon = new ImageIcon(bildPfad);
		label.setIcon(icon);
		frame.add(label);
		frame.setVisible(b);
		
	});
	}
}
