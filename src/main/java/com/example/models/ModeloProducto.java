package com.example.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class ModeloProducto {
	
	
	public ModeloProducto(DataSource origenDatos) {
		this.origenDatos = origenDatos;
		
	}
	
	public void deleteProducto(int id) {
		PreparedStatement st = null;
		Producto pd= null;
		
		String sql="DELETE FROM PRODUCTO WHERE IDROPA = ?";		
		
		try {
			
			miConexion = origenDatos.getConnection();
			st = miConexion.prepareStatement(sql);
			st.setInt(1, id);
			st.execute();
			
			miConexion.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public Producto getProducto(int id) {
		PreparedStatement st = null;
		Producto pd= null;
		
		String sql="SELECT * FROM PRODUCTO WHERE IDROPA = ?";
		
		try {
			miConexion = origenDatos.getConnection();
			st = miConexion.prepareStatement(sql);
			st.setInt(1, id);
			rs= st.executeQuery();
			
			if(rs.next()) {
				
				pd = new Producto(rs.getString(2),rs.getString(3),rs.getDouble(4));
				pd.setId(rs.getInt(1));
							
			}else {
				throw new Exception("No hemo encontrado el producto con codigo= "+ id);
			}
			
			miConexion.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(pd);
		return pd;
		
	}
	
	public List<Producto> getProductos() throws Exception{
		
		List<Producto> listaProductos=new ArrayList<Producto>();
		String sql="SELECT * FROM PRODUCTO";
		
		miConexion = origenDatos.getConnection();
		miStatement = miConexion.createStatement();
		
		rs = miStatement.executeQuery(sql);
		
		
		while(rs.next()) {
			
			Producto pd = new Producto();
			pd.setId(rs.getInt(1));
			pd.setNombre(rs.getString(2));
			pd.setDescripcion(rs.getString(3));
			pd.setPrecio(rs.getDouble(4));
			
			listaProductos.add(pd);
			
		}
		
		miConexion.close();
		
		
		
		return listaProductos;
		
	}
	
	public void setProducto(Producto producto){
		
		

		PreparedStatement st = null;
		try {
			miConexion = origenDatos.getConnection();
			
			String sql ="INSERT INTO PRODUCTO (NOMBRE, DESCRIPCION, PRECIO) VALUES(?,?,?)";
			
			st = miConexion.prepareStatement(sql);
			
			st.setString(1,producto.getNombre());
			st.setString(2,producto.getDescripcion());
			st.setDouble(3,producto.getPrecio());
			
			st.execute();
			
			miConexion.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	public void updateProducto(int id, Producto p) {
		
		PreparedStatement st = null;
		System.out.println(p);
		
		try {
			
			String sql= "UPDATE PRODUCTO SET NOMBRE =?, DESCRIPCION =?, PRECIO=? WHERE IDROPA =?";
			miConexion = origenDatos.getConnection();
			st = miConexion.prepareStatement(sql);
			
			st.setString(1, p.getNombre());
			st.setString(2, p.getDescripcion());
			st.setDouble(3, p.getPrecio());
			
			st.setInt(4, id);
			
			st.execute();
			
			miConexion.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private DataSource origenDatos;
	private Connection miConexion;
	private Statement miStatement;
	private ResultSet rs;
	

}
