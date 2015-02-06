package com.grupo5.cebancburger.model;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("serial")
public class Burger implements Serializable {

	private String tamano, tipo_carne, tipo_burger;
	private int cantidad;
	private double precio;

	public Burger(String tamano, String tipo_carne, String tipo_burger,
			int cantidad) {
		this.tamano = tamano;
		this.tipo_carne = tipo_carne;
		this.tipo_burger = tipo_burger;
		this.cantidad = cantidad;
		setPrecio();
	}

	public void setTamano(String tamano) {
		this.tamano = tamano;
	}

	public String getTamano() {
		return tamano;
	}

	public void setTipoCarne(String tipo_carne) {
		this.tipo_carne = tipo_carne;
	}

	public String getTipoCarne() {
		return tipo_carne;
	}

	public void setTipoBurger(String tipo_burger) {
		this.tipo_burger = tipo_burger;
	}

	public String getTipoBurger() {
		return tipo_burger;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setPrecio() {
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
