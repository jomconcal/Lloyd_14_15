package disenyo_logico;


public class CasillaLogica{

	private int numero;

	public CasillaLogica(int numero) {
		if (numero != 16) {
			this.numero = numero;
		}else {
			this.numero=0;
		}
	}

	public int getNumero() {
		return numero;
	}

	@Override
	public String toString() {
		if (numero ==0) {
			return "|  ";
		}
		else if (numero < 10)
			return "| "+numero;
		else 
			return "|"+numero;
	}

	@Override
	public boolean equals(Object obj) {
		
		if(this.numero==((CasillaLogica)obj).numero)
			return true;
		return false;
	}
	
	
}
