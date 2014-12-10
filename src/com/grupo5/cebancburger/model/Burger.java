package com.grupo5.cebancburger.model;

public class Burger {

	//Las variables tamano, tipo_carne y tipo_burger guarda la posición del array de values/String
	private int cantidad, tamano, tipo_carne, tipo_burger;
	private double precio;

	public Burger(int tamano, int tipo_carne,int tipo_burger, int cantidad){
		this.tamano = tamano;
		this.tipo_carne = tipo_carne;
		this.tipo_burger = tipo_burger;
		this.cantidad = cantidad;
	}

	public void setTamano(int tamano){
		this.tamano = tamano;
	}
	public int getTamano(){
		return tamano;
	}

	public void setTipoCarne(int tipo_carne){
		this.tipo_carne = tipo_carne;
	}
	public int getTipoCarne(){
		return tipo_carne;
	}

	public void setTipoBurger(int tipo_burger){
		this.tipo_burger = tipo_burger;
	}
	public int getTipoBurger(){
		return tipo_burger;
	}

	public void setCantidad(int cantidad){
		this.cantidad = cantidad;
	}
	public int getCantidad(){
		return cantidad;
	}

	public void setPrecio(){
		
		precio = 0;

		//Decidir precios a sumar por cada tipo
		switch(tamano){

		case 0: break;
		case 1: break;

		}

		switch(tipo_carne){

		case 0: break;
		case 1: break;
		case 3: break;

		}

		switch(tipo_burger){

		case 0: break;
		case 1: break;
		case 2: break;
		case 3: break;
		case 4: break;

		}
		
	}
	public double getPrecio(){
		return precio;
	}

}
