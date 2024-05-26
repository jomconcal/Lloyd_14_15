package aplicacion;

import java.util.Scanner;

import graficos.Tablero;

public class MainLogico {

	public static void main(String[] args) {

		Tablero tablero = new Tablero();
		tablero.desordenarTableroLogico();
		tablero.mostrarTableroLogico();
		//System.out.println(tablero.getIndiceDesorden());
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("introduce una dirección (0,1,2,3)");
			int direccion = Integer.parseInt(sc.nextLine());
			try {
				tablero.moverFicha(direccion);
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Por favor, cíñase al tamaño del tablero");
			}
			tablero.mostrarTableroLogico();
			//System.out.println(tablero.getIndiceDesorden());

		} while (!tablero.victoria());
		System.out.println("Has ganado.");
		sc.close();
	}

}
