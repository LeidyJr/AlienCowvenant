package CodePlay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Instrucciones.java
 * Universidad Del Valle
 * ASIGNATURA: Programacion Interactiva
 * DOCENTE: Ivan Cabezas
 *
 * @author Juan David Tello - Sergio Ballen - Leidy Rivera - Kevin Franco
 * @version 1.0
 * @since 2017-05-25
 */

/**
 * Esta clase se encarga de la ventana de instrucciones del juego.
 */
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
