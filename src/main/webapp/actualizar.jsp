<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modificar elemento</title>

</head>
<style>
.formularios{
        
        padding: .5em;
        background-color: white;
        width: 600px;
        height: 400px;
        margin: 50px;
        

    }
    .formularios p{
        margin-bottom: 100px;
    }
    .informacion-user{
        width: 290px;
        display: block;
        height: 400px;
        background-color: white;
        margin-top: 50px;
    
    }
    .item-informacion{
        height: 50px;
        width: 280px;
        color: black;
        display: flex;
        align-items: center;

    }
    
    table{
        border-collapse: separate;
    }
    
    .nombre-t{
        text-align: right;
        
    }
    
    select{
        width: 100%;
        padding:7px;
    }
    input{
        width: 100%;
        padding: 5px;
        margin: 3px;
        
    }
    .boton{
        background-color: green;
        color: white;
    }
    .form2{
        display: flex;
        align-items: center;
        width: 100%;
        height: 100%;
        margin-left: 20%;
    }
</style>
<body>
<form action="Controlador" method="get">

<input type="hidden" name="instruccion" value="actualizarBBDD">
<input type="hidden" name="idProducto" value="${VALORES.get(0) }">
<input type="hidden" name="nameTabla" value="${tabla }">
<input type="hidden" name="namePK" value="${CAMPOS.get(0) }">
				
				<h2>Nombre de la tabla: ${tabla }</h2>
                <p>Ingrese los datos generales del elemento que desee modificar:</p>
                
                <table>
                		<c:set var="i" value="0"/>
                        <c:forEach var="campo" items="${CAMPOS }">
                        <tr>
                        
                            <td class="nombre-t">${campo }</td>
                            <td class="input-t"><input type="text" name="${campo }" value="${VALORES.get(i) }"> </td>
                                       
                        </tr>
                        <c:set var="i" value="${ i+1}"/>
                        </c:forEach>
                        
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><input class="boton" type="submit" value="Aceptar" ></td>
                        </tr>
                    </table>
                    
                    
                    
</form>

</body>
</html>