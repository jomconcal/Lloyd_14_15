package aplicacion;

import java.awt.EventQueue;

import graficos.Tablero;

public class Main {

	public static void main(String[] args) {

		EventQueue.invokeLater(() -> {

			Tablero tablero = new Tablero();
			tablero.desordenarTablero();
			tablero.ponerALaEscuha();
		});
	}
}
