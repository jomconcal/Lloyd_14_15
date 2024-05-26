package graficos;

import java.awt.GridLayout;

import javax.swing.JFrame;

import acciones.AccionTeclado;

/**
 * Tablero donde se colocan las casillas
 * 
 * @author jomco
 * @since 26/05/2024
 * @version 2
 */
public class Tablero extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Matriz que contiene las casillas.
	 */
	private Casilla[][] tablero;

	/**
	 * Ínidice que marca cómo de desordenado está el tablero.
	 */
	private int indiceDesorden;

	/**
	 * Inicia el tablero y lo rellena con las casillas.
	 */
	public Tablero() {
		this.setVisible(true);
		this.setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setLayout(new GridLayout(4,4));
		this.tablero = new Casilla[4][4];
		rellenaTableroLogico();
		this.indiceDesorden = 0;
	}
	
	public void ponerALaEscuha() {
		this.setFocusable(true);
		this.requestFocus();
		addKeyListener(new AccionTeclado(this));
	}

	/**
	 * Crea las casillas lógicas y las añade a la matriz.
	 */
	private void rellenaTableroLogico() {
		int num = 1;
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				Casilla c = new Casilla(num++);
				tablero[i][j] = c;
				add(c);
			}
		}
		this.pack();
	}

	/**
	 * Desordena las casillas del tablero. Realiza 100 movimientos.
	 */
	public void desordenarTableroLogico() {

		for (int i = 0; i < 30; i++) {
			int numero = (int) (Math.random() * 4);
			try {
				moverFicha(numero);
			} catch (ArrayIndexOutOfBoundsException e) {

			}
		}
		colocarNuevoTablero();
	}

	public void colocarNuevoTablero() {
		
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				add(tablero[i][j]);
			}
		}
		this.pack();
	}
	/**
	 * Calcula el ínidice de desorden respecto a una casilla y las que la suceden.
	 * Servirá para determinar si se ha acabado la partida cuando el índice sea 0
	 * junto con otra condición. Actualiza el actual índice de desorden.
	 */
	private void setIndiceDesorden() {
		int indice = 0;
		Casilla[] orden = new Casilla[16];
		// Rellena el array orden con las casillas de la matriz tablero.
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				orden[i * 4 + j] = tablero[i][j];
			}
		}

		/**
		 * Cuenta cuántas casillas detras de la actual deberían estar por delante de
		 * ella. La suma de todas esas casillas dará el índice de desorden. Ej
		 * (1,2,3,6,5,4) dará un índice de 3.
		 */
		for (int i = 0; i < orden.length - 1; i++) {
			for (int j = i + 1; j < orden.length; j++) {
				if (orden[i].getNumero() > orden[j].getNumero() && orden[i].getNumero() != 0
						&& orden[j].getNumero() != 0) {
					indice++;
				}
			}
		}
		this.indiceDesorden = indice;
	}

	/**
	 * Representación en cosola del tablero lógico
	 */
	public void mostrarTableroLogico() {
		System.out.println(" -- -- -- --");
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				System.out.print(tablero[i][j]);
			}
			System.out.println("|\n -- -- -- --");
		}
	}

	/**
	 * Mueve la casilla con el valor 0 en la dirección indicada. Reestablece el
	 * índice de desorden.
	 * 
	 * @param direccion 0,1,2,3
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public void moverFicha(int direccion) throws ArrayIndexOutOfBoundsException {
		Casilla cero = new Casilla(0);
		int x = 0;
		int y = 0;
		// Encuentra dónde está la casilla 0.
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				if (tablero[i][j].equals(cero)) {
					x = j;
					y = i;
				}
			}
		}

		// mueve la casilla 0 hacia abajo
		if (direccion == 0) {
			Casilla aux = tablero[y][x];
			tablero[y][x] = tablero[y + 1][x];
			tablero[y + 1][x] = aux;

		} else if (direccion == 1) { // mueve la casilla 0 hacia arriba
			Casilla aux = tablero[y][x];
			tablero[y][x] = tablero[y - 1][x];
			tablero[y - 1][x] = aux;
		} else if (direccion == 2) {// mueve la casilla 0 hacia la derecha
			Casilla aux = tablero[y][x];
			tablero[y][x] = tablero[y][x + 1];
			tablero[y][x + 1] = aux;
		} else if (direccion == 3) {// mueve la casilla 0 hacia la izquierda
			Casilla aux = tablero[y][x];
			tablero[y][x] = tablero[y][x - 1];
			tablero[y][x - 1] = aux;
		}
		setIndiceDesorden();
	}

	/**
	 * Finaliza la partida cuando el índice de desorden sea 0 y la última casilla
	 * contenga el número 0.
	 * 
	 * @return victoria
	 */
	public boolean victoria() {
		return this.indiceDesorden == 0 && tablero[3][3].getNumero() == 0;
	}

	
}
