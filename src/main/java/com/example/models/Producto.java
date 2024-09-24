package com.example.models;

public class Producto {
	
	
	
	
	public Producto() {
		
	}

	public Producto(String nombre, String descripcion, double precio) {

		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
	}
	
	

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio
				+ "]";
	}

	public int getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public double getPrecio() {
		return precio;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	private int id;
	private String nombre;
	private String descripcion;
	private double precio;


}
