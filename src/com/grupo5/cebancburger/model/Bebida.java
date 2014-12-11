package com.grupo5.cebancburger.model;

public class Bebida {
	
	private final String TIPO_COLA = "Cola";
	private final String TIPO_LIMON = "Lim�n";
	private final String TIPO_NARANJA = "Naranja";
	private final String TIPO_NESTEA = "Nestea";
	private final String TIPO_CERVEZA = "Cerveza";
	private final String TIPO_AGUA = "Agua";
	
	private String tipo;
	private int cantidad;
	private double precio;
	
	public Bebida(String tipo, int cantidad, double precio){
		this.tipo = tipo;
		this.cantidad = cantidad;
		this.precio = precio;	
	}
	
	public void setTipo(String tipo){
		this.tipo = tipo;
	}
	public String getTipo(){
		return tipo;
	}
	
	public void setCantidad(int cantidad){
		this.cantidad = cantidad;
	}
	public int getCantidad(){
		return cantidad;
	}

	public void setPrecio(double precio){
		this.precio = precio;
	}
	public double getPrecio(){
		return precio;
	}


}
