package br.com.datainfo.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.datainfo.dao.DaoUsuario;

public class ServletPainel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DaoUsuario daoUsuario = new DaoUsuario();
       
    public ServletPainel() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			
			if(email != null && !email.isEmpty() && senha != null && !senha.isEmpty()) {				
				if(daoUsuario.validarLogin(email, senha)) {
					//Acesso Liberado
					RequestDispatcher dispatcher = request.getRequestDispatcher("painel.jsp");
					dispatcher.forward(request, response);
				}else {
					//Acesso Negado
					RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
					request.setAttribute("msg", "Email ou Senha Incorreta!");
					dispatcher.forward(request, response);
				}	
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
				request.setAttribute("msg", "Digite um Email e Senha!");
				dispatcher.forward(request, response);
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
