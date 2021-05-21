<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Telefones</title>

<link rel="stylesheet" href="resources/css/painel.css">

</head>
<body>	
	<a href="index.jsp"><img alt="In�cio" title="In�cio" src="resources/img/home.png" width="50px" height="50px"></a>
	<a href="servletUsuario?acao=listartodos"><img alt="Voltar" title="Voltar" src="resources/img/voltar.png" width="50px" height="50px"></a>

	<div class="containerExterno">	

		<div class="containerInterno">
		
		<h1>Painel Telefones</h1>
		
		<h3 style="color: orange;">${msg}</h3>
	
		<form action="servletTelefone" method="post" id="formUser">
			<ul class="form-style-1">
				<li>
					<table>
						<tr>
							<td align="right">Usu�rio:</td>
							<td><input type="text" readonly="readonly" id="id" name="id" value="${userEscolhido.id}" class="field-long"></td>
							<td><input type="text" readonly="readonly" id="nome" name="nome" value="${userEscolhido.nome}" class="field-long"></td>
						</tr>
						<tr>
							<td align="right">DDD:</td>
							<td><input type="number" id="ddd" name="ddd" placeholder="Informe o DDD"></td>
						</tr>
						<tr>
							<td align="right">N�mero:</td>
							<td><input type="text" id="numero" name="numero" placeholder="Informe um N�mero"></td>
							<td align="left">
							<select id="tipo" name="tipo">
								<option>Trabalho</option>
								<option>Pessoal</option>
								<option>Resid�ncial</option>
							</select>
							</td>
						</tr>
						<tr height="50px">
							<td></td>
							<td align="left">
								<input type="submit" value="Salvar" style="width: 100%">
							</td>
							<td></td>
						</tr>
					</table>
				</li>
			</ul>
		</form>
		
		</div>
	
	</div>

	<div class="container">
		<table class="responsive-table">
			<caption>Telefones cadastrados</caption>
			    <tr style="color: white; background: #1d96b2">
			      <th scope="col">ID</th>
			      <th scope="col">N�mero</th>
			      <th scope="col">Tipo</th>
			      <th scope="col">Excluir</th>
			    </tr>			
			<c:forEach items="${telefones}" var="fone">
				<tr>
					<td style="width: 150px"><c:out value="${fone.id}"></c:out></td>
					<td style="width: 150px"><c:out value="${fone.numero}"></c:out></td>
					<td><c:out value="${fone.tipo}"></c:out></td>

					<td><a href="servletTelefone?acao=deleteFone&id=${fone.id}" onclick="return confirm('Deseja excluir?')"> <img alt="Excluir" title="Excluir" src="resources/img/excluir.png" width="32px" height="32px"> </a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
</body>
</html>