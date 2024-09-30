<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nuevo elemento</title>

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

<input type="hidden" name="instruccion" value="insertarBBDD">
<input type="hidden" name="nameTabla" value="${tabla }">

				<h2>Nombre de la tabla: ${tabla }</h2>
                <p>Ingrese los datos en los campos correspondientes para agregarlos a la DDBB:</p>
                    <table>
                        <c:forEach var="campo" items="${campoFormulario }">
                        <tr>
                        
                            <td class="nombre-t">${campo }</td>
                            <td class="input-t"><input type="text" name="${campo }"> </td>
                                       
                        </tr>
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