package com.grupo5.cebancburger.model;

import java.io.Serializable;

import android.app.Activity;
import android.content.ContentValues;

import com.grupo5.cebancburger.interfaces.DDBBObject;

@SuppressWarnings("serial")
public class Customer implements Serializable, DDBBObject {

	private String nombre, direccion, telefono;

	public Customer(String nombre, String direccion, String telefono){
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
