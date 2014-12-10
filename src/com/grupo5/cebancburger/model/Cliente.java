package com.grupo5.cebancburger.model;

public class Cliente {

	private String nombre, direccion, telefono;

	public Cliente(String nombre, String direccion, String telefono){
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	public String getNombre(){
		return nombre;
	}

	public void setDireccion(String direccion){
		this.direccion = direccion;
	}
	public String getDireccion(){
		return direccion;
	}

	public void setTelefono(String telefono){
		this.telefono = telefono;
	}
	public String getTelefono(){
		return telefono;
	}
}
