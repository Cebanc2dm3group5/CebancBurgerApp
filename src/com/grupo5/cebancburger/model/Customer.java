package com.grupo5.cebancburger.model;

import java.io.Serializable;

import android.app.Activity;
import android.content.ContentValues;

import com.grupo5.cebancburger.ddbbrepo.tables.CustomerTable;
import com.grupo5.cebancburger.interfaces.DDBBObject;

@SuppressWarnings("serial")
public class Customer implements Serializable, DDBBObject {

	private String nombre, direccion, telefono;
	private int id;
	private char idLet;

	public Customer(String nombre, String direccion, String telefono){
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public Customer(String nombre, String direccion, String telefono, int id,
			char idLet) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.id = id;
		this.idLet = idLet;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public char getIdLet() {
		return idLet;
	}

	public void setIdLet(char idLet) {
		this.idLet = idLet;
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
		CustomerTable ct = new CustomerTable();
		if (this.id == -1) {
			ct.insert(this, activity);
		} else {
//			ct.edit(this, activity);
		}		
	}

	@Override
	public void delete(Activity activity) {
		CustomerTable ct = new CustomerTable();
		ct.delete(activity, this.getId());		
	}

	@Override
	public ContentValues getContentValue(Activity activity) {
		// TODO Auto-generated method stub
		return null;
	}
	public ContentValues getContentValueForEdit(Activity activity, int id){
		return null;
	}
}
