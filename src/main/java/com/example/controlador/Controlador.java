package com.example.controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.example.models.CargaBBDD;


@WebServlet("/Controlador")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;


	private CargaBBDD data;
	
	@Resource(name="jdbc/conexion")
	private DataSource conexion;
	
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		try {
			
			data = new CargaBBDD(conexion);
			
		}catch(Exception e) {
			
			throw new ServletException(e);
		}
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String comando = request.getParameter("instruccion");
		
		
		if(comando==null) comando="listar";
		
		switch(comando) {
		case "listar":
			listarTabla(request, response);
			break;
		case "insertarBBDD":
			insertarElemento(request,response);
			break;
		case "actualizar":
			cargarElemento(request,response);
			break;
		case "actualizarBBDD":
			actualizarElemento(request,response);
			break;
		case "eliminar":
			eliminarElemento(request,response);
			break;
		case "cargaForm":
			cargaFormularion(request,response);
			break;
		
		}
		
		
		
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public void listarTabla(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
			
		List<String> listTables;
		
		listTables=data.CargaTablas();
		
		request.setAttribute("LISTABLAS", listTables);
		
		String nombre=request.getParameter("nTables");
		List<String> listFields=null;
		List<HashMap<String, String>> listValues=null;
		if(nombre!=null) {
			request.setAttribute("nTables", nombre);
			listFields=data.CargaColumnas(nombre);
		}else {
			nombre=listTables.get(0);
			listFields=data.CargaColumnas(listTables.get(0));
			request.setAttribute("nTables", nombre);
		}
		
		request.setAttribute("LISTCAMPOS", listFields);
		
		listValues=data.CargaValores(nombre, listFields);
				
		request.setAttribute("LISTVALORES", listValues);
				
		
		RequestDispatcher miDP = request.getRequestDispatcher("/vistaPrincipal.jsp");
		miDP.forward(request, response);
		
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------
	private void eliminarElemento(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int id =  Integer.parseInt(request.getParameter("idProducto"));
		String table = request.getParameter("nTables");
		String namePK = request.getParameter("namePK");
		
		request.setAttribute("nTables", table);
		data.deletedElement(table, namePK ,id);
		try {
			listarTabla(request,response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	private void actualizarElemento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		int id = Integer.parseInt(request.getParameter("idProducto"));
		String identificador = request.getParameter("namePK");
		String nameTable = request.getParameter("nameTabla");
		List<String> campos = data.CargaColumnas(nameTable);
		List<String> valores = new ArrayList<String>();
		
		System.out.println(nameTable);
		
		for(String k : campos) {
			valores.add(request.getParameter(k));
		}
		data.actualizarBBDD(nameTable, identificador, id, valores, campos);
		request.setAttribute("nTables", nameTable);
		
		
		
		listarTabla(request, response);
		
	}
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
	private void cargarElemento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id= Integer.parseInt(request.getParameter("identificador"));
		
		
		String nameTable = request.getParameter("nTables");
		String nameIdentificador = request.getParameter("namePK");
		List<String> campos = data.CargaColumnas(nameTable);
		List<String> valores = data.CargaElementoModificar(id, nameTable, nameIdentificador, campos);
		
		request.setAttribute("CAMPOS", campos);
		request.setAttribute("VALORES", valores);
		request.setAttribute("tabla", nameTable);
		RequestDispatcher miDP = request.getRequestDispatcher("/actualizar.jsp");
		miDP.forward(request, response);
		
		
		
		
		
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public void insertarElemento(HttpServletRequest request, HttpServletResponse response) {
		
		String nameTable = request.getParameter("nameTabla");
		List<String> campos = data.CargaColumnas(nameTable);
		List<String> valores = new ArrayList<String>();
		
		for(String k : campos) {
			valores.add(request.getParameter(k));
		}
		
		data.setElementoBBDD(campos, valores, nameTable);
		request.setAttribute("nTables", nameTable);
		try {
			listarTabla(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public void cargaFormularion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nameTable = request.getParameter("nTables");
		List<String> campos=data.CargaColumnas(nameTable);
		request.setAttribute("campoFormulario", campos);
		request.setAttribute("tabla",nameTable );

		
		RequestDispatcher miDP = request.getRequestDispatcher("/insertar.jsp");
		miDP.forward(request, response);
	}
}
