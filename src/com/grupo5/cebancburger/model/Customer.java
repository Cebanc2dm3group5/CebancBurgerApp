package com.grupo5.cebancburger.model;

import java.io.Serializable;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.grupo5.cebancburger.config.Options;
import com.grupo5.cebancburger.ddbbrepo.DDBBSQLite;
import com.grupo5.cebancburger.ddbbrepo.tables.CustomerTable;
import com.grupo5.cebancburger.ddbbrepo.tables.UserTable;
import com.grupo5.cebancburger.interfaces.DDBBObject;

@SuppressWarnings("serial")
public class Customer implements Serializable, DDBBObject {

	private String nombre, direccion, telefono;
	private int id = -1;
	private char idLet;

	public Customer(String nombre, String direccion, String telefono, char letraID) {
		this.setNombre(nombre);
		this.setDireccion(direccion);
		this.setTelefono(telefono);
		this.setIdLet(letraID);
	}

	public Customer(String nombre, String direccion, String telefono, int id,
			char idLet) {
		this.setNombre(nombre);
		this.setDireccion(direccion);
		this.setTelefono(telefono);
		this.setIdLet(idLet);
		this.setId(id);
	}
	
	public Customer(int id, Activity activity){
		Customer customer = CustomerTable.getCustomer(activity, id);
		this.setDireccion(customer.getDireccion());
		this.setIdLet(customer.getIdLet());
		this.setNombre(customer.getNombre());
		this.setTelefono(customer.getTelefono());
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

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTelefono() {
		return telefono;
	}

	@Override
	public void save(Activity activity) {
		CustomerTable ct = new CustomerTable();
		if (this.id == -1) {
			ct.insert(this, activity);
		} else {
			 ct.edit(this, activity);
		}
	}

	@Override
	public void delete(Activity activity) {
		CustomerTable ct = new CustomerTable();
		ct.delete(activity, this.getId());
	}

	@Override
	public ContentValues getContentValue(Activity activity) {
		ContentValues nr = new ContentValues();
		nr.put("CustomerID", getNextID(activity));
		nr.put("Name", this.getNombre());
		nr.put("Address", this.getDireccion());
		nr.put("IDChar", String.valueOf(this.getIdLet()));
		nr.put("Phone", this.getTelefono());
		return nr;
	}
	
	public ContentValues getContentValue(Activity activity, SQLiteDatabase db) {
		ContentValues nr = new ContentValues();
		nr.put("CustomerID", getNextID(activity, db));
		nr.put("Name", this.getNombre());
		nr.put("Address", this.getDireccion());
		nr.put("IDChar", String.valueOf(this.getIdLet()));
		nr.put("Phone", this.getTelefono());
		return nr;
	}

	public ContentValues getContentValueForEdit(Activity activity, int id) {
		ContentValues nr = new ContentValues();
		nr.put("CustomerID", this.getId());
		nr.put("Name", this.getNombre());
		nr.put("Address", this.getDireccion());
		nr.put("IDChar", String.valueOf(this.getIdLet()));
		nr.put("Phone", this.getTelefono());
		return nr;
	}

	private static int getNextID(Activity activity) {
		String query = "SELECT CustomerID FROM Customer ORDER BY CustomerID DESC LIMIT 1";
		int lastID = 0;
		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		Cursor c = db.rawQuery(query, null);
		if (c.moveToFirst()) {
			lastID = c.getInt(0);
		}
		return lastID + 1;
	}
	
	private static int getNextID(Activity activity, SQLiteDatabase db) {
		String query = "SELECT CustomerID FROM Customer ORDER BY CustomerID DESC LIMIT 1";
		int lastID = 0;
//		SQLiteDatabase db = DDBBSQLite.getDDBB(Options.getDDBBName(), activity);
		Cursor c = db.rawQuery(query, null);
		if (c.moveToFirst()) {
			lastID = c.getInt(0);
		}
		return lastID + 1;
	}
}
