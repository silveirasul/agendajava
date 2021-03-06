package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {
	// ** M?dulo de Conex?o **/

	// Par?metros de conex?o
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql: //127.0.0.1:3306/dbagenda?userTimezone=true$serverTimezone=UTC";
	private String user = "root";
	private String password = "88aejpx0";

	// Metodos de conex?o
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;

		}
	}

	/** CRUD CREATE **/
	public void inserirContato(JavaBeans contato) {
		String create = "insert into contatos (nome, fone, email) values (?,?,?)";
		try {

			// abir a conex?o
			Connection con = conectar();
			// Prparar a query para execu??o no banco de dados
			PreparedStatement pst = con.prepareStatement(create);
			// Substituir os par?metros pelo JavaBeans

			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			// Executar a query
			pst.executeUpdate();
			// Encerrar a conex?o com o Banco
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	/** CRUD READ **/

	public ArrayList<JavaBeans> listarContatos() {
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		String read = "select * from contatos order by idcon desc";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			// o lo?a
			while (rs.next()) {
				// Vari?veis de apoo
				String idcon = rs.getString(1);
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				// Populando o Arraylist
				contatos.add(new JavaBeans(idcon, nome, fone, email));
			}
			con.close();
			return contatos;
		} catch (Exception e) {
			System.out.println(e);
			return null;

		}

	}

}
