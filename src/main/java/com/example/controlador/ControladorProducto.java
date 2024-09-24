package com.example.controlador;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.example.models.ModeloProducto;
import com.example.models.Producto;


@WebServlet("/ControladorProducto")
public class ControladorProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ModeloProducto modelo;
	
	@Resource(name="jdbc/conexion")
	private DataSource conexion;
	
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		try {
			
			modelo = new ModeloProducto(conexion);
			
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
			listarProducto(request, response);
			break;
		case "insertarBBDD":
			insertarProducto(request,response);
			break;
		case "actualizar":
			cargarProducto(request,response);
			break;
		case "actualizarBBDD":
			actualizarProducto(request,response);
			break;
		case "eliminar":
			eliminarProducto(request,response);
			break;
		
		}
		
		
		
		
		
		
	}
	
	private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int id =  Integer.parseInt(request.getParameter("idProducto"));
		modelo.deleteProducto(id);
		listarProducto(request,response);
		
	}


	private void actualizarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		int id = Integer.parseInt(request.getParameter("idProducto"));
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		Double precio = Double.parseDouble(request.getParameter("precio"));
		
		Producto nuevoProducto = new Producto(nombre, descripcion, precio);
		nuevoProducto.setId(id);
		System.out.println(nuevoProducto);
		modelo.updateProducto(id, nuevoProducto);
		listarProducto(request, response);
		
	}
	
	private void cargarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id= Integer.parseInt(request.getParameter("idProducto"));
		
		Producto p = modelo.getProducto(id);
		
		request.setAttribute("ProductoActualizar", p);
		
		RequestDispatcher miDP = request.getRequestDispatcher("/actualizarProducto.jsp");
		miDP.forward(request, response);
		
		
		
		
		
	}


	public void listarProducto(HttpServletRequest request, HttpServletResponse response){
		
		//obtener la lista del producto
		List<Producto> listaProductos=null;
		try {
			
			
			listaProductos = modelo.getProductos();
			
			//agregar lista de productos al request
			request.setAttribute("LISTAPRODUCTO", listaProductos);
			
			//enviar el Request a la pagina	
			RequestDispatcher miDP = request.getRequestDispatcher("/productos.jsp");
			miDP.forward(request, response);
			
		
			
		
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public void insertarProducto(HttpServletRequest request, HttpServletResponse response) {
		
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		Double precio = Double.parseDouble(request.getParameter("precio"));
		
		Producto nuevoProducto = new Producto(nombre, descripcion, precio);
		
		modelo.setProducto(nuevoProducto);
		
		
		listarProducto(request, response);
	}
}
