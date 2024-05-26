package disenyo_logico;

public class TableroLogico {

	private CasillaLogica[][] tablero;
	private int indiceDesorden;

	public TableroLogico() {
		this.tablero = new CasillaLogica[4][4];
		rellenaTablero();
		this.indiceDesorden = 0;
	}

	public CasillaLogica[][] getTablero() {
		return tablero;
	}

	public int getIndiceDesorden() {
		return indiceDesorden;
	}

	protected void rellenaTablero() {
		int num = 1;
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				tablero[i][j] = new CasillaLogica(num++);
			}
		}
	}

	public void desordenarTablero() {

		for (int i = 0; i < 100; i++) {
			int numero = (int) (Math.random() * 4);
			try {
				if (numero == 0)
					moverFicha('w');
				else if (numero == 1)
					moverFicha('s');
				else if (numero == 2)
					moverFicha('a');
				else if (numero == 3)
					moverFicha('d');
			} catch (ArrayIndexOutOfBoundsException e) {

			}
		}
	}

	private void setIndiceDesorden() {
		int indice = 0;
		CasillaLogica[] orden = new CasillaLogica[16];
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				orden[i * 4 + j] = tablero[i][j];
			}
		}

		for (int i = 0; i < orden.length - 1; i++) {
			for (int j = i + 1; j < orden.length; j++) {
				if (orden[i].getNumero() > orden[j].getNumero() && orden[i].getNumero() != 0
						&& orden[j].getNumero() != 0) {
					// System.out.print(orden[i] + "" + orden[j] + "|, ");
					indice++;
				}
			}
		}
		this.indiceDesorden = indice;
	}

	public void mostrarTablero() {
		System.out.println(" -- -- -- --");
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				System.out.print(tablero[i][j]);
			}
			System.out.println("|\n -- -- -- --");
		}
	}

	public void moverFicha(char direccion) throws ArrayIndexOutOfBoundsException {
		CasillaLogica cero = new CasillaLogica(0);
		int x = 0;
		int y = 0;
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				if (tablero[i][j].equals(cero)) {
					x = j;
					y = i;
				}
			}
		}

		if (direccion == 'w') {
			CasillaLogica aux = tablero[y][x];
			tablero[y][x] = tablero[y + 1][x];
			tablero[y + 1][x] = aux;
		} else if (direccion == 's') {
			CasillaLogica aux = tablero[y][x];
			tablero[y][x] = tablero[y - 1][x];
			tablero[y - 1][x] = aux;
		} else if (direccion == 'a') {
			CasillaLogica aux = tablero[y][x];
			tablero[y][x] = tablero[y][x + 1];
			tablero[y][x + 1] = aux;
		} else if (direccion == 'd') {
			CasillaLogica aux = tablero[y][x];
			tablero[y][x] = tablero[y][x - 1];
			tablero[y][x - 1] = aux;
		}
		setIndiceDesorden();
	}
}
