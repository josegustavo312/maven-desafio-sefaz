package br.com.datainfo.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.datainfo.bean.BeanTelefone;
import br.com.datainfo.bean.BeanUsuario;
import br.com.datainfo.dao.DaoTelefone;
import br.com.datainfo.dao.DaoUsuario;

public class ServletTelefone extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DaoUsuario daoUsuario = new DaoUsuario();	
	private DaoTelefone daoTelefone = new DaoTelefone();
    
    public ServletTelefone() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao");
			String id = request.getParameter("id");
			
			if(id != null) {
				if(acao.equals("addFone")) {				
					
					BeanUsuario beanUsuario = daoUsuario.consultar(id);
					
					//Atributos da sessão
					request.getSession().setAttribute("userEscolhido", beanUsuario);
					
					RequestDispatcher view = request.getRequestDispatcher("/telefone.jsp");
					request.setAttribute("telefones", daoTelefone.listar(beanUsuario.getId()));
					view.forward(request, response);
					
				}else if(acao.equals("deleteFone")){
					String foneId= request.getParameter("id");
					
					daoTelefone.delete(foneId);
					
					BeanUsuario beanUsuario = (BeanUsuario) request.getSession().getAttribute("userEscolhido");
					
					RequestDispatcher view = request.getRequestDispatcher("/telefone.jsp");
					request.setAttribute("telefones", daoTelefone.listar(beanUsuario.getId()));
					request.setAttribute("msg", "Removido com sucesso!");
					view.forward(request, response);
				}
				
			}else {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			}		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			BeanUsuario beanUsuario = (BeanUsuario) request.getSession().getAttribute("userEscolhido");
			
			String ddd = request.getParameter("ddd");
			String numero = request.getParameter("numero");
			String tipo = request.getParameter("tipo");
			
			if(ddd == null || (ddd != null && ddd.isEmpty())) {
				//Atributos da sessão
				request.getSession().setAttribute("userEscolhido", beanUsuario);
				
				RequestDispatcher view = request.getRequestDispatcher("/telefone.jsp");
				request.setAttribute("telefones", daoTelefone.listar(beanUsuario.getId()));
				request.setAttribute("msg", "Informe o DDD!");
				view.forward(request, response);
				
			}else if(numero == null || (numero != null && numero.isEmpty())) {
				//Atributos da sessão
				request.getSession().setAttribute("userEscolhido", beanUsuario);
				
				RequestDispatcher view = request.getRequestDispatcher("/telefone.jsp");
				request.setAttribute("telefones", daoTelefone.listar(beanUsuario.getId()));
				request.setAttribute("msg", "Informe o Número!");
				view.forward(request, response);
				
			}else {
				BeanTelefone beanTelefone = new BeanTelefone();
				beanTelefone.setDdd(Long.parseLong(ddd));
				beanTelefone.setNumero(numero);
				beanTelefone.setTipo(tipo);
				beanTelefone.setUsuario(beanUsuario.getId());
				
				daoTelefone.salvar(beanTelefone);
				
				//Atributos da sessão
				request.getSession().setAttribute("userEscolhido", beanUsuario);
				
				RequestDispatcher view = request.getRequestDispatcher("/telefone.jsp");
				request.setAttribute("telefones", daoTelefone.listar(beanUsuario.getId()));
				request.setAttribute("msg", "Salvo com sucesso!");
				view.forward(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
