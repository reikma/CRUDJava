<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    <%@ page import="java.util.*, com.example.models.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Productos de Moda......</title>
</head>

<%
//obtiene los productos del controlador
	List<Producto> productos =(List<Producto>)request.getAttribute("LISTAPRODUCTO");

%>


<style>
*{
        margin:0;
        padding:0;
        box-sizing:border-box;
        
    }
    body{
  
        background-color: #BBC3C6;
    }
     .panel{
        width: 100%;
        height: 100%;
        display: flex;
        padding-left: 300px;
        background-color: #BBC3C6;
        flex-wrap: wrap;
       
    }
     .dAgregar{
        border-bottom: gainsboro inset 1px;
        width: 100%;
    }
     .btn-agregar{
        background-color: purple;
        color: white;
        margin: 10px;
        padding: 5px;
    }
    
    table{
        width: 95%;
        border: whitesmoke solid 1px;
        
        margin-left: 15px;
        border-collapse: collapse;
        background-color: white;
        
    }
    td{
        
        border: gainsboro solid 1px;
        padding: 5px;
    }
    th{
        border: gainsboro solid 1px;
        background-color: #FFFFFF;
        text-align: left;
        padding: 5px;
    }
    .row-1{
        background-color: whitesmoke;
    }
    .row-2{
        background-color: #FFFFFF;
    }
    .btn-update{
        padding: 2px;
    }
    .btn-delete{
        padding: 2px;
    }
    .botones{
        display: flex;
        flex-direction: row;
    }

</style>
<body>
	<c:set var="fila" value="1"/>
 
	<h1>Lista de productos</h1>
 	<div class="dAgregar">
    	<input type="button" value="+ Add Productos" class="btn-agregar" onclick="window.location.href='insertarProductos.jsp'">
    </div>
 
 	<table>
    	<tr>
        	<th>Id Producto</th>
            <th>Nombre</th>
            <th>Descripcion</th>
            <th>Precio</th>
            <th colspan="2">Acciones</th> 
        </tr>
              
        <c:set var="fila" value="1"/>
               
               
       	<c:forEach var="p" items="${LISTAPRODUCTO}">
       	<c:set var="estado" value="true"/>
       	
       	
       	<!-- carga de acciones -->
       	<c:url var="linkTemp" value="ControladorProducto">
       		<c:param name="instruccion" value="actualizar"></c:param>
       		<c:param name="idProducto" value="${p.id }"></c:param>
       	</c:url>
       	<c:url var="linkEliminar" value="ControladorProducto">
       		<c:param name="instruccion" value="eliminar"></c:param>
       		<c:param name="idProducto" value="${p.id }"></c:param>
       	</c:url>
       	
       		<c:if test="${fila == 1 && estado}">
       
                	<tr class="row-1">
    					<td>${p.id }</td>
                    	<td>${p.nombre }</td>
                    	<td>${p.descripcion }</td>
                    	<td>${p.precio }</td>
                    	<td class="botones"><a href="${linkTemp }"><input type="button" value="Modificar" class="btn-update"></a>&nbsp;<a href="${linkEliminar }"><input type="button" value="Eliminar" class="btn-delete"></a></td>   		 
                	</tr>
                
                
                   <c:set var="fila" value="2"/>
                   <c:set var="estado" value="false" />
             </c:if>     
                
                  
                 <c:if test="${fila == 2  && estado}">
                <tr class="row-2">
             
                		 	<td>${p.id }</td>
                    		<td>${p.nombre }</td>
                    		<td>${p.descripcion }</td>
                    		<td>${p.precio }</td>
                    		<td class="botones"><a href="${linkTemp }"><input type="button" value="Modificar" class="btn-update"></a>&nbsp;<a href="${linkEliminar }"><input type="button" value="Eliminar" class="btn-delete"></a></td>   		 
                		 
                </tr>
                <c:set var="fila" value="1"/>
                <c:set var="estado" value="false" />
        
    			</c:if>
           
                
                
                </c:forEach>
                
               
               	 
        
                
            </table>
 




</body>
</html>