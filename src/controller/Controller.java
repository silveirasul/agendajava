package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	JavaBeans contato = new JavaBeans();

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/main")) {
			contatos(request, response);
		} else if (action.equals("/insert")) {
			novoContato(request, response);
		} else if (action.equals("/select")) {
			listarContato(request, response);
		} else	{
		response.sendRedirect("index.html");
	 }
	}

	// Listar Contatos
	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Criando um objeto que ir� receber os dados JavaBeans
		ArrayList<JavaBeans> lista = dao.listarContatos();
		// Encaminhar a lista

		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);

		// Teste de recebimento

		// for (int i = 0; i < lista.size(); i++) {
		// System.out.println(lista.get(i).getIdcon());
		// System.out.println(lista.get(i).getNome());
		// System.out.println(lista.get(i).getFone());
		// System.out.println(lista.get(i).getEmail());
		// }
	}

	// Novo contato
	protected void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// teste de recebimento dos dados formul�rio
		System.out.println(request.getParameter("nome"));
		System.out.println(request.getParameter("fone"));
		System.out.println(request.getParameter("email"));
		// setar as variaves javabeans
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		// invocar o m�todo inseririContato passando o objeto contato
		dao.inserirContato(contato);
		// Redirecionar para o documento agenda.jsp
		response.sendRedirect("main");

	}
	
	//Editar contato
	protected void listarContato(HttpServletRequest request, 
			HttpServletResponse response) {
		String idcon = request.getParameter("idcon");
		System.out.print(idcon);
	}
}
