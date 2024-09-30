<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    <%@ page import="java.util.*, com.example.models.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BBDD......</title>
</head>



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
	<h1>Tabla ${nTables }</h1>
	<c:set var="fila" value="1"/>
 
 
 
 	<c:url var="linkInsertar" value="Controlador">
       		<c:param name="instruccion" value="cargaForm"></c:param>
       		<c:param name="nTables" value="${nTables }"></c:param>
       		
     </c:url>
	
 	<div class="dAgregar">
    	<input type="button" value="+ Add Productos" class="btn-agregar" onclick="window.location.href='${linkInsertar }'">
    </div>
    
    
    <!-- carga de tablas -->
       
 	
 	
 	<form action="Controlador" method="get">
 		<select name=nTables>
 			<c:forEach var="name" items="${LISTABLAS }">
 			
 				<c:if test="${nTables!=null && name.equals(nTables) }">
 					<option value="${name }" selected>${name }</option>
 				
 				</c:if>
 				<c:if test="${name!=nTables }">
 					<option value="${name }">${name }</option>
 				
 				</c:if>
 			</c:forEach >
 		</select>
 	<input class="boton" type="submit" value="Cargar" >
 	</form>
 	
 	
 	
 	
 	
 	
 	
 	
 		
 	<c:set var="row" value="1"/>
 	
 	<table>
 	<!-- carga del encabezado de la tabla-->
 	<tr>
 		<c:forEach var="fiels" items="${LISTCAMPOS }">
 		
 			<th>${fiels }</th>
 		</c:forEach>
 		<th colspan="2">Acciones</th>
 	</tr>
 	<!-- carga del cuerpo -->
 	<c:forEach var="fila" items="${ LISTVALORES}">
 	<c:set var="estado" value="true"/>
 	
 	
 	
 		<!-- carga de acciones -->
       	<c:url var="linkInsertar" value="Controlador">
       		<c:param name="instruccion" value="actualizar"></c:param>
       		<c:param name="identificador" value="${fila.get(LISTCAMPOS.get(0)) }"></c:param>
       		<c:param name="nTables" value="${nTables }"></c:param>
       		<c:param name="namePK" value="${LISTCAMPOS.get(0) }"></c:param>
       		
       	</c:url>
       	<c:url var="linkEliminar" value="Controlador">
       		<c:param name="instruccion" value="eliminar"></c:param>
       		<c:param name="idProducto" value="${fila.get(LISTCAMPOS.get(0)) }"></c:param>
       		<c:param name="nTables" value="${nTables }"></c:param>
       		<c:param name="namePK" value="${LISTCAMPOS.get(0) }"></c:param>
       	</c:url>
       	
       	
 		<c:if test="${row == 1 && estado}">
 			<tr class="row-1">
 				<c:forEach var="columna" items="${ LISTCAMPOS}">
 					<td>${fila.get(columna) }</td>	
 				</c:forEach>
 					<td class="botones"><a href="${linkInsertar }"><input type="button" value="Modificar" class="btn-update"></a>&nbsp;<a href="${linkEliminar }"><input type="button" value="Eliminar" class="btn-delete"></a></td> 
 			</tr>
 	 	<c:set var="row" value="2"/>
 	 		
                   <c:set var="estado" value="false" />
    	</c:if>
    	<c:if test="${row == 2  && estado}">
    		<tr class="row-2">
 				<c:forEach var="columna" items="${ LISTCAMPOS}">
 					<td>${fila.get(columna) }</td>	
 				</c:forEach>
 					<td class="botones"><a href="${linkInsertar }"><input type="button" value="Modificar" class="btn-update"></a>&nbsp;<a href="${linkEliminar }"><input type="button" value="Eliminar" class="btn-delete"></a></td> 
 			</tr>
    	
    		
    		<c:set var="row" value="1"/>
            <c:set var="estado" value="false" />
        
    	</c:if>
    		
 	</c:forEach>
 		
 	</table>
 
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
            </table>
 




</body>
</html>