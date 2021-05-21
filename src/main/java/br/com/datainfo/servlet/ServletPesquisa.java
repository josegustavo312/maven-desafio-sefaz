package br.com.datainfo.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.datainfo.bean.BeanUsuario;
import br.com.datainfo.dao.DaoUsuario;

public class ServletPesquisa extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DaoUsuario daoUsuario = new DaoUsuario();
       
    public ServletPesquisa() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String descricaoPesquisa = request.getParameter("descricaoConsulta");
			
			if(descricaoPesquisa != null && !descricaoPesquisa.trim().isEmpty()) {				
				List<BeanUsuario> listaPesquisa = daoUsuario.listar(descricaoPesquisa);
				
				RequestDispatcher view = request.getRequestDispatcher("/usuario.jsp");
				request.setAttribute("usuarios", listaPesquisa);
				view.forward(request, response);
				
			}else {
				RequestDispatcher view = request.getRequestDispatcher("/usuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
