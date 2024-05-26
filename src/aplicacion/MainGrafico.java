package aplicacion;

import java.awt.EventQueue;

import graficos.Tablero;

public class MainGrafico {

	public static void main(String[] args) {

		EventQueue.invokeLater(() -> {

			Tablero tablero = new Tablero();
			tablero.desordenarTableroLogico();
			tablero.ponerALaEscuha();
		});
	}
}
