package CodePlay;
import java.lang.NumberFormatException;
import java.lang.NullPointerException;

public class EstructuraDatos{
private static int miMatriz[][];

	public EstructuraDatos(){
	}

	/**
	 * Este metodo crea una matriz de tipo miMatriz parseando la matriz
	 * de Strings recuperada del archivo de texto del mundo.
	 */
	public void saveMatriz(String[][] arreglo){
		miMatriz = new int [10][10];
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				try{
					miMatriz[i][j]=Integer.parseInt(arreglo[i][j]);
				}catch(NumberFormatException e){					
					miMatriz[i][j]=69; /** Si la excepcion se efectua, se setea un valor especifico que va a arrojar error para la vista*/
					
				}
			}
		}
	}

	public int[][] getmatriz(){
		return miMatriz;
	}

	/** Variables de Configuracion*/
	private static int mundoActivo;
	private static int cantidadMundos;
	private static int cantidadVacas;

	/** Coordenadas*/
	private static int PosiX;
	private static int PosiY;

	/**Coordenadas vacas*/
	private static int PosiXv;
	private static int PosiYv;
	
	/**
	 * Gets y Sets
	 */
	
	public int getVacas(){
		return cantidadVacas;
	}
	protected void setVacas(int cantidadVacas){
		this.cantidadVacas = cantidadVacas;
	}
	public int getPosiX(){
		return PosiX;
	}
	protected void setPosiX(int paramPosiX){
		this.PosiX = paramPosiX;
	}
	public int getPosiY(){
		return PosiY;
	}
	protected void setPosiY(int paramPosiY){
		this.PosiY = paramPosiY;
	}
	public int getMundoActivo(){
		return mundoActivo;
	}
	protected void setMundoActivo(int paramMundoActivo){
		this.mundoActivo = paramMundoActivo;
	}
	public int getCantidadMundos(){
		return cantidadMundos;
	}
	protected void setCantidadMundos(int paramCantidadMundos){
		this.cantidadMundos = paramCantidadMundos;
	}
	public int getPosiXv(){
		return PosiXv;
	}
	protected void setPosiXv(int paramPosiXv){
		this.PosiXv = paramPosiXv;
	}
	public int getPosiYv(){
		return PosiYv;
	}
	protected void setPosiYv(int paramPosiYv){
		this.PosiYv = paramPosiYv;
	}

	
}
