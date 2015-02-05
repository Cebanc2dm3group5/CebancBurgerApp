package com.grupo5.cebancburger.model;

import java.io.Serializable;
import java.math.BigDecimal;

import android.app.Activity;
import android.content.ContentValues;

import com.grupo5.cebancburger.interfaces.DDBBObject;

@SuppressWarnings("serial")
public class Burger implements Serializable, DDBBObject {

	private final String TAMANO_NORMAL = "Normal";
	private final String TAMANO_WHOPPER = "Whopper";

	private final String CARNE_BUEY = "Buey";
	private final String CARNE_POLLO = "Pollo";
	private final String CARNE_TERNERA = "Ternera";

	private final String TIPO_CLASICA = "Clásica";
	private final String TIPO_CLASICA_QUESO = "Clásica con queso";
	private final String TIPO_DOBLE_QUESO = "Doble con queso";
	private final String TIPO_VEGETAL = "Vegetal";
	private final String TIPO_ESPECIAL = "Especial";

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

		precio = 0;
		for (int i = 0; i < this.cantidad; i++) {
			if (tamano.equalsIgnoreCase(TAMANO_NORMAL))
				precio += 4.50;
			else if (tamano.equalsIgnoreCase(TAMANO_WHOPPER))
				precio += 5.00;

			if (tipo_carne.equalsIgnoreCase(CARNE_BUEY))
				precio += 1.50;
			else if (tipo_carne.equalsIgnoreCase(CARNE_POLLO))
				precio += 0.80;
			else if (tipo_carne.equalsIgnoreCase(CARNE_TERNERA))
				precio += 1.00;

			if (tipo_burger.equalsIgnoreCase(TIPO_CLASICA))
				precio += 1.00;
			else if (tipo_burger.equalsIgnoreCase(TIPO_CLASICA_QUESO))
				precio += 1.20;
			else if (tipo_burger.equalsIgnoreCase(TIPO_DOBLE_QUESO))
				precio += 2.50;
			else if (tipo_burger.equalsIgnoreCase(TIPO_VEGETAL))
				precio += 1.00;
			else if (tipo_burger.equalsIgnoreCase(TIPO_ESPECIAL))
				precio += 2.00;
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

	@Override
	public void save(Activity activity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Activity activity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ContentValues getContentValue(Activity activity) {
		// TODO Auto-generated method stub
		return null;
	}

}
