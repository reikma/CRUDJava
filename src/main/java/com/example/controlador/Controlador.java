package com.example.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.example.models.ModeloProducto;
import com.example.models.Producto;


@WebServlet("/Controlador")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//LLamar el Datasource (base de datos)
	
	@Resource(name="jdbc/conexion")
	private DataSource conexion;
	
	
    
    public Controlador() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//crear el objeto prinwiter el cual permitira escribir en la pagina web
		PrintWriter salida =response.getWriter();
		response.setContentType("text/plain");
		//crear la conexion con la base de datos a traves del pool de conexiones
		
		List<Producto> listaProductos=null;
		
	
		try {
			
			ModeloProducto lista = new ModeloProducto(conexion);
			 listaProductos = lista.getProductos();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		for (Producto p: listaProductos) {
			salida.println(p.getNombre());
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
