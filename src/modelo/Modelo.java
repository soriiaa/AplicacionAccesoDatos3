package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import controlador.Controlador;
import vista.Vista;

public class Modelo {
	
	private Vista[] misVistas;
	private Controlador miControlador;

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
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Conexión exitosa a MySQL");
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

}
