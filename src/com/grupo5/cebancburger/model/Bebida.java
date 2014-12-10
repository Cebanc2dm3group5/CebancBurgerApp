package com.grupo5.cebancburger.model;

public class Bebida {
	
	//La variable tipo guarda la posición del array de values/String
	private int cantidad, tipo;
	private double precio;
	
	public Bebida(int tipo, int cantidad, double precio){
		this.tipo = tipo;
		this.cantidad = cantidad;
		this.precio = precio;	
	}
	
	public void setTipo(int tipo){
		this.tipo = tipo;
	}
	public int getTipo(){
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
