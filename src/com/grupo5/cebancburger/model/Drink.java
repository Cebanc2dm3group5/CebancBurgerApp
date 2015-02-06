package com.grupo5.cebancburger.model;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("serial")
public class Drink implements Serializable {

	private int tipo;
	private int cantidad;
	private double precio;

	public Drink(int tipo, int cantidad, double precio) {
		setTipo(tipo);
		setCantidad(cantidad);
		setPrecio(precio);
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getTipo() {
		return tipo;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setPrecio(double precio) {
		this.precio = precio * this.cantidad;
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
