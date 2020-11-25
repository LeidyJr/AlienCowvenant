package CodePlay;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

/**
 * Operaciones.java
 * Universidad Del Valle
 * ASIGNATURA: Progrmacion Interactiva
 * DOCENTE: Ivan Cabezas
 *
 * @author Juan David Tello - Sergio Ballen - Leidy Rivera - Kevin Franco
 * @version 1.0
 * @since 2017-05-25
 */
/**
 * Esta clase se encarga de graficar el mundo y los comandos del juego
 * 
 */
public class Interfaz extends javax.swing.JFrame implements ActionListener {
	private Operaciones objOp = new Operaciones();
	private static EstructuraDatos Datos = new EstructuraDatos();
	
	private static JPanel panel= new JPanel();
	private static JPanel panel2 = new JPanel();
	public static JPanel panelprincipal= new JPanel();
	private static JLabel[][] Grafico;
	private int TamanoColumna = 10;
	private int TamanoFila = 10;
	private int TamanoCuadrado= 60;
	public static  int[][] MatrizLogica;
	
	private JMenuBar barra = new JMenuBar();
	private JMenu programa = new JMenu("Archivo");
	private JMenu Nivel;
	private JMenuItem Guia;
	private JMenuItem Menu_Principal;
	private JMenuItem Salir ;

	private JButton boton1;
	private JButton boton2;
	private JButton boton3;
	private JButton boton4;
	private JButton boton5;
	private JButton boton6;
	static JTextArea Entrada;//antes JTextField
	JScrollPane scroll;
	AudioClip clip = Applet.newAudioClip(getClass().getResource("/sonidos/misterio.wav"));
	/**
	 * Este metodo se encarga  de la visualizacion del nivel
	 */
	public void Pintar(){
		clip.loop();
		String mensaje = "Nivel "+ Datos.getMundoActivo();
		Nivel = new JMenu(mensaje);
		this.setJMenuBar(barra);
	    barra.add(programa);
	    barra.add(Nivel);
		Guia = new JMenuItem("Instrucciones");
		programa.add(Guia);
		
		
		this.Guia.addActionListener(this);
		Menu_Principal = new JMenuItem("Volver a Menu");
		programa.add(Menu_Principal);
		this.Menu_Principal.addActionListener(this);
	    Salir = new JMenuItem("Guardar y Salir");
	    programa.add(Salir);
	    this.Salir.addActionListener(this);
		
		
		setTitle("Alien CowVenant");
		setResizable(false);
		setBounds(150,50,820,650);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panelprincipal.setLayout(new BorderLayout(0,0));
		setContentPane(panelprincipal);
		panelprincipal.add(Comandos(), BorderLayout.EAST);
		panelprincipal.add(Escenario(), BorderLayout.WEST);
		cerrar();
		
	}
	/**
	 * Este metodo visualiza el escenario del nivel
	 * @return panel
	 */

	protected  JComponent Escenario(){
	
	MatrizLogica = Datos.getmatriz();
	Grafico = new JLabel[10][10];
	panel.setLayout(new GridLayout(10,10));
	llenarMatriz();
	add(panel);
	return panel;
	}
	/**
	 * Este metodo se encargar de mostrar la area donde se introduccira la acciones de la nave
	 * @return panel2
	 */
	
