package com.grupo5.cebancburger.model;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("serial")
public class Bebida implements Serializable {

	private final String TIPO_COLA = "Cola";
	private final String TIPO_LIMON = "Limón";
	private final String TIPO_NARANJA = "Naranja";
	private final String TIPO_NESTEA = "Nestea";
	private final String TIPO_CERVEZA = "Cerveza";
	private final String TIPO_AGUA = "Agua";

	private String tipo;
	private int cantidad;
	private double precio;

	public Bebida(String tipo, int cantidad) {
		this.tipo = tipo;
		this.cantidad = cantidad;
		setPrecio();
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setPrecio() {
		precio = 0;
		for (int i = 0; i < this.cantidad; i++) {
			if (tipo.equalsIgnoreCase(TIPO_COLA)
					|| tipo.equalsIgnoreCase(TIPO_LIMON)
					|| tipo.equalsIgnoreCase(TIPO_NARANJA)
					|| tipo.equalsIgnoreCase(TIPO_NESTEA)
					|| tipo.equalsIgnoreCase(TIPO_CERVEZA))
				precio += 1.50;
			else if (tipo.equalsIgnoreCase(TIPO_AGUA))
				precio += 1.00;
		}
	}

	public double getPrecio() {
		return Double.valueOf(truncateDecimal(this.precio, 2).toString());
	}

	private static BigDecimal truncateDecimal(double x, int numberofDecimals) {
		if (x > 0) {
			return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals,
					BigDecimal.ROUND_FLOOR);
		} else {
			return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals,
					BigDecimal.ROUND_CEILING);
		}
	}

}
