package pruebas;

import java.util.Scanner;

import disenyo_logico.TableroLogico;

public class MainLogico {

	public static void main(String[] args) {

		TableroLogico tableroLogico = new TableroLogico();
		tableroLogico.desordenarTablero();
		tableroLogico.mostrarTablero();
		System.out.println(tableroLogico.getIndiceDesorden());
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("introduce una dirección (w,a,s,d)");
			String direccion = sc.nextLine();
			try {
				tableroLogico.moverFicha(direccion.charAt(0));
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Por favor, cíñase al tamaño del tablero");
			}
			tableroLogico.mostrarTablero();
			System.out.println(tableroLogico.getIndiceDesorden());

		} while (tableroLogico.getIndiceDesorden() != 0 || tableroLogico.getTablero()[3][3].getNumero() != 0);
		System.out.println("Has ganado.");
		sc.close();
	}

}
