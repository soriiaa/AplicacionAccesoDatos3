package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controlador.Controlador;
import vista.Vista;

public class Modelo {

	private Vista[] misVistas;
	private Controlador miControlador;
	private Connection miConexion;

	public void setControlador(Controlador miControlador) {
		this.miControlador = miControlador;
	}

	public void setVista(Vista[] misVistas) {
		this.misVistas = misVistas;
	}

	public Modelo() {

		String url = "jdbc:mysql://localhost:3306/aplicacionaccesodatosjdbc";
		String username = "root";
		String password = "";

		try {
			miConexion = DriverManager.getConnection(url, username, password);
			System.out.println("Conexión exitosa a MySQL");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public boolean anadirUsuario(String nombre, String apellido) {

		PreparedStatement ps;
		try {
			ps = miConexion.prepareStatement("INSERT INTO usuarios (nombre, apellido) VALUES (?, ?)");
			ps.setString(1, nombre);
			ps.setString(2, apellido);
			ps.executeUpdate();
			ps.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public String[][] recogerRegistros() {

		String consulta = "SELECT * FROM usuarios";

		try {
			Statement statement = miConexion.createStatement();
			ResultSet resultSet = statement.executeQuery(consulta);

			ArrayList<String[]> listaResultados = new ArrayList<>();
			while (resultSet.next()) {
				String id = resultSet.getString("id");
				String nombre = resultSet.getString("nombre");
				String apellido = resultSet.getString("apellido");
				String[] arrayFila = { id, nombre, apellido };
				listaResultados.add(arrayFila);
			}

			String[][] arrayUsuarios = new String[listaResultados.size()][3];

			for (int i = 0; i < listaResultados.size(); i++) {
				for (int j = 0; j < 3; j++) {
					arrayUsuarios[i][j] = listaResultados.get(i)[j];
				}
			}

			return arrayUsuarios;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

}