	protected JComponent Comandos(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		panel2.setLayout(new GridBagLayout());
		
		Entrada = new JTextArea();
		
		Entrada.setOpaque(true); 
		Entrada.setAutoscrolls(true);;
		Entrada.setLineWrap(true);
	    Entrada.setWrapStyleWord(true);
		Entrada.setBorder(BorderFactory.createLineBorder(Color.black));
		Entrada.setBackground(Color.white);
		Entrada.setFont(new Font("Arial",Font.ITALIC,20));
		scroll=new JScrollPane(Entrada);
		
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx=0;
		gbc.gridy=0;
		gbc.gridheight=2;
		gbc.gridwidth=6;
		gbc.fill = GridBagConstraints.BOTH;;
		gbc.weighty = 1.0;
		gbc.weightx = 0.0;
		panel2.add(scroll,gbc);
		gbc.weighty = 0.0;
		gbc.weightx = 0.0;


		boton5 = new JButton("Run");
		gbc.gridx=2;
		gbc.gridy=4;
		gbc.gridy=3;
		gbc.gridwidth=1;
		gbc.gridheight=2;
		panel2.add(boton5,gbc);
		
		boton1 = new JButton("Step");
		gbc.gridx=0;
		gbc.gridy=3;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		panel2.add(boton1,gbc);

		boton2 = new JButton("Turn");
		gbc.gridx=1;
		gbc.gridy=3;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		panel2.add(boton2,gbc);

		boton3 = new JButton("Right");
		gbc.gridx=1;
		gbc.gridy=4;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		panel2.add(boton3,gbc);

		boton4 = new JButton("Left");
		gbc.gridx=0;
		gbc.gridy=4;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		panel2.add(boton4,gbc);
		
		this.boton1.addActionListener(this);
		this.boton2.addActionListener(this);
		this.boton3.addActionListener(this);
		this.boton4.addActionListener(this);
		this.boton5.addActionListener(this);
		return panel2;
	 }
	/**
	 * Metodo que graficar la matriz del nivel
	 * @return panel
	 */

	public JPanel llenarMatriz(){
		for (int i=0;i<TamanoFila;i++){
            for (int j=0;j<TamanoColumna;j++){
            	Grafico[i][j] = new JLabel();
        		Grafico[i][j].setOpaque(true);
        		Grafico[i][j].setBounds((i*TamanoCuadrado)+30,(j*TamanoCuadrado)+30,TamanoCuadrado,TamanoCuadrado);
        		Grafico[i][j].setVisible(true);
                Grafico[i][j].setIcon(objOp.getimagen(MatrizLogica[i][j]));
                panel.add(Grafico[i][j]);
            }
        }
		return panel;
	}
	
	/**
	 * Metodo que maniesta la acciones que moveran a la nave dentro del escenario
	 */
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==boton1){
			Entrada.setText(Entrada.getText()+"step ");
		}

		if(e.getSource()==boton2){
			Entrada.setText(Entrada.getText()+"turn ");
		}
		
		if(e.getSource()==boton3){
			Entrada.setText(Entrada.getText()+"Right ");
		
		}

		if(e.getSource()==boton4){
			Entrada.setText(Entrada.getText()+"Left ");
		}
		
		if(e.getSource()==boton5){
			leerComandos();
		}if(e.getSource()==Salir){
			try {
				objOp.saveProgress();
				System.exit(0);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		if(e.getSource()==Menu_Principal){
			clip.stop();
			MenuPrincipal Menu = new MenuPrincipal();
			llenarMatriz().removeAll();
			Comandos().removeAll();
			this.hide();
		}
		
		if(e.getSource()==Guia){
			Instrucciones guia = new Instrucciones();
		}

	}
	/**
	 * Metodo que recolecta la informacion ingresada y llama a la clase run
	 */
	
	public void leerComandos(){
		
		String s[] = Entrada.getText().split("\\r?\\n");
		ArrayList <String> arrList = new ArrayList<>(Arrays.asList(s)) ;
		
		objOp.obtenerPosicionNave(MatrizLogica);
		//posnave=MatrizLogica[Datos.getPosiY()][Datos.getPosiX()];
		//PosX=Datos.getPosiX();
		//PosY=Datos.getPosiY();
		Run Mover = new Run(Grafico,arrList,Nivel);
		Mover.start();
		//System.out.println("-- Imprimiendo el array: " + arrList);
	}//end of LeerComandos (method)
	/**
	 * Este metodo detecta la accion de cerrar la ventana del JFrame de la clase
	 */
	public void cerrar(){
		try{
			this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					cofirmarSalida();
				}
			});
			this.setVisible(true);
		}catch(Exception e){
			e.printStackTrace();
		}
		}
	/**
	 * Este metodo valida si desea cerrar la ventana del menu pricipal
	 */
	private void cofirmarSalida() {
		int valor =JOptionPane.showConfirmDialog(this,"¿Estas seguro de cerrar la aplicacion?","Advertencia",JOptionPane.YES_NO_OPTION);
		if(valor==JOptionPane.YES_OPTION){
			System.exit(0);
		}
	}


	public Interfaz() {
		
	}
	
	
}
