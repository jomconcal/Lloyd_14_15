package acciones;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import graficos.Tablero;

/**
 * Acción para los movimientos de las casillas. Si se colocan en la posición de
 * victoria se retira la capacidad de mover.
 * 
 * @author jomco
 * @since 26/05/2024
 * @version 2
 */
public class AccionTeclado extends KeyAdapter {

	private Tablero tablero;

	public AccionTeclado(Tablero tablero) {
		this.tablero = tablero;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			try {
				tablero.moverFicha(0);
				tablero.colocarNuevoTablero();
			} catch (ArrayIndexOutOfBoundsException ex) {

			}
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			try {
				tablero.moverFicha(1);
				tablero.colocarNuevoTablero();
			} catch (ArrayIndexOutOfBoundsException ex) {

			}
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			try {
				tablero.moverFicha(3);
				tablero.colocarNuevoTablero();
			} catch (ArrayIndexOutOfBoundsException ex) {

			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			try {
				tablero.moverFicha(2);
				tablero.colocarNuevoTablero();
			} catch (ArrayIndexOutOfBoundsException ex) {

			}
		}

		if (tablero.victoria()) {
			tablero.removeKeyListener(this);
			tablero.finalizar();
		}
	}

}
