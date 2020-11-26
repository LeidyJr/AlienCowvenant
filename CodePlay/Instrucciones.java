package CodePlay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Instrucciones extends JFrame{

	private JLabel Imagen;

	/** Constructor*/
	public void iniciarcomponent(){
		Imagen = new JLabel(new ImageIcon(this.getClass().getResource("/Imagenes/instrucciones.png")));
		Imagen.setBounds(0, 0, 1100, 600);
	}

	/** Carga del layout*/
	public Instrucciones(){
		iniciarcomponent();
		this.setTitle("INSTRUCCIONES");
		setLayout(null);
		add(Imagen);
		setBounds(150,50,1100,600);
		setResizable(false);
		setVisible(true);
	}

}
