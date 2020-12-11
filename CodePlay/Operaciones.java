package CodePlay;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * Esta clase se encarga de conetener todos los metodos
 * para modificar la matriz, es decir, esta clase es la
 * que se va a entender con los comandos que el usuario ingrese.
 */
public class Operaciones{

	public Operaciones(){
	}

	EstructuraDatos objDatos = new EstructuraDatos();
	int cont;

	/**
	 * Este metodo me permite validar si un archivo leido cumple las
	 * caracteristicas de un mundo
	 */
	public boolean verificarInconsistencias(){
		int inconsistencias = 0;
		boolean mundoOK = true;

		for(int i = 0; i <10; i++){
			for(int j = 0; j < 10; j++){
				if((objDatos.getmatriz()[i][j] == 0)||(objDatos.getmatriz()[i][j] == 1)|| (objDatos.getmatriz()[i][j] == 2) || (objDatos.getmatriz()[i][j] == 3)
					|| (objDatos.getmatriz()[i][j] == 4 ) || (objDatos.getmatriz()[i][j] == 51) || (objDatos.getmatriz()[i][j] == 52)
				  || (objDatos.getmatriz()[i][j] == 53) || (objDatos.getmatriz()[i][j] == 54) || (objDatos.getmatriz()[i][j] == 55)
					|| (objDatos.getmatriz()[i][j] == 56) || (objDatos.getmatriz()[i][j] == 57) || (objDatos.getmatriz()[i][j] == 58)){
				}else{
					inconsistencias++;
				}
			}
		}
		if(inconsistencias > 0){
			mundoOK = false;
		}else{
			mundoOK = true;
		}
		return mundoOK;
	}

	/**
	 * Este metodo me permite validar si no existen mas niveles de juego.
	 */
	public void nextLevel(){
		if ((((objDatos.getMundoActivo()+1)))<=objDatos.getCantidadMundos()){
			objDatos.setMundoActivo((objDatos.getMundoActivo()+1));
			JOptionPane.showMessageDialog(null, "Iniciando siguiente nivel...");
		}
		else{
			JOptionPane.showMessageDialog(null, "No existen mas niveles. \nFin del juego.","Anuncio",JOptionPane.INFORMATION_MESSAGE, null);
			System.exit(0);
		}
	}

