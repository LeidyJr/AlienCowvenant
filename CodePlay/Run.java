package CodePlay;

import java.applet.AudioClip;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Este Clases se encargar de realizar el proceso de ir pintando el recorrido
 * del personaje del juego sobre el escenario del nivel 
 */
public class Run extends Thread {
	Icon iconoWinner = new ImageIcon(getClass().getResource("/Imagenes/abduccion.gif"));
	Icon iconoSeFue = new ImageIcon(getClass().getResource("/Imagenes/vaca.gif"));
	Icon iconoCrashAntena = new ImageIcon(getClass().getResource("/Imagenes/antenaCrash.gif"));
	Icon iconoCrashArbol = new ImageIcon(getClass().getResource("/Imagenes/arbolCrash.gif"));
	Icon iconFueraPlaneta = new ImageIcon(getClass().getResource("/Imagenes/fuera_del_planeta.gif"));
	private Operaciones objOp = new Operaciones();
	
	JLabel[][] Grafico;
	EstructuraDatos datos = new EstructuraDatos();
	//JPanel panel;
	AudioClip sonido1;
	int rest;
	int Orientacion,recorrido,no_ejecutar;
	int posnave = Interfaz.MatrizLogica[datos.getPosiY()][datos.getPosiX()];
	int posx=datos.getPosiX();
	int posy=datos.getPosiY();
	boolean choque=true;
	//boolean meta=true;
	//boolean VacaAbducida=true;
	JMenu nivel;
	ArrayList <String> arrList;
	String [] botones1 = { "Reiniciar", "Siguiente", "Salir" };
	 
	Object [] botones = { "Reiniciar", "Siguiente", "Salir" };
	
	/**
	 * Contructor que se encargar de recibir el grafico, la lista de comandos y el nivel en que se encuentra 
	 * guardandolas dentro de variable a utilizar en la clase
	 * @param Grafico
	 * @param lista
	 * @param nivel
	 */

