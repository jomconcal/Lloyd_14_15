package graficos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 * Casilla con un número para desplazar en el Tablero
 * 
 * @author jomco
 * @since 26/05/2024
 * @version 2
 */
public class Casilla extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Número que identifica a la casilla. Único para cada una
	 */
	private int numero;

	private JLabel lblNumero;
	
	/**
	 * Inicia la casilla y le asigna un número (1-15). Si este fuera igual al máximo
	 * (16) se identifica como 0. La única casilla que podrá moverse será la marcada
	 * con el valor 0. Se mostrará al usuario en un JLabel.
	 * 
	 * @param numero
	 */
	public Casilla(int numero) {
		setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(80, 80));
		this.setBorder(new BevelBorder(1));
		if (numero != 16) {
			this.numero = numero;
		} else {
			this.numero = 0;
		}

		setLabel();
	}

	/**
	 * Establece el fondo de la casilla. Coloca el JLabel en el centro de la
	 * casilla.
	 */
	private void setLabel() {
		
		if (this.numero != 0) {
			lblNumero = new JLabel(String.valueOf(this.numero), JLabel.CENTER);
			this.setBackground(new Color(153, 153, 153));

		} else {
			lblNumero = new JLabel();
			this.setBackground(new Color(111, 111, 111));

		}
		lblNumero.setFont(new Font("Arial", Font.BOLD, 24));
		lblNumero.setForeground(new Color(92, 28, 32));
		add(lblNumero, BorderLayout.CENTER);
	}

	/**
	 * Devuelve el número almacenado en la etiqueta de la casilla
	 * 
	 * @return numero
	 */
	public int getNumero() {
		return this.numero;
	}

	@Override
	public String toString() {
		if (this.numero == 0) {
			return "|  ";
		} else if (this.numero < 10)
			return "| " + this.numero;
		else
			return "|" + this.numero;
	}

	@Override
	public boolean equals(Object obj) {

		if (this.numero == ((Casilla) obj).numero)
			return true;
		return false;
	}

	public void setColor(Color color) {
		this.lblNumero.setForeground(color);
	}
}