	/**
	 * Este metodo obtiene la posicion de la(s) vaca(s).
	 */
	public void obtenerPosicionVaca(int matriz[][]){
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				if( (matriz[i][j] == 4)){
					objDatos.setPosiYv(i);
					objDatos.setPosiXv(j);
				}
			}
		}
		System.out.println(objDatos.getPosiYv()+" "+objDatos.getPosiXv());
		System.out.println(objDatos.getPosiYv()+" "+objDatos.getPosiXv());
	}

	/**
	 * Este metodo de control verifica si es posible cargar un mundo, y en caso tal, pintarlo.
	 */
	public void comenzar(boolean next) throws FileNotFoundException, IOException{
		GestorArchivos hi = new GestorArchivos();
		if (next==false){
			hi.leerConfig();
			hi.leerNivel();

			if (verificarInconsistencias() == true){
			Interfaz juego = new Interfaz();
			juego.Pintar();
			}else{
				JOptionPane.showMessageDialog(null,"No se puede cargar el mundo, su archivo esta corrupo","Error" ,JOptionPane.ERROR_MESSAGE, null);
				System.exit(0);
			}
		}else{
			if(next==true)
			hi.leerSave();
			hi.leerNivel();

			if (verificarInconsistencias() == true){
			Interfaz juego = new Interfaz();
			juego.Pintar();
			}else{
				JOptionPane.showMessageDialog(null,"No se puede cargar el mundo, su archivo esta corrupo","Error" ,JOptionPane.ERROR_MESSAGE, null);
				System.exit(0);
			}
		}
	}

	/**
	 * Este metodo de control reconoce la cantidad de vacas por nivel.
	 */
	public void CantidadVacas(int matriz[][]){
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				if( (matriz[i][j] == 4)){
					cont++;
				}
			}
		}
		objDatos.setVacas(cont);
	}

	/**
	 * Este metodo compara la posicion de la nave con la posicion de la vaca y rebaja la cantidad de vacas cada que
	 * estas posiciones coinciden.
	 */
	public void abduceVaca(int posx,int posy){
		if (posy== objDatos.getPosiXv()&& posx== objDatos.getPosiYv()){
			objDatos.setVacas(objDatos.getVacas()-1);
		}
	}

	/**
	 * Metodo que finaliza el nivel, puede optimizarse eliminando el else?
	 */
	public boolean endLevel(){
		if (objDatos.getVacas()==0){
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 * Este metodo me sirve para obtener la posicion inicial de la nave
	 */
	public void obtenerPosicionNave(int matriz[][]){
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				if( (matriz[i][j] == 51) || (matriz[i][j] == 52) || (matriz[i][j] == 53) || (matriz[i][j] == 54) || (matriz[i][j] == 55) || (matriz[i][j] == 56) ||
                (matriz[i][j] == 57) || (matriz[i][j] == 58) ){
					objDatos.setPosiY(i);
					objDatos.setPosiX(j);
				}
			}
		}
	}

	/**
	 * Este metodo me sirve guardar el nivel en el cual quede
	 */
	public void saveProgress() throws IOException {
    File archivo = new File("Save.txt");
    PrintWriter bw = new PrintWriter(archivo);
    if(archivo.exists()) {
        bw.println(objDatos.getMundoActivo());
        bw.print(objDatos.getCantidadMundos());
    } else {
       JOptionPane.showConfirmDialog(null,"No existe archivo");
       System.exit(0);
    }
    bw.close();
  }
	/**
	* Este metodo setea la imagen correspondiente a cada convencion de la matriz numerica para la matriz grafica
	*/
	public ImageIcon getimagen(int ubicacion){
		switch(ubicacion){
			case 0:
				return new ImageIcon(getClass().getResource("/Imagenes/pasto.gif"));
			case 1:
				return new ImageIcon(getClass().getResource("/Imagenes/estacion.gif"));
			case 2:
				return new ImageIcon(getClass().getResource("/Imagenes/arbol.gif"));
			case 3:
				return new ImageIcon(getClass().getResource("/Imagenes/antena.gif"));
			case 4:
				return new ImageIcon(getClass().getResource("/Imagenes/vaca.gif"));
			case 5:
				return new ImageIcon(getClass().getResource("/Imagenes/antenaCrash.gif"));
			case 6:
				return new ImageIcon(getClass().getResource("/Imagenes/arbolCrash.gif"));
			case 7:
				return new ImageIcon(getClass().getResource("/Imagenes/antenaCrash.gif"));
			case 8:
				return new ImageIcon(getClass().getResource("/Imagenes/abduccion.gif"));
			case 9:
				return new ImageIcon(getClass().getResource("/Imagenes/nave-Puente.gif"));
			case 51:
				return new ImageIcon(getClass().getResource("/Imagenes/nave-0.gif"));
			case 52:
				return new ImageIcon(getClass().getResource("/Imagenes/nave-45.gif"));
			case 53:
				return new ImageIcon(getClass().getResource("/Imagenes/nave-90.gif"));
			case 54:
				return new ImageIcon(getClass().getResource("/Imagenes/nave-135.gif"));
			case 55:
				return new ImageIcon(getClass().getResource("/Imagenes/nave-180.gif"));
			case 56:
				return new ImageIcon(getClass().getResource("/Imagenes/nave-225.gif"));
			case 57:
				return new ImageIcon(getClass().getResource("/Imagenes/nave-270.gif"));
			case 58:
				return new ImageIcon(getClass().getResource("/Imagenes/nave-315.gif"));
			default:
				return new ImageIcon(getClass().getResource("/Imagenes/pasto.gif"));
		}
	}

	

}