	public Run(JLabel[][] Grafico,ArrayList <String> lista,JMenu nivel) {
		this.arrList=lista;
		this.Grafico=Grafico;
		this.nivel=nivel;
	}
	/**
	 * Metodo que se encargar del subproceso de ir pintado el recorrido de la nave
	 * segundo la lista de los comandos recibos 
	 */
	public void run(){
		objOp.CantidadVacas(Interfaz.MatrizLogica);
		//System.out.println(datos.getMundoActivo());
		for(int i=0; i<arrList.size();i++){
			String comandoValue[] = arrList.get(i).split(" ");
			if (comandoValue.length > 2 || comandoValue.length < 2 ){
				//Devolver esto en un JFrame!!!!
				JOptionPane.showMessageDialog(null,"Error, solo se acepta una instruccion y un valor.","Error" ,JOptionPane.ERROR_MESSAGE, null);
				//Forzar salida del metodo principal -for de leerArreglo
				i = arrList.size();
			} else {
				switch (comandoValue[0].toUpperCase()) {
					case "STEP":
						try {
							//System.out.println("Estoy en el Step");
							recorrido=Integer.parseInt(comandoValue[1]);
							for (int z = 0;z < Integer.parseInt(comandoValue[1]); z++) {
								//System.out.println(" --- runAlien function executed! ----");
								runAlien();
								
								if(choque){
									
									try {
										
										Thread.sleep(1000);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
								}else
									try {
										Thread.sleep(1);
										z = Integer.parseInt(comandoValue[1]);
										
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								//validateMundoDesplazamiento(); //- validar espacio de desplazamiento de la matriz para ver que sea posible.
								//runAlien(); //-> modifica la matriz.
								//InterfazMapa.metodoDeInterfazMapaComoSeLlame(); // ejecutar la repintada actualizando la interfaz grafica (incluye tiempo de espera para ver la animacion)
							
							}
							
							if(objOp.endLevel()){
								i = arrList.size();
								break;
							}else{
								if(choque){
									continue;
								}else{	
									no_ejecutar=2;
									AnuncioPerdiste();
									i = arrList.size();
									break;
								}
							}
							
							//continue to read the next arraylist
						} catch (NumberFormatException nfe){
							//Devolver esto en un JFrame !!!
							JOptionPane.showMessageDialog(null,"El valor de la instruccibon no es valido! (STEP)","Error" ,JOptionPane.ERROR_MESSAGE, null);
							//Forzar salida del metodo principal -for de leerArreglo
							i = arrList.size();
							break;
						}
					case "TURN":
						String rotationShip = comandoValue[1].toUpperCase();
						//System.out.println("Estoy en el Turn");
						//turnAlien modifica el estado interno de orientacion de la nave.
						
						if ( rotationShip.equals("0") || rotationShip.equals("-360") || rotationShip.equals("360") ) {
							//System.out.println(" --- turnAlien function executed - 0,360,-360! ----");
							
							if(posnave==52){
								turnAlien(objOp.getimagen(52),52);
							}else{
								if(posnave==51){
									turnAlien(objOp.getimagen(51),51);
								}else{
									if(posnave==58){
										turnAlien(objOp.getimagen(58),58);
									}else{
										if(posnave==57){
											turnAlien(objOp.getimagen(57),57);
										}else{
											if(posnave==56){
												turnAlien(objOp.getimagen(56),56);
											}else{
												if(posnave==55){
													turnAlien(objOp.getimagen(55),55);
												}else
													if(posnave==54){
														turnAlien(objOp.getimagen(54),54);
													}else{
														if(posnave==53){
															turnAlien(objOp.getimagen(53),53);
														}
															
													}
											}
												
										}
									}
								}
							}
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							continue;
						} else if ( rotationShip.equals("RIGHT")) {
							//System.out.println(" --- turnAlien function executed - RIGHT! ----");
							
							if(posnave==52){
								turnAlien(objOp.getimagen(51),51);
							}else{
								if(posnave==51){
									turnAlien(objOp.getimagen(57),57);
								}else{
									if(posnave==58){
										turnAlien(objOp.getimagen(57),57);
									}else{
										if(posnave==57){
											turnAlien(objOp.getimagen(55),55);
										}else{
											if(posnave==56){
												turnAlien(objOp.getimagen(55),55);
											}else{
												if(posnave==55){
													turnAlien(objOp.getimagen(53),53);
												}else
													if(posnave==54){
														turnAlien(objOp.getimagen(53),53);
													}else{
														if(posnave==53){
															turnAlien(objOp.getimagen(51),51);
														}
															
													}
											}
												
										}
									}
								}
							}
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							continue;
						} else if ( rotationShip.equals("45") || rotationShip.equals("-315") ) {
							//System.out.println(" --- turnAlien function executed 45 -315 ----");
							
							if(posnave==52){
								turnAlien(objOp.getimagen(53),53);
							}else{
								if(posnave==51){
									turnAlien(objOp.getimagen(52),52);
								}else{
									if(posnave==58){
										turnAlien(objOp.getimagen(51),51);
									}else{
										if(posnave==57){
											turnAlien(objOp.getimagen(58),58);
										}else{
											if(posnave==56){
												turnAlien(objOp.getimagen(57),57);
											}else{
												if(posnave==55){
													turnAlien(objOp.getimagen(56),56);
												}else
													if(posnave==54){
														turnAlien(objOp.getimagen(55),55);
													}else{
														if(posnave==53){
															turnAlien(objOp.getimagen(54),54);
														}
															
													}
											}
												
										}
									}
								}
							}
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							continue;
						} else if (rotationShip.equals("90") || rotationShip.equals("-270")) {
							//System.out.println(" --- turnAlien function executed 90 -270 ----");
							
							if(posnave==52){
								turnAlien(objOp.getimagen(54),54);
							}else{
								if(posnave==51){
									turnAlien(objOp.getimagen(53),53);
								}else{
									if(posnave==58){
										turnAlien(objOp.getimagen(52),52);
									}else{
										if(posnave==57){
											turnAlien(objOp.getimagen(51),51);
										}else{
											if(posnave==56){
												turnAlien(objOp.getimagen(58),58);
											}else{
												if(posnave==55){
													turnAlien(objOp.getimagen(57),57);
												}else
													if(posnave==54){
														turnAlien(objOp.getimagen(56),56);
													}else{
														if(posnave==53){
															turnAlien(objOp.getimagen(55),55);
														}
															
													}
											}
												
										}
									}
								}
							}
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							continue;
						} else if (rotationShip.equals("135") || rotationShip.equals("-225") ) {
							//System.out.println(" --- turnAlien function executed 135 -225 ----");
							
							if(posnave==52){
								turnAlien(objOp.getimagen(55),55);
							}else{
								if(posnave==51){
									turnAlien(objOp.getimagen(54),54);
								}else{
									if(posnave==58){
										turnAlien(objOp.getimagen(53),53);
									}else{
										if(posnave==57){
											turnAlien(objOp.getimagen(52),52);
										}else{
											if(posnave==56){
												turnAlien(objOp.getimagen(51),51);
											}else{
												if(posnave==55){
													turnAlien(objOp.getimagen(58),58);
												}else
													if(posnave==54){
														turnAlien(objOp.getimagen(57),57);
													}else{
														if(posnave==53){
															turnAlien(objOp.getimagen(56),56);
														}
															
													}
											}
												
										}
									}
								}
							}
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							continue;
						} else if (rotationShip.equals("180") || rotationShip.equals("-180") ) {
							//System.out.println(" --- turnAlien function executed 180 -180! ----");
							
							if(posnave==52){
								turnAlien(objOp.getimagen(56),56);
							}else{
								if(posnave==53){
									turnAlien(objOp.getimagen(57),57);
								}else{
									if(posnave==54){
										turnAlien(objOp.getimagen(58),58);
									}else{
										if(posnave==55){
											turnAlien(objOp.getimagen(51),51);
										}else{
											if(posnave==56){
												turnAlien(objOp.getimagen(52),52);
											}else{
												if(posnave==57){
													turnAlien(objOp.getimagen(53),53);
												}else
													if(posnave==58){
														turnAlien(objOp.getimagen(54),54);
													}else{
														if(posnave==51){
															turnAlien(objOp.getimagen(55),55);
														}
															
													}
											}
												
										}
									}
								}
							}
							
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							continue;
						} else if (rotationShip.equals("LEFT")){
							//System.out.println(" --- turnAlien function executed #55 - LEFT! ----");
							
							if(posnave==52){
								turnAlien(objOp.getimagen(53),53);
							}else{
								if(posnave==53){
									turnAlien(objOp.getimagen(55),55);
								}else{
									if(posnave==54){
										turnAlien(objOp.getimagen(54),55);
									}else{
										if(posnave==55){
											turnAlien(objOp.getimagen(57),57);
										}else{
											if(posnave==56){
												turnAlien(objOp.getimagen(57),57);
											}else{
												if(posnave==57){
													turnAlien(objOp.getimagen(51),51);
												}else
													if(posnave==58){
														turnAlien(objOp.getimagen(51),51);
													}else{
														if(posnave==51){
															turnAlien(objOp.getimagen(53),53);
														}
															
													}
											}
												
										}
									}
								}
							}
							
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							continue;
						} else if (rotationShip.equals("225") || rotationShip.equals("-135") ) {
							//System.out.println(" --- turnAlien function executed 225 -135 ----");
							
							if(posnave==52){
								turnAlien(objOp.getimagen(57),57);
							}else{
								if(posnave==53){
									turnAlien(objOp.getimagen(58),58);
								}else{
									if(posnave==54){
										turnAlien(objOp.getimagen(51),51);
									}else{
										if(posnave==55){
											turnAlien(objOp.getimagen(52),52);
										}else{
											if(posnave==56){
												turnAlien(objOp.getimagen(53),53);
											}else{
												if(posnave==57){
													turnAlien(objOp.getimagen(54),54);
												}else
													if(posnave==58){
														turnAlien(objOp.getimagen(55),55);
													}else{
														if(posnave==51){
															turnAlien(objOp.getimagen(56),56);
														}
															
													}
											}
												
										}
									}
								}
							}
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							continue;
						} else if (rotationShip.equals("270") || rotationShip.equals("-90")) {
							//System.out.println(" --- turnAlien function executed 270 -90 ----");
							
							if(posnave==52){
								turnAlien(objOp.getimagen(52),58);
							}else{
								if(posnave==53){
									turnAlien(objOp.getimagen(51),51);
								}else{
									if(posnave==54){
										turnAlien(objOp.getimagen(52),52);
									}else{
										if(posnave==55){
											turnAlien(objOp.getimagen(53),53);
										}else{
											if(posnave==56){
												turnAlien(objOp.getimagen(54),54);
											}else{
												if(posnave==57){
													turnAlien(objOp.getimagen(55),55);
												}else
													if(posnave==58){
														turnAlien(objOp.getimagen(56),56);
													}else{
														if(posnave==51){
															turnAlien(objOp.getimagen(57),57);
														}
															
													}
											}
												
										}
									}
								}
							}
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							continue;
						} else if (rotationShip.equals("315") || rotationShip.equals("-45") ) {
							//System.out.println(" --- turnAlien function executed 315 -45 ----");
							
							if(posnave==52){
								turnAlien(objOp.getimagen(51),51);
							}else{
								if(posnave==53){
									turnAlien(objOp.getimagen(52),52);
								}else{
									if(posnave==54){
										turnAlien(objOp.getimagen(53),53);
									}else{
										if(posnave==55){
											turnAlien(objOp.getimagen(54),54);
										}else{
											if(posnave==56){
												turnAlien(objOp.getimagen(55),55);
											}else{
												if(posnave==57){
													turnAlien(objOp.getimagen(56),56);
												}else
													if(posnave==58){
														turnAlien(objOp.getimagen(57),57);
													}else{
														if(posnave==51){
															turnAlien(objOp.getimagen(58),58);
														}
															
													}
											}
												
										}
									}
								}
							}
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							continue;
						} else {
							//Forzar salida del metodo principal -for de leerArreglo
							i = arrList.size();
							JOptionPane.showMessageDialog(null,"El valor de la instruccion no es valido! (TURN)","Error" ,JOptionPane.ERROR_MESSAGE, null);
							//Devolver esto en un JFrame !!!
							break;
						}
						//end of turn
					default:
						//Forzar salida del metodo principal -for de leerArreglo
						i = arrList.size();
						//Devolver esto n un JFrame
						//System.out.println("Tiene un error en la linea de instruccion, verifiquelo e intente nuevamente.");
					}
				}
			}
		
		
			if(objOp.endLevel()){
					AnuncioGanaste();
			}else {
				if(no_ejecutar!=2){	
					AnuncioPerdiste();
				}
			}
		///}
		//end of leerArreglo (for)
	}//end of Run (method)
	
	/**
	 * Meteodo encargado de mostras una ventana felicitando al usurio por a ver completado el nivel
	 * y preguntandole si desea volver a intentar el nivel o pasar el siguiente nivel
	 */
	public void AnuncioGanaste(){
		rest=JOptionPane.showOptionDialog (null,  "Felicitaciones", "Anuncio", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, iconoWinner, botones, botones[0]);
		if(rest==JOptionPane.YES_OPTION){
			Interfaz.Entrada.setText("");
			actulizar();
		}else{
			if(rest==JOptionPane.NO_OPTION){
				SiguienteNivel();
			}else{
				if(rest==JOptionPane.CANCEL_OPTION){
					int choice = JOptionPane.showOptionDialog(null, 
						      "Deseas Guardar el nivel?", "Quit?",  JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
					if(choice==JOptionPane.YES_OPTION){
						try {
							objOp.saveProgress();
							System.exit(0);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else{
						System.exit(0);
					}
				}else{
					Interfaz.Entrada.setText("");
					actulizar();
				}
			}
		}
	}
	
	/**
	 * Metodo que muestra una ventana anunciando al usuario que perdio el nivel
	 * y preguntando si desea volver a intentarlo o seguir al siguiente nivel 
	 */
	public void AnuncioPerdiste(){
		if(Interfaz.MatrizLogica[posy][posx]==2){
			rest=JOptionPane.showOptionDialog (null,  "Rodea los arboles para no chocarte", "Alerta", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, iconoCrashArbol, botones, botones[0]);
		}else{
			if(Interfaz.MatrizLogica[posy][posx]==3){
				rest=JOptionPane.showOptionDialog (null,  "No puedes atravesar los campos electricos de las torres", "Alerta", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, iconoCrashAntena, botones, botones[0]);
			}else{
				rest=JOptionPane.showOptionDialog (null,  "Parece que tienes errores en tus instrucciones", "Alerta", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, iconoSeFue, botones, botones[0]);
			}
		}
		
		if(rest==JOptionPane.YES_OPTION){
			Interfaz.Entrada.setText("");
			actulizar();
		}else{
			if(rest==JOptionPane.NO_OPTION){
				SiguienteNivel();
			}else
			{
				if(rest==JOptionPane.CANCEL_OPTION){
					int choice = JOptionPane.showOptionDialog(null, 
						      "Deseas Guardar el nivel?", "Quit?",  JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
					if(choice==JOptionPane.YES_OPTION){
						try {
							objOp.saveProgress();
							System.exit(0);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else{
						System.exit(0);
					}
				}else{
					Interfaz.Entrada.setText("");
					actulizar();
				}
			}
		}
	}
	/**
	 * Metodo que reproduce un sonido al llegar a la vaca
	 */
	public void SonidoAbduccion(){
		sonido1 = java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/Abduccion.wav"));
		sonido1.play();
	}
	/**
	 * Metodo que reproduce un sonido al chocar con una antena 
	 */
	public void SonidoAntena(){
		sonido1 = java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/Cortocircuito.wav"));
		sonido1.play();
	}
	/**
	 * Metodo que reproduce un sonido al chocar con un arbol
	 */
	public void SonidoArbol(){
		sonido1 = java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/Incendio.wav"));
		sonido1.play();
	}
	/**
	 * Metodo que reproduce un sonido al salirse de los limites del escenario
	 */
	
	public void SonidoLimites(){
		sonido1 = java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/portal.wav"));
		sonido1.play();
	}
	/**
	 * Metodo que se encargar de leer el siguiente nivel y visualizarlo
	 */
	public void SiguienteNivel(){
		Interfaz.Entrada.setText("");
		objOp.nextLevel();
		GestorArchivos next = new GestorArchivos();
		next.leerNivel();
		if(objOp.verificarInconsistencias() == true){
			Interfaz.MatrizLogica=datos.getmatriz();
			actulizar();
		}else{
			JOptionPane.showMessageDialog(null,"No se puede cargar el mundo, su archivo esta corrupo","Error" ,JOptionPane.ERROR_MESSAGE, null);
			System.exit(0);
		}
	}
	/**
	 * Metodo que se encargar de dar y pintar la direccion en la se encuentra la nave
	 */
	
		public void turnAlien(ImageIcon sentido,int orientacion){
			if(Interfaz.MatrizLogica[posy][posx]==4){
				try {
					Thread.sleep(900);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					}
				}
			if(Interfaz.MatrizLogica[posy][posx]==1){
				Grafico[posy][posx].setIcon(objOp.getimagen(9));
			}else{
				Grafico[posy][posx].setIcon(sentido);
			}
			
			posnave=orientacion;
			
		}
		/**
		 * Metodo que pinta el nivel del juego 
		 */
		public void actulizar(){
			nivel.setText("Nivel "+datos.getMundoActivo());
			for (int i=0;i<10;i++){
	            for (int j=0;j<10;j++){
	                Grafico[i][j].setIcon(objOp.getimagen(Interfaz.MatrizLogica[i][j]));
	            }
	        }
		}
		
		/**
		 * Meteodo que se encargar de mover la nave a la direccion en la se encuentra 
		 * y verifica si la siguiente casilla se encuentra vacio o se encontrara con objeto con el cual se va a colisionar
		 * o si se saldra del escenario
		 */
		public void runAlien(){
			switch(posnave){
			
				case 51:
					if(Interfaz.MatrizLogica[posy][posx]==4){
						try {
							Thread.sleep(900);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							}
						}
					if(Interfaz.MatrizLogica[posy][posx]==3||Interfaz.MatrizLogica[posy][posx]==2){
						break;
					}else{
						if((posx+1)>9){
							//System.out.print(posx+"+"+posy);
							SonidoLimites();
							choque=false;
							if(Interfaz.MatrizLogica[posy][posx]==0){
								Grafico[posy][posx].setIcon(objOp.getimagen(0));
								JOptionPane.showMessageDialog(null,"Por accidente abriste un portal a otro planeta","No te encuentras en la tierra ",JOptionPane.WARNING_MESSAGE, iconFueraPlaneta);
							}else{
								if(Interfaz.MatrizLogica[posy][posx]==3)
									Grafico[posy][posx].setIcon(objOp.getimagen(1));
								if(Interfaz.MatrizLogica[posy][posx]==2)
									Grafico[posy][posx].setIcon(objOp.getimagen(2));
								else
									Grafico[posy][posx].setIcon(objOp.getimagen(0));
								
							}break;
							}else{
							if(Interfaz.MatrizLogica[posy][posx+1]==4){
								SonidoAbduccion();
								if(Interfaz.MatrizLogica[posy][posx]==1){
									Grafico[posy][posx].setIcon(objOp.getimagen(1));
								}else{
									Grafico[posy][posx].setIcon(objOp.getimagen(0));
								}
								datos.setVacas(datos.getVacas()-1);
								Grafico[posy][posx+1].setIcon(objOp.getimagen(8));
							}else{
								if(Interfaz.MatrizLogica[posy][posx+1]==3){
									choque=false;
									if(Interfaz.MatrizLogica[posy][posx]==1){
										Grafico[posy][posx].setIcon(objOp.getimagen(1));
									}else{
										Grafico[posy][posx].setIcon(objOp.getimagen(0));
									}
									Grafico[posy][posx+1].setIcon(objOp.getimagen(51));
								}else{
									if(Interfaz.MatrizLogica[posy][posx+1]==2){
										choque=false;
										if(Interfaz.MatrizLogica[posy][posx]==1){
											Grafico[posy][posx].setIcon(objOp.getimagen(1));
										}else{
											Grafico[posy][posx].setIcon(objOp.getimagen(0));
										}
										Grafico[posy][posx+1].setIcon(objOp.getimagen(51));
									}else{
										if(Interfaz.MatrizLogica[posy][posx+1]==1){
											Grafico[posy][posx].setIcon(objOp.getimagen(0));
											Grafico[posy][posx+1].setIcon(objOp.getimagen(9));
										}else{
											if(Interfaz.MatrizLogica[posy][posx]==3){
												Grafico[posy][posx].setIcon(objOp.getimagen(7));
											}else{
												if(Interfaz.MatrizLogica[posy][posx]==2){
													Grafico[posy][posx].setIcon(objOp.getimagen(6));
												}else{
													if(Interfaz.MatrizLogica[posy][posx]==1){
														Grafico[posy][posx].setIcon(objOp.getimagen(1));
													}else{
														if(Interfaz.MatrizLogica[posy][posx]==4){
															//System.out.println(datos.getVacas());
															//System.out.print(objOp.endLevel());
															if(objOp.endLevel()){
																choque=false;
																break;
															}
															try {
																Thread.sleep(1);
																sonido1.stop();
															} catch (InterruptedException e) {
																// TODO Auto-generated catch block
																e.printStackTrace();
															}
															Grafico[posy][posx].setIcon(objOp.getimagen(0));
														}else{
															Grafico[posy][posx].setIcon(objOp.getimagen(0));
														}
													}
													Grafico[posy][posx+1].setIcon(objOp.getimagen(51));
													}
												}
											}
										}
									}
								}
							}
						}
					posx=posx+1;
					
					if(Interfaz.MatrizLogica[posy][posx]==3){
						SonidoAntena();
						Grafico[posy][posx].setIcon(objOp.getimagen(7));
					}else{
						if(Interfaz.MatrizLogica[posy][posx]==2){
							SonidoArbol();
							Grafico[posy][posx].setIcon(objOp.getimagen(6));
							}
						}
				break;
				case 52:
					if(Interfaz.MatrizLogica[posy][posx]==4){
						try {
							Thread.sleep(900);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							}
						}
					if(Interfaz.MatrizLogica[posy][posx]==3||Interfaz.MatrizLogica[posy][posx]==2){
						break;
					}else{
						if((posx+1)>9||(posy-1)<0){
							SonidoLimites();
							//System.out.print(posx+"+"+posy);
							choque=false;
							if(Interfaz.MatrizLogica[posy][posx]==0){
								Grafico[posy][posx].setIcon(objOp.getimagen(0));
								JOptionPane.showMessageDialog(null,"Por accidente abriste un portal a otro planeta","No te encuentras en la tierra ",JOptionPane.WARNING_MESSAGE, iconFueraPlaneta);
							}else{
								if(Interfaz.MatrizLogica[posy][posx]==3)
									Grafico[posy][posx].setIcon(objOp.getimagen(1));
								if(Interfaz.MatrizLogica[posy][posx]==2)
									Grafico[posy][posx].setIcon(objOp.getimagen(2));
								else
									Grafico[posy][posx].setIcon(objOp.getimagen(0));
							}break;
							
						}else{
							if(Interfaz.MatrizLogica[posy][posx+1]==4){
								SonidoAbduccion();
								if(Interfaz.MatrizLogica[posy][posx]==1){
									Grafico[posy][posx].setIcon(objOp.getimagen(1));
								}else{
									Grafico[posy][posx].setIcon(objOp.getimagen(0));
								}
								datos.setVacas(datos.getVacas()-1);
								Grafico[posy-1][posx+1].setIcon(objOp.getimagen(8));
							}else{
								if(Interfaz.MatrizLogica[posy-1][posx+1]==3){
									choque=false;
									if(Interfaz.MatrizLogica[posy][posx]==1){
										Grafico[posy][posx].setIcon(objOp.getimagen(1));
									}else{
										Grafico[posy][posx].setIcon(objOp.getimagen(0));
									}
									Grafico[posy-1][posx+1].setIcon(objOp.getimagen(52));
								}else{
									if(Interfaz.MatrizLogica[posy-1][posx+1]==2){
										choque=false;
										if(Interfaz.MatrizLogica[posy][posx]==1){
											Grafico[posy][posx].setIcon(objOp.getimagen(1));
										}else{
											Grafico[posy][posx].setIcon(objOp.getimagen(0));
										}
										Grafico[posy-1][posx+1].setIcon(objOp.getimagen(52));
									}else{
										if(Interfaz.MatrizLogica[posy-1][posx+1]==1){
											Grafico[posy][posx].setIcon(objOp.getimagen(0));
											Grafico[posy-1][posx+1].setIcon(objOp.getimagen(1));
										}else{
											if(Interfaz.MatrizLogica[posy][posx]==3){
												Grafico[posy][posx].setIcon(objOp.getimagen(7));
											}else{
												if(Interfaz.MatrizLogica[posy][posx]==2){
													Grafico[posy][posx].setIcon(objOp.getimagen(6));
												}else{
													if(Interfaz.MatrizLogica[posy][posx]==1){
														Grafico[posy][posx].setIcon(objOp.getimagen(1));
													}else{
														if(Interfaz.MatrizLogica[posy][posx]==4){
															//System.out.println(datos.getVacas());
															//System.out.print(objOp.endLevel());
															if(objOp.endLevel()){
																choque=false;
																break;
															}
															try {
																Thread.sleep(1);
																sonido1.stop();
															} catch (InterruptedException e) {
																// TODO Auto-generated catch block
																e.printStackTrace();
															}
															Grafico[posy][posx].setIcon(objOp.getimagen(0));
														}else{
															Grafico[posy][posx].setIcon(objOp.getimagen(0));
														}
													}
													Grafico[posy-1][posx+1].setIcon(objOp.getimagen(52));
												}
											}
										}
									}
								}
							}
						}
					}
					posx=posx+1;
					posy=posy-1;
					if(Interfaz.MatrizLogica[posy][posx]==3){
						SonidoAntena();
						Grafico[posy][posx].setIcon(objOp.getimagen(7));
						
					}else{
						if(Interfaz.MatrizLogica[posy][posx]==2){
							SonidoArbol();
							Grafico[posy][posx].setIcon(objOp.getimagen(6));
							
					}}
					
				break;
				
				case 53:
					if(Interfaz.MatrizLogica[posy][posx]==4){
						try {
							Thread.sleep(900);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							}
						}
					if(Interfaz.MatrizLogica[posy][posx]==3||Interfaz.MatrizLogica[posy][posx]==2){
						break;
					}else{
						if((posy-1)<0){
							SonidoLimites();
							//System.out.print(posx+"+"+posy);
							choque=false;
							if(Interfaz.MatrizLogica[posy][posx]==0){
								Grafico[posy][posx].setIcon(objOp.getimagen(0));
								JOptionPane.showMessageDialog(null,"Por accidente abriste un portal a otro planeta","No te encuentras en la tierra ",JOptionPane.WARNING_MESSAGE, iconFueraPlaneta);
							}else{
								if(Interfaz.MatrizLogica[posy][posx]==3)
									Grafico[posy][posx].setIcon(objOp.getimagen(1));
								if(Interfaz.MatrizLogica[posy][posx]==2)
									Grafico[posy][posx].setIcon(objOp.getimagen(2));
								else
									Grafico[posy][posx].setIcon(objOp.getimagen(0));
						}break;
						}else{
							if(Interfaz.MatrizLogica[posy-1][posx]==4){
								SonidoAbduccion();
								if(Interfaz.MatrizLogica[posy][posx]==1){
									Grafico[posy][posx].setIcon(objOp.getimagen(1));
								}else{
									Grafico[posy][posx].setIcon(objOp.getimagen(0));
								}
								datos.setVacas(datos.getVacas()-1);
								Grafico[posy-1][posx].setIcon(objOp.getimagen(8));
							}else{
								if(Interfaz.MatrizLogica[posy-1][posx]==3){
									choque=false;
									if(Interfaz.MatrizLogica[posy][posx]==1){
										Grafico[posy][posx].setIcon(objOp.getimagen(1));
									}else{
										Grafico[posy][posx].setIcon(objOp.getimagen(0));
									}
									Grafico[posy-1][posx].setIcon(objOp.getimagen(51));
								}else{
									if(Interfaz.MatrizLogica[posy-1][posx]==2){
										choque=false;
										if(Interfaz.MatrizLogica[posy][posx]==1){
											Grafico[posy][posx].setIcon(objOp.getimagen(1));
										}else{
											Grafico[posy][posx].setIcon(objOp.getimagen(0));
										}
										Grafico[posy-1][posx].setIcon(objOp.getimagen(51));
									}else{
										if(Interfaz.MatrizLogica[posy-1][posx]==1){
											Grafico[posy][posx].setIcon(objOp.getimagen(0));
											Grafico[posy-1][posx].setIcon(objOp.getimagen(9));
										}else{
											if(Interfaz.MatrizLogica[posy][posx]==3){
												Grafico[posy][posx].setIcon(objOp.getimagen(7));
											}else{
												if(Interfaz.MatrizLogica[posy][posx]==2){
													Grafico[posy][posx].setIcon(objOp.getimagen(6));
												}else{
													if(Interfaz.MatrizLogica[posy][posx]==1){
														Grafico[posy][posx].setIcon(objOp.getimagen(1));
													}else{
														if(Interfaz.MatrizLogica[posy][posx]==4){
															//System.out.println(datos.getVacas());
															//System.out.print(objOp.endLevel());
															if(objOp.endLevel()){
																choque=false;
																break;
															}
															try {
																Thread.sleep(1);
																sonido1.stop();
															} catch (InterruptedException e) {
																// TODO Auto-generated catch block
																e.printStackTrace();
															}
															Grafico[posy][posx].setIcon(objOp.getimagen(0));
														}else{
															Grafico[posy][posx].setIcon(objOp.getimagen(0));
														}
													}
													Grafico[posy-1][posx].setIcon(objOp.getimagen(53));
													}
												}
											}
										}
									}
								}
							}
						}
					posy=posy-1;
					if(Interfaz.MatrizLogica[posy][posx]==3){
						SonidoAntena();
						Grafico[posy][posx].setIcon(objOp.getimagen(7));
					}else{
						if(Interfaz.MatrizLogica[posy][posx]==2){
							SonidoArbol();
							Grafico[posy][posx].setIcon(objOp.getimagen(6));
							}
						}
				break;
				case 54: 
					if(Interfaz.MatrizLogica[posy][posx]==4){
						try {
							Thread.sleep(900);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							}
						}
					if(Interfaz.MatrizLogica[posy][posx]==3||Interfaz.MatrizLogica[posy][posx]==2){
						break;
					}else{
						if((posx-1)<0||(posy-1)<0){
							SonidoLimites();
							//System.out.print(posx+"+"+posy);
							choque=false;
							if(Interfaz.MatrizLogica[posy][posx]==0){
								Grafico[posy][posx].setIcon(objOp.getimagen(0));
								JOptionPane.showMessageDialog(null,"Por accidente abriste un portal a otro planeta","No te encuentras en la tierra ",JOptionPane.WARNING_MESSAGE, iconFueraPlaneta);
							}else{
								if(Interfaz.MatrizLogica[posy][posx]==3)
								Grafico[posy][posx].setIcon(objOp.getimagen(1));
								if(Interfaz.MatrizLogica[posy][posx]==2)
									Grafico[posy][posx].setIcon(objOp.getimagen(2));
								else
									Grafico[posy][posx].setIcon(objOp.getimagen(0));
								}
							break;
						}else{
							if(Interfaz.MatrizLogica[posy-1][posx-1]==4){
								SonidoAbduccion();
								if(Interfaz.MatrizLogica[posy][posx]==1){
									Grafico[posy][posx].setIcon(objOp.getimagen(1));
								}else{
									Grafico[posy][posx].setIcon(objOp.getimagen(0));
								}
								datos.setVacas(datos.getVacas()-1);
								Grafico[posy-1][posx-1].setIcon(objOp.getimagen(8));
							}else{
								if(Interfaz.MatrizLogica[posy-1][posx-1]==3){
									choque=false;
									if(Interfaz.MatrizLogica[posy][posx]==1){
										Grafico[posy][posx].setIcon(objOp.getimagen(1));
									}else{
										Grafico[posy][posx].setIcon(objOp.getimagen(0));
									}
									Grafico[posy-1][posx-1].setIcon(objOp.getimagen(54));
								}else{
									if(Interfaz.MatrizLogica[posy-1][posx-1]==2){
										choque=false;
									if(Interfaz.MatrizLogica[posy][posx]==1){
										Grafico[posy][posx].setIcon(objOp.getimagen(1));
									}else{
										Grafico[posy][posx].setIcon(objOp.getimagen(0));
									}
									Grafico[posy-1][posx-1].setIcon(objOp.getimagen(9));
									}else{
										if(Interfaz.MatrizLogica[posy-1][posx-1]==1){
											Grafico[posy][posx].setIcon(objOp.getimagen(0));
											Grafico[posy-1][posx-1].setIcon(objOp.getimagen(1));
										}else{
											if(Interfaz.MatrizLogica[posy][posx]==3){
											Grafico[posy][posx].setIcon(objOp.getimagen(7));
										}else{
											if(Interfaz.MatrizLogica[posy][posx]==2){
												Grafico[posy][posx].setIcon(objOp.getimagen(6));
											}else{
												if(Interfaz.MatrizLogica[posy][posx]==1){
													Grafico[posy][posx].setIcon(objOp.getimagen(1));
												}else{
													if(Interfaz.MatrizLogica[posy][posx]==4){
														//System.out.println(datos.getVacas());
														//System.out.print(objOp.endLevel());
														if(objOp.endLevel()){
															choque=false;
															break;
														}
														try {
															Thread.sleep(1);
															sonido1.stop();
														} catch (InterruptedException e) {
															// TODO Auto-generated catch block
															e.printStackTrace();
														}
														Grafico[posy][posx].setIcon(objOp.getimagen(0));
													}else{
														Grafico[posy][posx].setIcon(objOp.getimagen(0));
													}
												}
												Grafico[posy-1][posx-1].setIcon(objOp.getimagen(54));
											
												}
											}
										}
									}
								}
							}
						}
					}
					posx=posx-1;
					posy=posy-1;
					if(Interfaz.MatrizLogica[posy][posx]==3){
						SonidoAntena();
		 				Grafico[posy][posx].setIcon(objOp.getimagen(7));
				
		 			}else{
			
		 				if(Interfaz.MatrizLogica[posy][posx]==2){
		 					SonidoArbol();
		 					Grafico[posy][posx].setIcon(objOp.getimagen(6));
				
		 			}}	
				break;
				case 55:
					if(Interfaz.MatrizLogica[posy][posx]==4){
						try {
							Thread.sleep(900);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							}
						}
					if(Interfaz.MatrizLogica[posy][posx]==3||Interfaz.MatrizLogica[posy][posx]==2){
						break;
					}else{
						if((posx-1)<0){
							SonidoLimites();
							//System.out.print(posx+"+"+posy);
							choque=false;
							if(Interfaz.MatrizLogica[posy][posx]==0){
								Grafico[posy][posx].setIcon(objOp.getimagen(0));
								JOptionPane.showMessageDialog(null,"Por accidente abriste un portal a otro planeta","No te encuentras en la tierra ",JOptionPane.WARNING_MESSAGE, iconFueraPlaneta);
							}else{
								if(Interfaz.MatrizLogica[posy][posx]==3)
									Grafico[posy][posx].setIcon(objOp.getimagen(1));
								if(Interfaz.MatrizLogica[posy][posx]==2)
									Grafico[posy][posx].setIcon(objOp.getimagen(2));
								else
									Grafico[posy][posx].setIcon(objOp.getimagen(0));
						}break;
						}else{
							if(Interfaz.MatrizLogica[posy][posx-1]==4){
								SonidoAbduccion();
								if(Interfaz.MatrizLogica[posy][posx]==1){
									Grafico[posy][posx].setIcon(objOp.getimagen(1));
								}else{
									Grafico[posy][posx].setIcon(objOp.getimagen(0));
								}
								datos.setVacas(datos.getVacas()-1);
								Grafico[posy][posx-1].setIcon(objOp.getimagen(8));
							}else{
								if(Interfaz.MatrizLogica[posy][posx-1]==3){
									choque=false;
									if(Interfaz.MatrizLogica[posy][posx]==1){
										Grafico[posy][posx].setIcon(objOp.getimagen(1));
									}else{
										Grafico[posy][posx].setIcon(objOp.getimagen(0));
									}
									Grafico[posy][posx-1].setIcon(objOp.getimagen(55));
								}else{
									if(Interfaz.MatrizLogica[posy][posx-1]==2){
										choque=false;
										if(Interfaz.MatrizLogica[posy][posx]==1){
											Grafico[posy][posx].setIcon(objOp.getimagen(1));
										}else{
											Grafico[posy][posx].setIcon(objOp.getimagen(0));
										}
										Grafico[posy][posx-1].setIcon(objOp.getimagen(55));
									}else{
										if(Interfaz.MatrizLogica[posy][posx-1]==1){
											Grafico[posy][posx].setIcon(objOp.getimagen(0));
											Grafico[posy][posx-1].setIcon(objOp.getimagen(9));
										}else{
											if(Interfaz.MatrizLogica[posy][posx]==3){
												Grafico[posy][posx].setIcon(objOp.getimagen(7));
											}else{
												if(Interfaz.MatrizLogica[posy][posx]==2){
													Grafico[posy][posx].setIcon(objOp.getimagen(6));
												}else{
													if(Interfaz.MatrizLogica[posy][posx]==1){
														Grafico[posy][posx].setIcon(objOp.getimagen(1));
													}else{
														if(Interfaz.MatrizLogica[posy][posx]==4){
															//System.out.println(datos.getVacas());
															//System.out.print(objOp.endLevel());
															if(objOp.endLevel()){
																choque=false;
																break;
															}
															try {
																Thread.sleep(1);
																sonido1.stop();
															} catch (InterruptedException e) {
																// TODO Auto-generated catch block
																e.printStackTrace();
															}
															Grafico[posy][posx].setIcon(objOp.getimagen(0));
														}else{
															Grafico[posy][posx].setIcon(objOp.getimagen(0));
														}
													}
													Grafico[posy][posx-1].setIcon(objOp.getimagen(55));
												
													}
												}
											}
										}
									}
								}
							}
						}
					posx=posx-1;
					if(Interfaz.MatrizLogica[posy][posx]==3){
						SonidoAntena();
						Grafico[posy][posx].setIcon(objOp.getimagen(7));
					}else{
						if(Interfaz.MatrizLogica[posy][posx]==2){
							SonidoArbol();
							Grafico[posy][posx].setIcon(objOp.getimagen(6));
							}
						}
				break;
				case 56:
					if(Interfaz.MatrizLogica[posy][posx]==4){
						try {
							Thread.sleep(900);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							}
						}
					if(Interfaz.MatrizLogica[posy][posx]==3||Interfaz.MatrizLogica[posy][posx]==2){
						break;
					}else{
						if((posy+1)>9||(posx-1)<0){
							SonidoLimites();
							//System.out.print(posx+"+"+posy);
							choque=false;
							if(Interfaz.MatrizLogica[posy][posx]==0){
								Grafico[posy][posx].setIcon(objOp.getimagen(0));
								JOptionPane.showMessageDialog(null,"Por accidente abriste un portal a otro planeta","No te encuentras en la tierra ",JOptionPane.WARNING_MESSAGE, iconFueraPlaneta);
							}else{
								if(Interfaz.MatrizLogica[posy][posx]==3)
									Grafico[posy][posx].setIcon(objOp.getimagen(1));
								if(Interfaz.MatrizLogica[posy][posx]==2)
									Grafico[posy][posx].setIcon(objOp.getimagen(2));
								else
									Grafico[posy][posx].setIcon(objOp.getimagen(0));
						}break;
						}else{
							if(Interfaz.MatrizLogica[posy+1][posx-1]==4){
								SonidoAbduccion();
								if(Interfaz.MatrizLogica[posy][posx]==1){
									Grafico[posy][posx].setIcon(objOp.getimagen(1));
								}else{
									Grafico[posy][posx].setIcon(objOp.getimagen(0));
								}
								datos.setVacas(datos.getVacas()-1);
								Grafico[posy+1][posx-1].setIcon(objOp.getimagen(8));
							}else{
								if(Interfaz.MatrizLogica[posy+1][posx-1]==3){
									choque=false;
									if(Interfaz.MatrizLogica[posy][posx]==1){
										Grafico[posy][posx].setIcon(objOp.getimagen(1));
									}else{
										Grafico[posy][posx].setIcon(objOp.getimagen(0));
									}
									Grafico[posy+1][posx-1].setIcon(objOp.getimagen(56));
								}else{
									if(Interfaz.MatrizLogica[posy+1][posx-1]==2){
										choque=false;
										if(Interfaz.MatrizLogica[posy][posx]==1){
											Grafico[posy][posx].setIcon(objOp.getimagen(1));
										}else{
											Grafico[posy][posx].setIcon(objOp.getimagen(0));
										}
										Grafico[posy+1][posx-1].setIcon(objOp.getimagen(56));
									}else{
										if(Interfaz.MatrizLogica[posy+1][posx-1]==1){
											Grafico[posy][posx].setIcon(objOp.getimagen(0));
											Grafico[posy+1][posx-1].setIcon(objOp.getimagen(9));
										}else{
											if(Interfaz.MatrizLogica[posy][posx]==3){
												Grafico[posy][posx].setIcon(objOp.getimagen(7));
											}else{
												if(Interfaz.MatrizLogica[posy][posx]==2){
													Grafico[posy][posx].setIcon(objOp.getimagen(6));
												}else{
													if(Interfaz.MatrizLogica[posy][posx]==1){
														Grafico[posy][posx].setIcon(objOp.getimagen(1));
													}else{
														if(Interfaz.MatrizLogica[posy][posx]==4){
															//System.out.println(datos.getVacas());
															//System.out.print(objOp.endLevel());
															if(objOp.endLevel()){
																choque=false;
																break;
															}
															try {
																Thread.sleep(1);
																sonido1.stop();
															} catch (InterruptedException e) {
																// TODO Auto-generated catch block
																e.printStackTrace();
															}
															Grafico[posy][posx].setIcon(objOp.getimagen(0));
														}else{
															Grafico[posy][posx].setIcon(objOp.getimagen(0));
														}
													}
													Grafico[posy+1][posx-1].setIcon(objOp.getimagen(56));
													}
												}
											}
										}
									}
								}
							}
						}
					posy=posy+1;
					posx=posx-1;
					if(Interfaz.MatrizLogica[posy][posx]==3){
						SonidoAntena();
						Grafico[posy][posx].setIcon(objOp.getimagen(7));
					}else{
						if(Interfaz.MatrizLogica[posy][posx]==2){
							SonidoArbol();
							Grafico[posy][posx].setIcon(objOp.getimagen(6));
							}
						}
				break;
				case 57:
					if(Interfaz.MatrizLogica[posy][posx]==4){
						try {
							Thread.sleep(900);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							}
						}
					if(Interfaz.MatrizLogica[posy][posx]==3||Interfaz.MatrizLogica[posy][posx]==2){
						break;
					}else{
						if((posy+1)>9){
							SonidoLimites();
							//System.out.print(posx+"+"+posy);
							choque=false;
							if(Interfaz.MatrizLogica[posy][posx]==0){
								Grafico[posy][posx].setIcon(objOp.getimagen(0));
								JOptionPane.showMessageDialog(null,"Por accidente abriste un portal a otro planeta","No te encuentras en la tierra ",JOptionPane.WARNING_MESSAGE, iconFueraPlaneta);
							}else{
								if(Interfaz.MatrizLogica[posy][posx]==3)
									Grafico[posy][posx].setIcon(objOp.getimagen(1));
								if(Interfaz.MatrizLogica[posy][posx]==2)
									Grafico[posy][posx].setIcon(objOp.getimagen(2));
								else
									Grafico[posy][posx].setIcon(objOp.getimagen(0));
						}break;
						}else{
							if(Interfaz.MatrizLogica[posy+1][posx]==4){
								SonidoAbduccion();
								if(Interfaz.MatrizLogica[posy][posx]==1){
									Grafico[posy][posx].setIcon(objOp.getimagen(1));
								}else{
									Grafico[posy][posx].setIcon(objOp.getimagen(0));
								}
								datos.setVacas(datos.getVacas()-1);
								Grafico[posy+1][posx].setIcon(objOp.getimagen(8));
							}else{
								if(Interfaz.MatrizLogica[posy+1][posx]==3){
									choque=false;
									if(Interfaz.MatrizLogica[posy][posx]==1){
										Grafico[posy][posx].setIcon(objOp.getimagen(1));
									}else{
										Grafico[posy][posx].setIcon(objOp.getimagen(0));
									}
									Grafico[posy+1][posx].setIcon(objOp.getimagen(57));
								}else{
									if(Interfaz.MatrizLogica[posy+1][posx]==2){
										choque=false;
										if(Interfaz.MatrizLogica[posy][posx]==1){
											Grafico[posy][posx].setIcon(objOp.getimagen(1));
										}else{
											Grafico[posy][posx].setIcon(objOp.getimagen(0));
										}
										Grafico[posy+1][posx].setIcon(objOp.getimagen(57));
									}else{
										if(Interfaz.MatrizLogica[posy+1][posx]==1){
											Grafico[posy][posx].setIcon(objOp.getimagen(0));
											Grafico[posy+1][posx].setIcon(objOp.getimagen(9));
										}else{
											if(Interfaz.MatrizLogica[posy][posx]==3){
												Grafico[posy][posx].setIcon(objOp.getimagen(7));
											}else{
												if(Interfaz.MatrizLogica[posy][posx]==2){
													Grafico[posy][posx].setIcon(objOp.getimagen(6));
												}else{
													if(Interfaz.MatrizLogica[posy][posx]==1){
														Grafico[posy][posx].setIcon(objOp.getimagen(1));
													}else{
														if(Interfaz.MatrizLogica[posy][posx]==4){
															//System.out.println(datos.getVacas());
															//System.out.print(objOp.endLevel());
															if(objOp.endLevel()){
																choque=false;
																break;
															}
															try {
																Thread.sleep(1);
																sonido1.stop();
															} catch (InterruptedException e) {
																// TODO Auto-generated catch block
																e.printStackTrace();
															}
															Grafico[posy][posx].setIcon(objOp.getimagen(0));
														}else{
															Grafico[posy][posx].setIcon(objOp.getimagen(0));
														}
													}
													Grafico[posy+1][posx].setIcon(objOp.getimagen(57));
													}
												}
											}
										}
									}
								}
							}
						}
					posy=posy+1;
					if(Interfaz.MatrizLogica[posy][posx]==3){
						SonidoAntena();
						Grafico[posy][posx].setIcon(objOp.getimagen(7));
					}else{
						if(Interfaz.MatrizLogica[posy][posx]==2){
							SonidoArbol();
							Grafico[posy][posx].setIcon(objOp.getimagen(6));
							}
						}
				break;
				case 58: 
					if(Interfaz.MatrizLogica[posy][posx]==4){
						try {
							Thread.sleep(900);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							}
						}
					if(Interfaz.MatrizLogica[posy][posx]==3||Interfaz.MatrizLogica[posy][posx]==2){
						break;
					}else{
						if((posx+1)>9){
							SonidoLimites();
							//System.out.print(posx+"+"+posy);
							choque=false;
							if(Interfaz.MatrizLogica[posy][posx]==0){
								Grafico[posy][posx].setIcon(objOp.getimagen(0));
								JOptionPane.showMessageDialog(null,"Por accidente abriste un portal a otro planeta","No te encuentras en la tierra ",JOptionPane.WARNING_MESSAGE, iconFueraPlaneta);
							}else{
								if(Interfaz.MatrizLogica[posy][posx]==3)
									Grafico[posy][posx].setIcon(objOp.getimagen(1));
								if(Interfaz.MatrizLogica[posy][posx]==2)
									Grafico[posy][posx].setIcon(objOp.getimagen(2));
								else
									Grafico[posy][posx].setIcon(objOp.getimagen(0));
						}break;
						}else{
							if(Interfaz.MatrizLogica[posy+1][posx+1]==4){
								SonidoAbduccion();
								if(Interfaz.MatrizLogica[posy][posx]==1){
									Grafico[posy][posx].setIcon(objOp.getimagen(1));
								}else{
									Grafico[posy][posx].setIcon(objOp.getimagen(0));
								}
								datos.setVacas(datos.getVacas()-1);
								Grafico[posy+1][posx+1].setIcon(objOp.getimagen(8));
							}else{
								if(Interfaz.MatrizLogica[posy+1][posx+1]==3){
									choque=false;
									if(Interfaz.MatrizLogica[posy][posx]==1){
										Grafico[posy][posx].setIcon(objOp.getimagen(1));
									}else{
										Grafico[posy][posx].setIcon(objOp.getimagen(0));
									}
									Grafico[posy+1][posx+1].setIcon(objOp.getimagen(58));
								}else{
									if(Interfaz.MatrizLogica[posy+1][posx+1]==2){
										choque=false;
										if(Interfaz.MatrizLogica[posy][posx]==1){
											Grafico[posy][posx].setIcon(objOp.getimagen(1));
										}else{
											Grafico[posy][posx].setIcon(objOp.getimagen(0));
										}
										Grafico[posy+1][posx+1].setIcon(objOp.getimagen(58));
									}else{
										if(Interfaz.MatrizLogica[posy+1][posx+1]==1){
											Grafico[posy][posx].setIcon(objOp.getimagen(0));
											Grafico[posy+1][posx+1].setIcon(objOp.getimagen(9));
										}else{
											if(Interfaz.MatrizLogica[posy][posx]==3){
												Grafico[posy][posx].setIcon(objOp.getimagen(7));
											}else{
												if(Interfaz.MatrizLogica[posy][posx]==2){
													Grafico[posy][posx].setIcon(objOp.getimagen(6));
												}else{
													if(Interfaz.MatrizLogica[posy][posx]==1){
														Grafico[posy][posx].setIcon(objOp.getimagen(1));
													}else{
														if(Interfaz.MatrizLogica[posy][posx]==4){
															//System.out.println(datos.getVacas());
															//System.out.print(objOp.endLevel());
															if(objOp.endLevel()){
																choque=false;
																break;
															}
															try {
																Thread.sleep(1);
																sonido1.stop();
															} catch (InterruptedException e) {
																// TODO Auto-generated catch block
																e.printStackTrace();
															}
															Grafico[posy][posx].setIcon(objOp.getimagen(0));
														}else{
															Grafico[posy][posx].setIcon(objOp.getimagen(0));
														}
													}
													Grafico[posy+1][posx+1].setIcon(objOp.getimagen(58));
													}
												}
											}
										}
									}
								}
							}
						}
					posx=posx+1;
					posy=posy+1;
					if(Interfaz.MatrizLogica[posy][posx]==3){
						SonidoAntena();
						Grafico[posy][posx].setIcon(objOp.getimagen(7));
					}else{
						if(Interfaz.MatrizLogica[posy][posx]==2){
							SonidoArbol();
							Grafico[posy][posx].setIcon(objOp.getimagen(6));
							}
						}
				break;
				default:
				//System.out.println("Entro a default");
				break;
			}
		}
}
