package com.example.models;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

public class CargaBBDD {
	
	
	
	
	public CargaBBDD(DataSource origenDatos) {
	
		this.origenDatos = origenDatos;
	}
	
	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public List<String> CargaTypeDate(String table){
		
		ResultSetMetaData rsMT = null;
		List<String> listTypes = new ArrayList<String>();
		try {
			conexion = origenDatos.getConnection();
			
			String sql="SELECT * FROM " + table;
			Statement st;
			st = conexion.createStatement();
			rs=st.executeQuery(sql);
			
			rsMT=rs.getMetaData();		
			int cant = rsMT.getColumnCount();
			
			for(int i=1; i<=cant; i++) {
				listTypes.add(rsMT.getColumnTypeName(i));
			}
						
			rs.close();
			conexion.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return listTypes;
	}
	
	//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public List<String> CargaTablas() {
		DatabaseMetaData datosBBDD= null;
		
		String[] g = {"TABLE"};
		List<String> listTables = new ArrayList<String>();
		
		try {
			conexion = origenDatos.getConnection();
			
			datosBBDD=conexion.getMetaData();
			
			rs=datosBBDD.getTables("tiendaonline", null, null, g);
		
			while(rs.next()) {
				listTables.add(rs.getString("TABLE_NAME"));
			}
			
			rs.close();
			conexion.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listTables;
		
	}
	
	public List<String> CargaColumnas(String nombre) {
		DatabaseMetaData datosBBDD= null;
		
		List<String> listCampos = new ArrayList<String>();
		
		try {
			conexion = origenDatos.getConnection();
			
			datosBBDD=conexion.getMetaData();
			
			rs=datosBBDD.getColumns(null, null, nombre, null);
			
			while(rs.next()) {
				
				listCampos.add(rs.getString("COLUMN_NAME"));		
			}
			
			rs.close();
			conexion.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listCampos;
		
	}
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public List<HashMap<String, String>> CargaValores(String nombre ,List<String> campos) {
		
		
		List<HashMap<String, String>> lista = new ArrayList<HashMap<String, String>>();
		
		String sql="SELECT * FROM " + nombre;
		Statement st;
		try {
			conexion= origenDatos.getConnection();
			st = conexion.createStatement();
			rs=st.executeQuery(sql);
			
			while(rs.next()) {
				HashMap<String, String> valoresT = new HashMap<String, String>();
				
				for(String campo: campos) {
					valoresT.put(campo, rs.getString(campo));					
				}
				lista.add(valoresT);		
			}
			
			
			rs.close();
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista;
		
		
	}
	//------------------------------------------------------------------------------------------------------------------------------------------------------------
	public void setElementoBBDD(List<String> campos,List<String> valores, String tabla){
		
		String sql ="INSERT INTO "+ tabla + "(";
		
		List<String> tiposdatos=null;
		
		int tam = 0;
		for(String campo : campos) {
			
			sql+=campo + ",";
			tam++;
		}
		
		int ultimo=sql.length()-1;
		
		sql=sql.substring(0, ultimo) + ") VALUES(";
			
		for(int i=0; i<tam;i++) {
			sql+="?,";
		}
		
		ultimo=sql.length()-1;
		sql=(sql.substring(0, ultimo) +")").toUpperCase();
		
		 
		PreparedStatement st = null;
		try {
			conexion = origenDatos.getConnection();
			
			st = conexion.prepareStatement(sql);
			tiposdatos=CargaTypeDate(tabla);
			
			int cont=0;
			for(String tipo : tiposdatos) {
				cont++;
				
				
				switch(tipo) {
				case "VARCHAR":
					st.setString(cont, valores.get(cont-1));
					break;
				case "INT":
					st.setInt(cont, Integer.parseInt(valores.get(cont-1)));
					break;
				case "DOUBLE":
					st.setDouble(cont, Double.parseDouble(valores.get(cont-1)));
					break;
				case "DECIMAL":
					st.setDouble(cont, Double.parseDouble(valores.get(cont-1)));
					break;
				case "DATE":
					
					SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd");
					
					java.util.Date fecha =  formato.parse(valores.get(cont-1));		
					
					java.sql.Date fechaSql = new java.sql.Date(fecha.getTime());
					st.setDate(cont,fechaSql);
					break;
				}
					
			}	
			st.execute();
			st.close();
			conexion.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------
	public void deletedElement(String table,String namePK, int id) {
		PreparedStatement st = null;
		
		String sql="DELETE FROM "+table.toUpperCase()+" WHERE "+namePK+" = ?";		
		
		try {
			
			conexion = origenDatos.getConnection();
			st = conexion.prepareStatement(sql);
			
			st.setInt(1, id);
			st.execute();
			
			conexion.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public List<String> CargaElementoModificar(int id, String tabla, String identificador, List<String> campos){
		PreparedStatement st = null;
		
		List<String> valores = new ArrayList<String>();
		
		String sql="SELECT * FROM "+ tabla +" WHERE " + identificador + " = ?";
		
		try {
			conexion = origenDatos.getConnection();
			st = conexion.prepareStatement(sql);
			st.setInt(1, id);
			rs= st.executeQuery();
			
			if(rs.next()) {
				
				for(String campo : campos) {
					
					valores.add(rs.getString(campo));
				}
				
				
							
			}else {
				throw new Exception("No hemo encontrado el producto con codigo= "+ id);
			}
			st.close();
			conexion.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return valores;
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public void actualizarBBDD(String tabla, String identificador,int id, List<String> valores, List<String> campos) {
		List<String> tiposdatos=null;
		
		String sql= "UPDATE "+ tabla + " SET ";
		tiposdatos=CargaTypeDate(tabla);
		
		for(String campo : campos) {
			
			sql+=campo + " = ?,";
		}
		
		int ultimo=sql.length()-1;
		
		
		sql=(sql.substring(0, ultimo) + " WHERE " + identificador + " = " + id).toUpperCase();
		
		PreparedStatement st=null;
		try {
			
			
			
			
			conexion = origenDatos.getConnection();
			st = conexion.prepareStatement(sql);
			
			
			int cont=0;
			for(String tipo : tiposdatos) {
				cont++;
				
				
				switch(tipo) {
				case "VARCHAR":
					st.setString(cont, valores.get(cont-1));
					break;
				case "INT":
					st.setInt(cont, Integer.parseInt(valores.get(cont-1)));
					break;
				case "DOUBLE":
					st.setDouble(cont, Double.parseDouble(valores.get(cont-1)));
					break;
				case "DECIMAL":
					st.setDouble(cont, Double.parseDouble(valores.get(cont-1)));
					break;
				case "DATE":
					
					SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd");
					
					java.util.Date fecha =  formato.parse(valores.get(cont-1));		
					
					
					
					java.sql.Date fechaSql = new java.sql.Date(fecha.getTime());
					st.setDate(cont,fechaSql);
					
					break;
				}
				
				
			}
			
			
			st.execute();
			
			st.close();
			conexion.close();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private DataSource origenDatos;
	private Connection conexion;
	
	private ResultSet rs;
	
}
