<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sistema Sefaz</title>

<link rel="stylesheet" href="resources/css/estilo.css">

</head>
<body>
	
	<div class="login-page" style="padding-top: 10%">
		
		<div class="form">

			<form action="servletPainel" method="post" class="login-form">
				<p class="message">Acesso ao sistema Sefaz </p>
				<h4>${msg}</h4>
				<input type="text" id="email" name="email" placeholder="Email"> <br />
				<input type="password" id="senha" name="senha" placeholder="Senha"> <br />
				<button type="submit" value="Entrar">Entrar</button>
			</form>

		</div>		
		
	</div>

</body>
</html>