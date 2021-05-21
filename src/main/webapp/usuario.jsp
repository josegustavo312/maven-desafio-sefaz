<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Painel Usuários</title>

<link rel="stylesheet" href="resources/css/painel.css">

</head>
<body>
	<a href="index.jsp"><img alt="Início" title="Início" src="resources/img/home.png" width="50px" height="50px"></a>
	<a href="painel.jsp"><img alt="Voltar" title="Voltar" src="resources/img/voltar.png" width="50px" height="50px"></a>

	<div class="containerExterno">	

		<div class="containerInterno">
		
		<h1>Painel Usuários</h1>
		
		<h3 style="color: orange;">${msg}</h3>
	
		<form action="servletUsuario" method="post" id="formUser">
			<ul class="form-style-1">
				<li>
					<table>
						<tr>
							<td align="right">Código:</td>
							<td><input type="text" readonly="readonly" id="id" name="id" class="field-long" value="${user.id}"></td>
							
							<td align="right">Nome:</td>
							<td><input type="text" id="nome" name="nome"  placeholder="Informe um Nome" class="field-long" value="${user.nome}" maxlength="100"></td>
						</tr>
						<tr>
							<td align="right">Email:</td>
							<td><input type="email" id="email" name="email" placeholder="Informe o Email"  class="field-long" value="${user.email}" maxlength="100"></td>
							
							<td align="right">Senha:</td>
							<td><input type="password" id="senha" name="senha" placeholder="Informe uma Senha" class="field-long" value="${user.senha}" maxlength="100"></td>
						</tr>
						<tr height="50px">
							<td></td>
							<td>
								<input type="submit" value="Salvar" style="width: 100%">
							</td>
							<td></td>
							<td>
								<input type="submit" value="Cancelar" style="width: 100%" onclick="document.getElementById('formUser').action='servletUsuario?acao=reset'">
							</td>
						</tr>
					</table>
				</li>
			</ul>
		</form>
		
		</div>
		
	</div>
	
	<div class="containerExterno">	

		<div class="containerInterno">
		
			<form action="servletPesquisa" method="post">
				<ul class="form-style-1">
					<li>
						<table>
							<tr>
								<td>Descrição:</td>
								<td style="width: 70%">
									<input type="text" id="descricaoConsulta" name="descricaoConsulta" style="width: 100%" placeholder="Digite o nome para pesquisar...">
								</td>
								<td>
									<input type="submit" value="Pesquisar">
								</td>
							</tr>
						</table>
					</li>
				</ul>
			</form>
		
		</div>
		
	</div>

	<div class="container">
		<table class="responsive-table">
			<caption>Lista de Usuários</caption>
			    <tr style="color: white; background: #1d96b2">
					<th scope="col">ID</th>
					<th scope="col">Nome</th>
					<th scope="col">Email</th>
					<th scope="col">Telefones</th>
					<th scope="col">Excluir</th>
					<th scope="col">Editar</th>			      
			    </tr>			
			<c:forEach items="${usuarios}" var="user">
				<tr>
					<td style="width: 150px"><c:out value="${user.id}"></c:out></td>					
					<td><c:out value="${user.nome}"></c:out></td>
					<td><c:out value="${user.email}"></c:out></td>
					
					<td><a href="servletTelefone?acao=addFone&id=${user.id}"> <img alt="Telefones" title="Telefones" src="resources/img/telefones.png" width="32px" height="32px"> </a></td>
					<td><a href="servletUsuario?acao=delete&id=${user.id}" onclick="return confirm('Deseja excluir?')"> <img alt="Excluir" title="Excluir" src="resources/img/excluir.png" width="32px" height="32px"> </a></td>
					<td><a href="servletUsuario?acao=editar&id=${user.id}"> <img alt="Editar" title="Editar" src="resources/img/editar.png" width="32px" height="32px"> </a></td>
				</tr>
			</c:forEach>
		</table>
	</div>	
	
</body>
</html>