package com.grupo5.cebancburger.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;
	private Cliente cliente = null;
	private ArrayList<Burger> alBurger = null;
	private ArrayList<Bebida> alBebida = null;
	private String regalo = "";
	private double precio;

	public Pedido() {

		alBurger = new ArrayList<Burger>();
		alBebida = new ArrayList<Bebida>();

	}

	public void setCliente(Cliente cliente) {

		this.cliente = cliente;

	}

	public Cliente getCliente() {

		return cliente;

	}

	public void setBurger(Burger burger) {

		alBurger.add(burger);

	}

	public ArrayList<Burger> getBurger() {

		return alBurger;

	}

	public void setBebida(Bebida bebida) {

		alBebida.add(bebida);

	}

	public ArrayList<Bebida> getBebida() {

		return alBebida;

	}

	public String getRegalo() {

		if (getPrecio() > 15 && getPrecio() <= 25)
			regalo = "Peluche de android";
		else if (getPrecio() > 25)
			regalo = "Peluche de android y vale para comer en Cebanc";
		else
			regalo = "Ninguno";
		return regalo;

	}

	public double getPrecio() {
		double price = 0;
		for (int i = 0; i < alBurger.size(); i++) {
			price += alBurger.get(i).getPrecio();
		}

		for (int i = 0; i < alBebida.size(); i++) {
			price += alBebida.get(i).getPrecio();
		}

		return Double.valueOf(truncateDecimal(price, 2).toString());
	}

	public void setFinalPrice() {
		this.precio = getPrecio();
	}

	public double getFinalPrecio() {
		return this.precio;
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
