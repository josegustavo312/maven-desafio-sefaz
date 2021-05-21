package br.com.datainfo.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.datainfo.bean.BeanUsuario;
import br.com.datainfo.dao.DaoTelefone;
import br.com.datainfo.dao.DaoUsuario;

public class ServletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DaoUsuario daoUsuario = new DaoUsuario();
	private DaoTelefone daoTelefone = new DaoTelefone();
     
    public ServletUsuario() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao") != null ? request.getParameter("acao") : "listartodos";
			String id = request.getParameter("id");
			
			// Botão Delete
			if(acao!=null && acao.equalsIgnoreCase("delete") && id != null) {
				
				daoUsuario.delete(id);
				daoTelefone.deleteFoneUsuario(id);
				
				RequestDispatcher view = request.getRequestDispatcher("/usuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				request.setAttribute("msg", "Removido com sucesso!");
				view.forward(request, response);
				
			// Botão Editar
			}else if(acao!=null && acao.equalsIgnoreCase("editar")) {
				BeanUsuario beanUsuario = daoUsuario.consultar(id);
				
				RequestDispatcher view = request.getRequestDispatcher("/usuario.jsp");
				request.setAttribute("user", beanUsuario);
				view.forward(request, response);
			
			// Requisição da tela acessoliberado
			}else if(acao!=null && acao.equalsIgnoreCase("listartodos")) {
				RequestDispatcher view = request.getRequestDispatcher("/usuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
				
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String acao = request.getParameter("acao");
		
		if(acao!=null && acao.equalsIgnoreCase("reset")) {
			try {
				RequestDispatcher view = request.getRequestDispatcher("/usuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
						
			} catch (Exception e) {
				e.printStackTrace();
			}
					
		}else {
			
		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
							
		BeanUsuario beanUsuario = new BeanUsuario();
		beanUsuario.setId(id != null && !id.isEmpty()? Long.parseLong(id) : null);
		beanUsuario.setNome(nome);
		beanUsuario.setEmail(email);
		beanUsuario.setSenha(senha);		
				
		try {			
			boolean podeInserir = true;
					
			if (nome==null || nome.isEmpty()) {
				request.setAttribute("msg", "Nome deve ser informado");
				podeInserir = false;
						
			}else if (email==null || email.isEmpty()) {
				request.setAttribute("msg", "Email deve ser informado");
				podeInserir = false;					
						
			}else if (senha==null || senha.isEmpty()) {
				request.setAttribute("msg", "Senha deve ser informada");
				podeInserir = false;
						
			}else if (id == null || id.isEmpty() && !daoUsuario.validarEmail(email)) {
				request.setAttribute("msg", "Login existente!");
				podeInserir = false;
						
			}else if (id == null || id.isEmpty() && daoUsuario.validarEmail(email)) {
				daoUsuario.salvar(beanUsuario);
				request.setAttribute("msg", "Salvo com sucesso!");
						
			}else if (id != null || !id.isEmpty()){
				if(!daoUsuario.validarEmailUpdate(email, id)) {
					request.setAttribute("msg", "Email existente!");
				}else {
					daoUsuario.atualizar(beanUsuario);
					request.setAttribute("msg", "Atualizado com sucesso!");
				}
						
					
			}	
					
			// Caso o login seja existente
			if(!podeInserir) {
				// Carrega os dados preenchidos
				request.setAttribute("user", beanUsuario); 
			}
				
			RequestDispatcher view = request.getRequestDispatcher("/usuario.jsp");
			request.setAttribute("usuarios", daoUsuario.listar());
			view.forward(request, response);
					
			} catch (Exception e) {
				e.printStackTrace();
			}			
			
		}
	}

}
