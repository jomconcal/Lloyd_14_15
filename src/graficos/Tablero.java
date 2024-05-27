package graficos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

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
	 * 
	 */
	private JPanel panelCentral;
	/**
	 * Ínidice que marca cómo de desordenado está el tablero.
	 */
	private int indiceDesorden;

	private AccionTeclado accionTeclado;

	/**
	 * Inicia el tablero y lo rellena con las casillas.
	 */
	public Tablero() {
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		this.setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Opciones");
		JMenuItem reiniciar = new JMenuItem("Reiniciar");
		reiniciar.addActionListener((e) -> {

			this.removeKeyListener(accionTeclado);
			rellenaTableroLogico();
			desordenarTablero();
			ponerALaEscuha();
		});
		menu.add(reiniciar);
		menuBar.add(menu);
		panelCentral = new JPanel();
		panelCentral.setLayout(new GridLayout(4, 4));
		this.tablero = new Casilla[4][4];
		this.add(menuBar, BorderLayout.NORTH);
		this.add(panelCentral, BorderLayout.CENTER);
		rellenaTableroLogico();

	}

	public void ponerALaEscuha() {
		accionTeclado = new AccionTeclado(this);
		this.setFocusable(true);
		this.requestFocus();
		addKeyListener(accionTeclado);
	}

	/**
	 * Crea las casillas lógicas y las añade a la matriz. Si ya exisiteran las
	 * elimina. Inicializa el índice de desorden a 0.
	 */
	private void rellenaTableroLogico() {
		panelCentral.removeAll();
		this.indiceDesorden = 0;
		int num = 1;
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				Casilla c = new Casilla(num++);
				tablero[i][j] = c;
				panelCentral.add(c);
			}
		}
		this.pack();
	}

	/**
	 * Desordena las casillas del tablero. Realiza 100 movimientos.
	 */
	public void desordenarTablero() {

		for (int i = 0; i < 70; i++) {
			int numero = (int) (Math.random() * 4);
			try {
				moverFicha(numero);
			} catch (ArrayIndexOutOfBoundsException e) {

			}
		}

		colocarNuevoTablero();
	}

	public void colocarNuevoTablero() {
		this.panelCentral.removeAll();
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				panelCentral.add(tablero[i][j]);
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

	/**
	 * Se llama cuando se acaba la partida. Cambia el colo de fondo.
	 */
	public void finalizar() {
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				tablero[i][j].setBackground(new Color(0, 78, 31));
				tablero[i][j].setColor(new Color(204, 225, 123));
				tablero[i][j].setBorder(new BevelBorder(0));
			}
		}
	}

}
