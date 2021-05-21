<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Painel</title>

<link rel="stylesheet" href="resources/css/painel.css">

</head>
<body>
	<a href="index.jsp"><img alt="Início" title="Início" src="resources/img/home.png" width="50px" height="50px"></a>
	<br/>
	
	<div class="containerExterno">
		<div class="containerInterno" style="padding-top: 10%">			
			<table>
				<tr>
					<td>
						<a href="servletUsuario?acao=listartodos">
							<img alt="Cadastro de Usuários" title="Painel de Usuários" src="resources/img/user.png" width="100px" height="100px">				
						</a>
					</td>
				</tr>
				<tr>
					<td>
						Usuários
					</td>
				</tr>
			</table>
			
		</div>
	</div>	

</body>
</html>