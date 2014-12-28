package com.grupo5.cebancburger.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Pedido implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Cliente cliente = null;
	private ArrayList<Burger> alBurger = null;
	private ArrayList<Bebida> alBebida = null;
	private String regalo = "";
	
	public Pedido(){
		
		alBurger = new ArrayList<Burger>();
		alBebida = new ArrayList<Bebida>();
		
	}
	
	public void setCliente(Cliente cliente){
		
		this.cliente = cliente;
		
	}
	
	public Cliente getCliente(){
		
		return cliente;
		
	}
	
	public void setBurger(Burger burger){
		
		alBurger.add(burger);
		
	}
	
	public ArrayList<Burger> getBurger(){
		
		return alBurger;
		
	}
	
	public void setBebida(Bebida bebida){
		
		alBebida.add(bebida);
		
	}
	
	public ArrayList<Bebida> getBebida(){
		
		return alBebida;
		
	}
	
	public void setRegalo(String regalo){
		
		this.regalo = regalo;
		
	}
	
	public String getRegalo(){
		
		return regalo;
		
	}

}
