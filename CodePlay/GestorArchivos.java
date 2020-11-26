package CodePlay;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;


public class GestorArchivos{
	//Variables que recogeran los datos de los archivos se enviaran a la clase Datos
		EstructuraDatos data = new EstructuraDatos();
		BufferedReader br = null;
		private static String[] datos;

		public GestorArchivos(){
		}

		/**
		* Metodo que lee el archivo del estado de la partida guardada anteriormente
		*/
		public void leerSave()throws FileNotFoundException, IOException{
			File archivo = new File ("Save.txt");
			Scanner lector;

			try{
				 lector = new Scanner(archivo);
				 while(lector.hasNext()){
					 data.setMundoActivo(lector.nextInt());
					 data.setCantidadMundos(lector.nextInt());
		     }
		     lector.close();
			}catch (FileNotFoundException e){
				e.printStackTrace();
			}
		}

		/**
		* Metodo que lee el archivo de configuración del juego
		*/
		public void leerConfig()throws FileNotFoundException, IOException{
			File archivo = new File ("Config.txt");
			Scanner lector;

			try{
				 lector = new Scanner(archivo);
				 while(lector.hasNext()){
					 data.setMundoActivo(lector.nextInt());
					 data.setCantidadMundos(lector.nextInt());
		     }
		     lector.close();
			}catch (FileNotFoundException e){
				e.printStackTrace();
			}
		}

		/**
		* Metodo que lee el archivo de configuración del juego
		*/
		public void leerNivel(){

			String filename="Nivel"+data.getMundoActivo()+".txt";
			String strLine,x ="";
			File file = new File(filename);
			 if(file.exists()) {
				 try{
						br = new BufferedReader( new FileReader( filename ) );
						String[][] jo = new String[10][10];
				    //contador
				    int contador = 0;
						while((strLine = br.readLine()) != null){
							String[] values = strLine.split(" ");
							for (int i = 0; i<values.length; i++) {
				      	//se obtiene el primer caracter de el arreglo de strings
				        jo[contador][i] = values[i].toString();
				      }
				      contador++;
						}
						data.saveMatriz(jo);
					}catch(Exception e){
						//
					}
        } else {
           JOptionPane.showMessageDialog(null,"No existe archivo");
           System.exit(0);
        }
		}
}
